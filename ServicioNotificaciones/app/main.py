"""
Servicio de Notificaciones planta Manufacturera
Escucha mensajes del broker  y los reenvía
en tiempo real via WebSockets al sistema QA y al ERP.
"""
import asyncio
import json
import logging
from contextlib import asynccontextmanager
from datetime import datetime, timezone
 
import aio_pika
from fastapi import FastAPI, WebSocket, WebSocketDisconnect
from fastapi.middleware.cors import CORSMiddleware
 
logging.basicConfig(
    level=logging.INFO,
    format="%(asctime)s [%(levelname)s] %(message)s",
)
logger = logging.getLogger("servicio.notificaciones")
 

RABBITMQ_URL = "amqp://notificaciones:notificaciones123@localhost:5672/"
COLA_QA      = "cola.notificaciones.qa"
COLA_ERP     = "cola.notificaciones.erp"
 
 
#manejador de conexiones Websocket 
class ManejadorConexiones:
    def __init__(self):
        self.conexiones_qa:  list[WebSocket] = []
        self.conexiones_erp: list[WebSocket] = []
 
    async def conectar(self, websocket: WebSocket, destino: str):
        await websocket.accept()
        if destino == "qa":
            self.conexiones_qa.append(websocket)
            logger.info("✓ Sistema QA conectado  (total: %d)", len(self.conexiones_qa))
        elif destino == "erp":
            self.conexiones_erp.append(websocket)
            logger.info("✓ Sistema ERP conectado (total: %d)", len(self.conexiones_erp))
 
    def desconectar(self, websocket: WebSocket, destino: str):
        if destino == "qa" and websocket in self.conexiones_qa:
            self.conexiones_qa.remove(websocket)
            logger.info("✗ Sistema QA desconectado  (total: %d)", len(self.conexiones_qa))
        elif destino == "erp" and websocket in self.conexiones_erp:
            self.conexiones_erp.remove(websocket)
            logger.info("✗ Sistema ERP desconectado (total: %d)", len(self.conexiones_erp))
 
    async def enviar_a_qa(self, mensaje: dict):
        await self._broadcast(self.conexiones_qa, mensaje, "QA")
 
    async def enviar_a_erp(self, mensaje: dict):
        await self._broadcast(self.conexiones_erp, mensaje, "ERP")
 
    async def _broadcast(self, conexiones: list[WebSocket], mensaje: dict, nombre: str):
        caidas = []
        for ws in conexiones:
            try:
                await ws.send_json(mensaje)
            except Exception:
                caidas.append(ws)
        for ws in caidas:
            conexiones.remove(ws)
        if conexiones:
            logger.info("📡 Enviado a %d cliente(s) %s", len(conexiones), nombre)
        else:
            logger.warning("⚠ No hay clientes %s conectados", nombre)
 
 
manejador = ManejadorConexiones()
 
 
#consumidor RabbitMQ 
async def manejar_mensaje_qa(mensaje: aio_pika.abc.AbstractIncomingMessage):
    async with mensaje.process(requeue=False):
        try:
            datos = json.loads(mensaje.body.decode())
            logger.info("📨 [QA]  Defecto recibido: %s", datos.get("codigo_defecto"))
            datos["requiere_atencion"] = datos.get("gravedad_defecto") == "CRITICO"
            datos["recibido_en"] = datetime.now(timezone.utc).isoformat()
            await manejador.enviar_a_qa(datos)
        except json.JSONDecodeError:
            logger.error("✗ Mensaje malformado en cola QA")
 
 
async def manejar_mensaje_erp(mensaje: aio_pika.abc.AbstractIncomingMessage):
    async with mensaje.process(requeue=False):
        try:
            datos = json.loads(mensaje.body.decode())
            logger.info("📨 [ERP] Defecto recibido: %s", datos.get("codigo_defecto"))
            payload_erp = {
                "id_reporte":         datos.get("id_reporte"),
                "fecha_hora":         datos.get("fecha_hora"),
                "sku_producto":       datos.get("sku_producto"),
                "codigo_defecto":     datos.get("codigo_defecto"),
                "nombre_defecto":     datos.get("nombre_defecto"),
                "gravedad_defecto":   datos.get("gravedad_defecto"),
                "lote_producto":      datos.get("lote_producto"),
                "cantidad_rechazada": datos.get("cantidad_rechazada"),
                "recibido_en":        datetime.now(timezone.utc).isoformat(),
            }
            await manejador.enviar_a_erp(payload_erp)
        except json.JSONDecodeError:
            logger.error("✗ Mensaje malformado en cola ERP")
 
 
async def iniciar_consumidor():
    while True:
        try:
            conexion = await aio_pika.connect_robust(RABBITMQ_URL)
            logger.info("✓ Conectado a RabbitMQ")
            async with conexion:
                canal = await conexion.channel()
                await canal.set_qos(prefetch_count=1)
                cola_qa  = await canal.get_queue(COLA_QA)
                cola_erp = await canal.get_queue(COLA_ERP)
                await cola_qa.consume(manejar_mensaje_qa)
                await cola_erp.consume(manejar_mensaje_erp)
                logger.info("⏳ Escuchando mensajes del broker...")
                await asyncio.Future()
        except aio_pika.AMQPConnectionError as e:
            logger.error("✗ Error de conexión al broker: %s — reintentando en 5s...", e)
            await asyncio.sleep(5)
        except asyncio.CancelledError:
            break

@asynccontextmanager
async def lifespan(app: FastAPI):
    tarea = asyncio.create_task(iniciar_consumidor())
    yield
    tarea.cancel()
 
 
app = FastAPI(title="Servicio de Notificaciones", lifespan=lifespan)
 
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_methods=["*"],
    allow_headers=["*"],
)
 
 
@app.get("/health")
async def health():
    return {
        "status": "ok",
        "timestamp": datetime.now(timezone.utc).isoformat(),
        "clientes_qa":  len(manejador.conexiones_qa),
        "clientes_erp": len(manejador.conexiones_erp),
    }
 
 
@app.websocket("/ws/qa")
async def websocket_qa(websocket: WebSocket):
    await manejador.conectar(websocket, "qa")
    try:
        while True:
            await websocket.receive_text()
    except WebSocketDisconnect:
        manejador.desconectar(websocket, "qa")
 
 
@app.websocket("/ws/erp")
async def websocket_erp(websocket: WebSocket):
    await manejador.conectar(websocket, "erp")
    try:
        while True:
            await websocket.receive_text()
    except WebSocketDisconnect:
        manejador.desconectar(websocket, "erp")