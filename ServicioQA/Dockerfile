FROM python:3.10-slim
WORKDIR /app

COPY requirements.txt .
RUN pip install websockets
COPY . .


CMD ["python", "ServicioQA.py"]
