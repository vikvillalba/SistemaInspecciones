import { useEffect, useState } from "react";
import { obtenerDefectos } from "../api/index";
import { type Defecto } from "../types/index";

export default function Defectos() {
    const [defectos, setDefectos] = useState<Defecto[]>([]);
    const [cargando, setCargando] = useState(true);
    const [error, setError] = useState("");
    const [filtroGravedad, setFiltroGravedad] = useState(0);

    useEffect(() => {
        setCargando(true);
        obtenerDefectos(filtroGravedad)
            .then((data) => setDefectos(data))
            .catch(() => setError("No se pudieron cargar los defectos"))
            .finally(() => setCargando(false));
    }, [filtroGravedad])

    return (
        <div className="pagina">
            <h1>Catálogo de Defectos</h1>

            <div className="filtros">
                <label>Filtrar por gravedad:</label>
                <select value={filtroGravedad} onChange={(e) => setFiltroGravedad(Number(e.target.value))}>
                    <option value={0}>Todos</option>
                    <option value={1}>Menor</option>
                    <option value={2}>Mayor</option>
                    <option value={3}>Crítico</option>
                </select>
            </div>

            {cargando && <p>Cargando...</p>}
            {error && <p className="error">{error}</p>}

            {!cargando && !error && (
                <table className="tabla">
                    <thead>
                        <tr>
                            <th>Código</th>
                            <th>Nombre</th>
                            <th>Gravedad</th>
                        </tr>
                    </thead>
                    <tbody>
                        {defectos.map((d) => (
                            <tr key={d.codigo}>
                                <td>{d.codigo}</td>
                                <td>{d.nombre}</td>
                                <td>{d.gravedad}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            )}
        </div>
    );
}