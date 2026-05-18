import { Link, useLocation } from "react-router-dom";
import { useAuth0 } from "@auth0/auth0-react"; 

export default function Barrita() {
    const location = useLocation();
    const { loginWithRedirect, logout, isAuthenticated, user } = useAuth0();

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
                
                {/* --- Lógica de Auth0 --- */}
                {!isAuthenticated ? (
                    // Si no está autenticado, mostramos boton de iniciar sesión
                    <button 
                        onClick={() => loginWithRedirect()}
                        style={{ marginLeft: "auto", cursor: "pointer", padding: "5px 10px", borderRadius: "5px", border: "none", backgroundColor: "#007bff", color: "white", fontWeight: "bold" }}
                    >
                        Iniciar Sesión
                    </button>
                ) : (
                    // Si ya está autenticado, mostramos su nombre y botn de Cerrar Sesión
                    <div style={{ marginLeft: "auto", display: "flex", alignItems: "center", gap: "15px" }}>
                        <span style={{ fontSize: "0.9rem", color: "#666" }}>
                            Hola, {user?.name || user?.email}
                        </span>
                        <button 
                            onClick={() => logout({ logoutParams: { returnTo: window.location.origin } })}
                            style={{ cursor: "pointer", padding: "5px 10px", borderRadius: "5px", border: "1px solid #dc3545", backgroundColor: "transparent", color: "#dc3545", fontWeight: "bold" }}
                        >
                            Salir
                        </button>
                    </div>
                )}
            </div>
        </nav>
    );
}