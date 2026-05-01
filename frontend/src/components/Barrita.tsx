import { Link, useLocation } from "react-router-dom";

export default function Barrita() {
    const location = useLocation();

    const activo = (path: string) =>
        location.pathname === path ? "activo" : "";

    return (
        <nav className="barrita">
            <span className="barrita-titulo">Sistema de Inspecciones</span>
            <div className="barrita-links">
                <Link className={activo("/")} to="/">Inicio</Link>
                <Link className={activo("/productos")} to="/productos">Productos</Link>
                <Link className={activo("/defectos")} to="/defectos">Defectos</Link>
                <Link className={activo("/inspecciones/nueva")} to="/inspecciones/nueva">Registrar Inspección</Link>

            </div>
        </nav>
    );
}