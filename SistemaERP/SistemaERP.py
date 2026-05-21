import os
import asyncio
import websockets
import json

async def escuchar_notificaciones_erp():
    host_ws = os.getenv("WS_SERVER_URL", "localhost:8000")
    uri = f"ws://{host_ws}/ws/erp"
   
    try:
        async with websockets.connect(uri) as websocket:
            print("[ERP] Conectado")
            while True:
                mensaje = await websocket.recv()
                datos = json.loads(mensaje)

                print("\n" + "═"*50)
                print("[ERP] Nuevo rechazo de producto")
                print("═"*50)
                print(f" ID Reporte:      {datos.get('id_reporte')}")
                print(f" Fecha/Hora:      {datos.get('fecha_hora')}")
                print(f" SKU Producto:    {datos.get('sku_producto')}")
                print(f" Código Defecto:  {datos.get('codigo_defecto')}")
                print(f" Nombre Defecto:  {datos.get('nombre_defecto')}")
                print(f" Gravedad:        {datos.get('gravedad_defecto')}")
                print(f" Lote:            {datos.get('lote_producto')}")
                print(f" Cant. Rechazada: {datos.get('cantidad_rechazada')}")
                print(f" Recibido en ERP: {datos.get('recibido_en')}")
                print("═"*50)

    except websockets.ConnectionClosed:
        print("Conexión cerrada con el servidor")

asyncio.run(escuchar_notificaciones_erp())