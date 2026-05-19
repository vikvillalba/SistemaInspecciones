import { useEffect, useState } from "react";
import { obtenerProductos } from "../api/index";
import { type Producto } from "../types/index";
import { useAuth0 } from "@auth0/auth0-react";

export default function Productos() {
    const { getAccessTokenSilently } = useAuth0(); // <-- 2. Sacar la función
    const [productos, setProductos] = useState<Producto[]>([]);
    const [cargando, setCargando] = useState(true);
    const [error, setError] = useState("");

    useEffect(() => {
        const cargar = async () => {
            setCargando(true);
            try {
                const token = await getAccessTokenSilently(); // <-- 3. Pedir el token
                // Como obtenerProductos tiene (tipo, soloDisponibles, token), le pasamos 0 y false
                const data = await obtenerProductos(0, false, token); 
                setProductos(data);
            } catch (err) {
                setError("No se pudieron cargar los productos");
            } finally {
                setCargando(false);
            }
        };

        cargar();
    }, [getAccessTokenSilently]); // <-- 4. Agregar a las dependencias

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