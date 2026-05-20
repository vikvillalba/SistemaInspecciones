FROM python:3.10-slim
WORKDIR /app

RUN pip install --upgrade pip
RUN pip install grpcio grpcio-tools protobuf==4.25.1

COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt
RUN pip install --no-cache-dir protobuf==4.25.1
COPY . .

RUN python -m grpc_tools.protoc -I. --python_out=. --grpc_python_out=. cambio.proto

CMD ["python", "CambioServicer.py"]
