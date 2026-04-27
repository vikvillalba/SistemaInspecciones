import grpc from "@grpc/grpc-js";
import protoLoader from "@grpc/proto-loader";
import path from "path";
import { fileURLToPath } from "url";

const __dirname = path.dirname(fileURLToPath(import.meta.url));

const packageDef = protoLoader.loadSync(
    path.join(__dirname, "../proto/inspecciones.proto"),
    { keepCase: true, longs: String, enums: String, defaults: true, oneofs: true }
);

const proto = grpc.loadPackageDefinition(packageDef).reporte;

const client = new proto.ReportesService(
    process.env.SERVICIO_INSPECCIONES_URL,
    grpc.credentials.createInsecure()
);

export const registrarReporte = (datos) =>
    new Promise((resolve, reject) => {
        const fecha = datos.fechaHora
            ? new Date(datos.fechaHora)
            : new Date();
        client.registrarReporte(
            {
                fecha_hora: {
                    seconds: Math.floor(fecha.getTime() / 1000),
                    nanos: 0,
                },
                nombre_inspector: datos.nombreInspector,
                sku_producto: datos.skuProducto,
                codigo_defecto: datos.codigoDefecto,
                tamano_muestra: datos.tamanoMuestra,
                lote_producto: datos.loteProducto,
                cantidad_rechazada: datos.cantidadRechazada,
                comentarios: datos.comentarios ?? "",
            },
            (err, res) => {
                if (err) reject(err);
                else resolve(res);
            }
        );
    });

export const obtenerReporte = (idReporte) =>
    new Promise((resolve, reject) => {
        client.obtenerReporte(
            { filtro: 0, id_reporte: idReporte },
            (err, res) => {
                if (err) reject(err);
                else resolve(res);
            }
        );
    });

export const obtenerReportes = (filtro = 0, valor = "") =>
    new Promise((resolve, reject) => {
        const reportes = [];
        const call = client.obtenerReportes(buildReporteRequest(filtro, valor));
        call.on("data", (r) => reportes.push(r));
        call.on("end", () => resolve(reportes));
        call.on("error", reject);
    });

function buildReporteRequest(filtro, valor) {
    switch (filtro) {
        case 1: return { filtro, sku_producto: valor };
        case 3: return { filtro, lote_producto: valor };
        case 4: return { filtro, nombre_inspector: valor };
        case 5: return { filtro, tipo_defecto: valor };
        default: return { filtro: 0 };
    }
}