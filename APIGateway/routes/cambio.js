import { Router } from "express";
import { getConversion } from "../grpc/clientes/servicioCambio.js";

const router = Router();

router.get("/", async (req, res, next) => {
    try {
        const { sku, cantidad } = req.query;
        if (!sku || !cantidad) {
            return res.status(400).json({ error: "Los parámetros 'sku' y 'cantidad' son requeridos" });
        }
        const resultado = await getConversion(sku, parseFloat(cantidad));
        res.json(resultado);
    } catch (err) {
        next(err);
    }
});

export default router;