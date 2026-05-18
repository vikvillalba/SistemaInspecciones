import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth0 } from "@auth0/auth0-react"; 
import { obtenerReportes } from "../api/index";
import { type Reporte } from "../types/index";

export default function Reportes() {
    const navigate = useNavigate();
    const { getAccessTokenSilently } = useAuth0(); // <-- Sacamos la función

    const [reportes, setReportes] = useState<Reporte[]>([]);
    const [cargando, setCargando] = useState(true);
    const [error, setError] = useState("");
    const [filtro, setFiltro] = useState("TODOS");
    const [valor, setValor] = useState("");

    useEffect(() => { 
        const cargar = async () => {
            setCargando(true);
            try {
                
                const token = await getAccessTokenSilently();
                const data = await obtenerReportes(filtro, valor, token);
                setReportes(data);
            } catch (err) {
                setError("No se pudieron cargar los reportes");
            } finally {
                setCargando(false);
            }
        };

        cargar(); 
    }, [filtro, valor, getAccessTokenSilently]);

    return (
        <div className="pagina">
            <h1>Reportes de Inspección</h1>
            {cargando && <p>Cargando...</p>}
            {error && <p className="error">{error}</p>}

            {!cargando && !error && (
                <table className="tabla">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Inspector</th>
                            <th>Producto</th>
                            <th>Defecto</th>
                            <th>Lote</th>
                            <th>Rechazados</th>
                        </tr>
                    </thead>
                    <tbody>
                        {reportes.map((r) => (
                            <tr key={r.id_reporte} onClick={() => navigate(`/reportes/${r.id_reporte}`)} style={{ cursor: "pointer" }}>
                                <td>{r.id_reporte.slice(0, 8)}...</td>
                                <td>{r.nombre_inspector}</td>
                                <td>{r.sku_producto}</td>
                                <td>{r.codigo_defecto}</td>
                                <td>{r.lote_producto}</td>
                                <td>{r.cantidad_rechazada}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            )}
        </div>
    );
}