import { Router } from "express";
import {
    registrarReporte,
    obtenerReporte,
    obtenerReportes,
} from "../grpc/clientes/servicioInspecciones.js";

const router = Router();

const FILTROS = {
    TODOS: 0,
    PRODUCTO: 1,
    LOTE: 3,
    INSPECTOR: 4,
    DEFECTO: 5,
};

router.post("/", async (req, res, next) => {
    try {
        const reporte = await registrarReporte(req.body);
        res.status(201).json(reporte);
    } catch (err) {
        next(err);
    }
});

router.get("/", async (req, res, next) => {
    try {
        const filtro = FILTROS[req.query.filtro?.toUpperCase()] ?? 0;
        const valor = req.query.valor ?? "";
        const reportes = await obtenerReportes(filtro, valor);
        res.json(reportes);
    } catch (err) {
        next(err);
    }
});

router.get("/:id", async (req, res, next) => {
    try {
        const reporte = await obtenerReporte(req.params.id);
        res.json(reporte);
    } catch (err) {
        next(err);
    }
});

export default router;