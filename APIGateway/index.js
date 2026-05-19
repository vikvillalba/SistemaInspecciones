import "dotenv/config";
import express from "express";
import cors from "cors";

import productosRouter from "./routes/productos.js";
import defectosRouter from "./routes/defectos.js";
import inspeccionesRouter from "./routes/inspecciones.js";
import cambioRouter from "./routes/cambio.js";
import errorHandler from "./middleware/errorHandler.js";

import { validarToken } from "./middleware/auth0.js";

const app = express();

app.use(cors());
app.use(express.json());
// Proteger las rutas con el middleware validarToken de Auth0
app.use("/api/productos", validarToken, productosRouter);
app.use("/api/defectos", validarToken, defectosRouter);
app.use("/api/cambio", validarToken, cambioRouter);
app.use("/api/inspecciones", validarToken, inspeccionesRouter);

app.get("/health", (_req, res) => res.json({ status: "ok" }));
app.use(errorHandler);

const PORT = process.env.PORT ?? 3000;
app.listen(PORT, () => {
    console.log(`API Gateway corriendo en http://localhost:${PORT}`);
    console.log(`Conectando a:`);
    console.log(`Catálogo Productos -> ${process.env.CATALOGO_PRODUCTOS_URL}`);
    console.log(`Catálogo Defectos -> ${process.env.CATALOGO_DEFECTOS_URL}`);
    console.log(`Servicio Inspecciones -> ${process.env.SERVICIO_INSPECCIONES_URL}`);
    console.log(`Servicio Cambio -> ${process.env.SERVICIO_CAMBIO_URL}`);
});