import grpc from "@grpc/grpc-js";
import protoLoader from "@grpc/proto-loader";
import path from "path";
import { fileURLToPath } from "url";

const __dirname = path.dirname(fileURLToPath(import.meta.url));

const packageDef = protoLoader.loadSync(
    path.join(__dirname, "../proto/productos.proto"),
    { keepCase: true, longs: String, enums: String, defaults: true, oneofs: true }
);

const proto = grpc.loadPackageDefinition(packageDef).producto;

const client = new proto.CatalogoService(
    process.env.CATALOGO_PRODUCTOS_URL,
    grpc.credentials.createInsecure()
);

export const verificarProducto = (sku) =>
    new Promise((resolve, reject) => {
        client.VerificarYObtenerProducto({ sku }, (err, response) => {
            if (err) reject(err);
            else resolve(response);
        });
    });

export const obtenerCatalogo = (tipoProducto = 0, soloDisponibles = false) =>
    new Promise((resolve, reject) => {
        const productos = [];
        const call = client.ObtenerCatalogo({
            tipo_producto: tipoProducto,
            solo_disponibles: soloDisponibles,
        });
        call.on("data", (producto) => productos.push(producto));
        call.on("end", () => resolve(productos));
        call.on("error", reject);
    });