import asyncio
import httpx
import grpc
import cambio_pb2
import cambio_pb2_grpc
import os
from dotenv import load_dotenv

# Carga las variables del archivo .env
load_dotenv()

async def calculos(servicer_instance, identificador_producto, cantidad):
    
    tasa = await servicer_instance.getTasa() # Metodo para obtener la tasa de Banxico

    try:
        request_cat = cambio_pb2.ProductoRequest(sku=identificador_producto)
        response_cat = await servicer_instance.catalogo_stub.VerificarYObtenerProducto(request_cat)
        precio_base = response_cat.costo_unitario
        print(f"Catálogo respondió: {response_cat.nombre} cuesta ${precio_base}")
        
    except Exception as e:
        print(f"Error al consultar Catálogo: {e}. Usando precio Mock.")
        precio_base = 50.0
    
    # Calculo
    total_mxn = precio_base * cantidad
    total_usd = total_mxn / tasa
    
    return {
        "nombre": identificador_producto,
        "tasa": tasa,
        "usd": total_usd,
        "mxn": total_mxn
    }

class CambioServicer(cambio_pb2_grpc.CambioServiceServicer):
    def __init__(self):
        # Configuración
        self.banco_url = os.getenv("BANXICO_URL")
        self.token = os.getenv("BANXICO_TOKEN")

        self.catalogo_url = os.getenv("CATALOGO_URL")
        self.channel = grpc.aio.insecure_channel(self.catalogo_url)
        self.catalogo_stub = cambio_pb2_grpc.CatalogoServiceStub(self.channel)

    async def getTasa(self):
        headers = {
            "Bmx-Token": self.token, 
            "Accept": "application/json"
        }

        async with httpx.AsyncClient(http2=True) as client:
            try:
                response = await client.get(self.banco_url, headers=headers, timeout=5.0) # Obtiene la tasa de Banxico
                response.raise_for_status()
                data = response.json()
                valor_tasa = data['bmx']['series'][0]['datos'][0]['dato']
                return float(valor_tasa)
            except Exception as e:
                print(f"Error en Banxico: {e}")
                return 18.50 # Tasa de emergencia

    # Metodo de servicio grpc (http2)
    async def GetConversion(self, request, context):
        print(f"Petición grpc recibida para: {request.sku}")
        res = await calculos(self, request.sku, request.cantidad)
        
        return cambio_pb2.ConversionResponse(
            total_mxn=res["mxn"],
            total_usd=res["usd"],
            tasa_usada=res["tasa"]
        )
    
async def serve():
    server = grpc.aio.server()
    cambio_pb2_grpc.add_CambioServiceServicer_to_server(CambioServicer(), server)
    server.add_insecure_port(os.getenv("CAMBIO_URL")) # Puerto del servicio de cambio
    print(f"Servidor grpc iniciado en el puerto {os.getenv('CAMBIO_URL')}")
    await server.start()
    try:
        await server.wait_for_termination()
    except KeyboardInterrupt:
        await server.stop(0)

if __name__ == "__main__":
    asyncio.run(serve())