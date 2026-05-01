import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { obtenerReportes } from "../api/index";
import { type Reporte } from "../types/index";

export default function Reportes() {
    const navigate = useNavigate();
    const [reportes, setReportes] = useState<Reporte[]>([]);
    const [cargando, setCargando] = useState(true);
    const [error, setError] = useState("");
    const [filtro, setFiltro] = useState("TODOS");
    const [valor, setValor] = useState("");

    const cargar = () => {
        setCargando(true);
        obtenerReportes(filtro, valor)
            .then(setReportes)
            .catch(() => setError("No se pudieron cargar los reportes"))
            .finally(() => setCargando(false));
    };

    useEffect(() => { cargar(); }, []);

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