import { BrowserRouter, Routes, Route } from "react-router-dom";
import { useAuth0 } from "@auth0/auth0-react";
import Barrita from "./components/Barrita";
import Productos from "./pantallas/Productos";
import Defectos from "./pantallas/Defectos";
import RegistrarInspeccion from "./pantallas/RegistrarInspeccion";
import Reportes from "./pantallas/Reportes";
import DetalleReporte from "./pantallas/DetalleReporte";

export default function App() {
  //funciones y estados de Auth0
  const { isAuthenticated, isLoading, loginWithRedirect } = useAuth0();

  // Pantalla de carga mientras Auth0 verifica si hay una sesión activa
  if (isLoading) {
    return (
      <div style={{ textAlign: "center", marginTop: "100px" }}>
        <h2>Cargando sistema...</h2>
      </div>
    );
  }

  // Si el usuario NO está autenticado, bloqueamos las rutas y mostramos el Login
  if (!isAuthenticated) {
    return (
      <div style={{ textAlign: "center", marginTop: "100px" }}>
        <h1>Sistema de Inspecciones</h1>
        <p>Acceso restringido. Por favor, inicia sesión para continuar.</p>
        <button 
          onClick={() => loginWithRedirect()}
          style={{ padding: "10px 20px", fontSize: "16px", cursor: "pointer", marginTop: "20px" }}
        >
          Iniciar Sesión
        </button>
      </div>
    );
  }

  //Si SÍ está autenticado entra a la aplicacion 
  return (
    <BrowserRouter>
      <Barrita />
      <Routes>
        <Route path="/" element={<Reportes />} />
        <Route path="/productos" element={<Productos />} />
        <Route path="/defectos" element={<Defectos />} />
        <Route path="/inspecciones/nueva" element={<RegistrarInspeccion />} />
        <Route path="/reportes" element={<Reportes />} />
        <Route path="/reportes/:id" element={<DetalleReporte />} />
      </Routes>
    </BrowserRouter>
  );
}