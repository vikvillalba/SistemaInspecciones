import grpc from "@grpc/grpc-js";
import protoLoader from "@grpc/proto-loader";
import path from "path";
import { fileURLToPath } from "url";

const __dirname = path.dirname(fileURLToPath(import.meta.url));

const packageDef = protoLoader.loadSync(
    path.join(__dirname, "../proto/defectos.proto"),
    { keepCase: true, longs: String, enums: String, defaults: true, oneofs: true }
);

const proto = grpc.loadPackageDefinition(packageDef).defecto;

const client = new proto.DefectosService(
    process.env.CATALOGO_DEFECTOS_URL,
    grpc.credentials.createInsecure()
);

export const obtenerDefecto = (codigoDefecto) =>
    new Promise((resolve, reject) => {
        client.obtenerDefecto(
            { codigo_defecto: codigoDefecto },
            (err, res) => {
                if (err) reject(err);
                else resolve(res);
            }
        );
    });

export const obtenerDefectosPorGravedad = (nivel = 0) =>
    new Promise((resolve, reject) => {
        const defectos = [];
        const call = client.obtenerDefectosPorGravedad({ nivel });
        call.on("data", (d) => defectos.push(d));
        call.on("end", () => resolve(defectos));
        call.on("error", reject);
    });