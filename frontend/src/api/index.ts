const BASE_URL = "http://localhost:3000/api";

//productos
export const obtenerProductos = async (tipo = 0, soloDisponibles = false) => {
    const res = await fetch(`${BASE_URL}/productos?tipo=${tipo}&soloDisponibles=${soloDisponibles}`);
    if (!res.ok) throw new Error("Error al obtener productos");
    return res.json();
};

export const obtenerProducto = async (sku: string) => {
    const res = await fetch(`${BASE_URL}/productos/${sku}`);
    if (!res.ok) throw new Error("Producto no encontrado");
    return res.json();
};

//degectos
export const obtenerDefectos = async (gravedad = 0) => {
    const res = await fetch(`${BASE_URL}/defectos?gravedad=${gravedad}`);
    if (!res.ok) throw new Error("Error al obtener defectos");
    return res.json();
};

export const obtenerDefecto = async (codigo: string) => {
    const res = await fetch(`${BASE_URL}/defectos/${codigo}`);
    if (!res.ok) throw new Error("Defecto no encontrado");
    return res.json();
};

//inspecciones
export const registrarReporte = async (datos: {
    nombreInspector: string;
    skuProducto: string;
    codigoDefecto: string;
    tamanoMuestra: number;
    loteProducto: string;
    cantidadRechazada: number;
    comentarios?: string;
}) => {
    const res = await fetch(`${BASE_URL}/inspecciones`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(datos),
    });
    if (!res.ok) throw new Error("Error al registrar reporte");
    return res.json();
};

export const obtenerReportes = async (filtro = "TODOS", valor = "") => {
    const res = await fetch(`${BASE_URL}/inspecciones?filtro=${filtro}&valor=${valor}`);
    if (!res.ok) throw new Error("Error al obtener reportes");
    return res.json();
};

export const obtenerReporte = async (id: string) => {
    const res = await fetch(`${BASE_URL}/inspecciones/${id}`);
    if (!res.ok) throw new Error("Reporte no encontrado");
    return res.json();
};

//cambio
export const obtenerConversion = async (sku: string, cantidad: number) => {
    const res = await fetch(`${BASE_URL}/cambio?sku=${sku}&cantidad=${cantidad}`);
    if (!res.ok) throw new Error("Error al obtener conversión");
    return res.json();
};