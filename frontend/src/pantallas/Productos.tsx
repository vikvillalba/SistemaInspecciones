import { useEffect, useState } from "react";
import { obtenerProductos } from "../api/index";
import { type Producto } from "../types/index";

export default function Productos() {
    const [productos, setProductos] = useState<Producto[]>([]);
    const [cargando, setCargando] = useState(true);
    const [error, setError] = useState("");

    useEffect(() => {
        obtenerProductos()
            .then((data) => setProductos(data))
            .catch(() => setError("No se pudieron cargar los productos"))
            .finally(() => setCargando(false));
    }, []);

    if (cargando) return <p>Cargando...</p>;
    if (error) return <p className="error">{error}</p>;

    return (
        <div className="pagina">
            <h1>Catálogo de Productos</h1>
            <table className="tabla">
                <thead>
                    <tr>
                        <th>SKU</th>
                        <th>Nombre</th>
                        <th>Costo unitario</th>
                        <th>Tipo</th>
                        <th>Disponible</th>
                    </tr>
                </thead>
                <tbody>
                    {productos.map((p) => (
                        <tr key={p.sku}>
                            <td>{p.sku}</td>
                            <td>{p.nombre}</td>
                            <td>${p.costo_unitario.toFixed(2)}</td>
                            <td>{p.tipo}</td>
                            <td>{p.disponible ? "Sí" : "No"}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}