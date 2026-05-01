import { BrowserRouter, Routes, Route } from "react-router-dom";
import Barrita from "./components/Barrita";
import Productos from "./pantallas/Productos";
import Defectos from "./pantallas/Defectos";
import RegistrarInspeccion from "./pantallas/RegistrarInspeccion";
import Reportes from "./pantallas/Reportes";
import DetalleReporte from "./pantallas/DetalleReporte";

export default function App() {
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