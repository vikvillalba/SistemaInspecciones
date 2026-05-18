import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth0 } from "@auth0/auth0-react"; 
import { obtenerProductos, obtenerDefectos, registrarReporte } from "../api/index";
import { type Producto, type Defecto } from "../types/index";

export default function RegistrarInspeccion() {
    const navigate = useNavigate();
    const { getAccessTokenSilently } = useAuth0();

    const [productos, setProductos] = useState<Producto[]>([]);
    const [defectos, setDefectos] = useState<Defecto[]>([]);
    const [enviando, setEnviando] = useState(false);
    const [error, setError] = useState("");

    const [form, setForm] = useState({
        nombreInspector: "",
        skuProducto: "",
        codigoDefecto: "",
        tamanoMuestra: 0,
        loteProducto: "",
        cantidadRechazada: 0,
        comentarios: "",
    });

    useEffect(() => {
        obtenerProductos().then(setProductos);
        obtenerDefectos().then(setDefectos);
    }, []);

    const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement | HTMLTextAreaElement>) => {
        const { name, value } = e.target;
        setForm((prev) => ({
            ...prev,
            [name]: e.target.type === "number" ? Number(value) : value,
        }));
    };

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        setEnviando(true);
        setError("");
        try {
            // 1. Conseguimos el token silenciosamente
            const token = await getAccessTokenSilently(); 
            
            // 2. Lo enviamos como parámetro a tu API
            await registrarReporte(form, token); 
            navigate("/reportes");
        } catch (error) {
            setError("Error al registrar la inspección. Verifica tu sesión.");
            console.error(error);
        } finally {
            setEnviando(false);
        }
    };

    return (
        <div className="pagina">
            <h1>Registrar Inspección</h1>
            {error && <p className="error">{error}</p>}
            <form onSubmit={handleSubmit} className="formulario">
                <label>Inspector</label>
                <input name="nombreInspector" value={form.nombreInspector} onChange={handleChange} required />

                <label>Producto</label>
                <select name="skuProducto" value={form.skuProducto} onChange={handleChange} required>
                    <option value="">Selecciona un producto</option>
                    {productos.map((p) => (
                        <option key={p.sku} value={p.sku}>{p.nombre} ({p.sku})</option>
                    ))}
                </select>

                <label>Defecto</label>
                <select name="codigoDefecto" value={form.codigoDefecto} onChange={handleChange} required>
                    <option value="">Selecciona un defecto</option>
                    {defectos.map((d) => (
                        <option key={d.codigo} value={d.codigo}>{d.nombre} — {d.gravedad}</option>
                    ))}
                </select>

                <label>Lote</label>
                <input name="loteProducto" value={form.loteProducto} onChange={handleChange} required />

                <label>Tamaño de muestra</label>
                <input type="number" name="tamanoMuestra" value={form.tamanoMuestra} onChange={handleChange} required min={1} />

                <label>Cantidad rechazada</label>
                <input type="number" name="cantidadRechazada" value={form.cantidadRechazada} onChange={handleChange} required min={0} />

                <label>Comentarios</label>
                <textarea name="comentarios" value={form.comentarios} onChange={handleChange} />

                <button type="submit" disabled={enviando}>
                    {enviando ? "Guardando..." : "Registrar"}
                </button>
            </form>
        </div>
    );
}