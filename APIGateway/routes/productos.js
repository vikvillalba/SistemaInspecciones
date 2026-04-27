import { Router } from "express";
import { verificarProducto, obtenerCatalogo } from "../grpc/clientes/catalogoProductos.js";

const router = Router();

router.get("/", async (req, res, next) => {
    try {
        const tipo = parseInt(req.query.tipo ?? "0");
        const soloDisponibles = req.query.soloDisponibles === "true";
        const productos = await obtenerCatalogo(tipo, soloDisponibles);
        res.json(productos);
    } catch (err) {
        next(err);
    }
});

router.get("/:sku", async (req, res, next) => {
    try {
        const producto = await verificarProducto(req.params.sku);
        res.json(producto);
    } catch (err) {
        next(err);
    }
});

export default router;