import { Router } from "express";
import { obtenerDefecto, obtenerDefectosPorGravedad } from "../grpc/clientes/catalogoDefectos.js";

const router = Router();

router.get("/", async (req, res, next) => {
    try {
        const nivel = parseInt(req.query.gravedad ?? "0");
        const defectos = await obtenerDefectosPorGravedad(nivel);
        res.json(defectos);
    } catch (err) {
        next(err);
    }
});

router.get("/:codigo", async (req, res, next) => {
    try {
        const defecto = await obtenerDefecto(req.params.codigo);
        res.json(defecto);
    } catch (err) {
        next(err);
    }
});

export default router;