import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { obtenerReporte, obtenerConversion } from "../api/index";
import { type Reporte, type Conversion } from "../types/index";

export default function DetalleReporte() {
  const { id } = useParams<{ id: string }>();
  const [reporte, setReporte] = useState<Reporte | null>(null);
  const [conversion, setConversion] = useState<Conversion | null>(null);
  const [cargando, setCargando] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    if (!id) return;
    obtenerReporte(id)
      .then((data) => {
        setReporte(data);
        return obtenerConversion(data.sku_producto, data.cantidad_rechazada);
      })
      .then(setConversion)
      .catch(() => setError("No se pudo cargar el reporte"))
      .finally(() => setCargando(false));
  }, [id]);

  if (cargando) return <p>Cargando...</p>;
  if (error) return <p className="error">{error}</p>;
  if (!reporte) return null;

  const fecha = new Date(Number(reporte.fecha_hora.seconds) * 1000).toLocaleString("es-MX");

  return (
    <div className="pagina">
      <h1>Detalle del Reporte</h1>

      <div className="detalle">
        <p><strong>ID:</strong> {reporte.id_reporte}</p>
        <p><strong>Fecha:</strong> {fecha}</p>
        <p><strong>Inspector:</strong> {reporte.nombre_inspector}</p>
        <p><strong>Producto:</strong> {reporte.sku_producto}</p>
        <p><strong>Defecto:</strong> {reporte.codigo_defecto}</p>
        <p><strong>Lote:</strong> {reporte.lote_producto}</p>
        <p><strong>Muestra:</strong> {reporte.tamano_muestra}</p>
        <p><strong>Rechazados:</strong> {reporte.cantidad_rechazada}</p>
        {reporte.comentarios && <p><strong>Comentarios:</strong> {reporte.comentarios}</p>}

        {conversion && (
          <>
            <p style={{ gridColumn: "1 / -1", borderTop: "1px solid #edf2f7", paddingTop: "0.75rem", fontWeight: 700, color: "#1a1a2e" }}>
              Costo de piezas rechazadas
            </p>
            <p><strong>Total MXN:</strong> ${conversion.total_mxn.toFixed(2)}</p>
            <p><strong>Total USD:</strong> ${conversion.total_usd.toFixed(2)}</p>
            <p><strong>Tasa del día:</strong> {conversion.tasa_usada.toFixed(4)}</p>
          </>
        )}
      </div>
    </div>
  );
}