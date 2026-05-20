import os
import asyncio
import websockets
import json

async def escuchar_notificaciones_qa():
    host_ws = os.getenv("WS_SERVER_URL", "localhost:8000")
    uri = f"ws://{host_ws}/ws/qa"
    async with websockets.connect(uri) as websocket:
        print("[QA] Conectado")
        try:
            while True:
                mensaje = await websocket.recv()
                datos = json.loads(mensaje)
                
                # Si es crítico, lanzar alerta
                if datos.get("requiere_atencion"):
                    print(f"ALERTA CRÍTICA: Defecto {datos['codigo_defecto']} en producto {datos['sku_producto']}")
                else:
                    print(f"Notificación recibida para producto {datos['sku_producto']}: {datos['nombre_defecto']}")
        except websockets.ConnectionClosed:
            print("Conexión cerrada con el servidor")

asyncio.run(escuchar_notificaciones_qa())