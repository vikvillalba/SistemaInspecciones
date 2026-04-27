import grpc from "@grpc/grpc-js";
import protoLoader from "@grpc/proto-loader";
import path from "path";
import { fileURLToPath } from "url";

const __dirname = path.dirname(fileURLToPath(import.meta.url));

const packageDef = protoLoader.loadSync(
    path.join(__dirname, "../proto/cambio.proto"),
    { keepCase: true, longs: String, enums: String, defaults: true, oneofs: true }
);

const proto = grpc.loadPackageDefinition(packageDef).producto;

const client = new proto.CambioService(
    process.env.SERVICIO_CAMBIO_URL,
    grpc.credentials.createInsecure()
);

export const getConversion = (sku, cantidad) =>
    new Promise((resolve, reject) => {
        client.GetConversion({ sku, cantidad }, (err, res) => {
            if (err) reject(err);
            else resolve(res);
        });
    });