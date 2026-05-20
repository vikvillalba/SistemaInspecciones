const BASE_URL = import.meta.env.VITE_API_URL 
  ? `${import.meta.env.VITE_API_URL}/api` 
  : "http://localhost:8080/api";

// --- PRODUCTOS ---
export const obtenerProductos = async (tipo = 0, soloDisponibles = false, token: string) => {
    const res = await fetch(`${BASE_URL}/productos?tipo=${tipo}&soloDisponibles=${soloDisponibles}`, {
        headers: {
            "Authorization": `Bearer ${token}`
        }
    });
    if (!res.ok) throw new Error("Error al obtener productos");
    return res.json();
};

export const obtenerProducto = async (sku: string, token: string) => {
    const res = await fetch(`${BASE_URL}/productos/${sku}`, {
        headers: {
            "Authorization": `Bearer ${token}`
        }
    });
    if (!res.ok) throw new Error("Producto no encontrado");
    return res.json();
};

// --- DEFECTOS ---
export const obtenerDefectos = async (gravedad = 0, token: string) => {
    const res = await fetch(`${BASE_URL}/defectos?gravedad=${gravedad}`, {
        headers: {
            "Authorization": `Bearer ${token}`
        }
    });
    if (!res.ok) throw new Error("Error al obtener defectos");
    return res.json();
};

export const obtenerDefecto = async (codigo: string, token: string) => {
    const res = await fetch(`${BASE_URL}/defectos/${codigo}`, {
        headers: {
            "Authorization": `Bearer ${token}`
        }
    });
    if (!res.ok) throw new Error("Defecto no encontrado");
    return res.json();
};

// --- INSPECCIONES (Estos ya los tenías bien) ---
export const registrarReporte = async (datos: {
    nombreInspector: string;
    skuProducto: string;
    codigoDefecto: string;
    tamanoMuestra: number;
    loteProducto: string;
    cantidadRechazada: number;
    comentarios?: string;
}, token: string) => { 
    const res = await fetch(`${BASE_URL}/inspecciones`, {
        method: "POST",
        headers: { 
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}` 
        },
        body: JSON.stringify(datos),
    });
    if (!res.ok) throw new Error("Error al registrar reporte");
    return res.json();
};

export const obtenerReportes = async (filtro = "TODOS", valor = "", token: string) => { 
    const res = await fetch(`${BASE_URL}/inspecciones?filtro=${filtro}&valor=${valor}`, {
        headers: {
            "Authorization": `Bearer ${token}` 
        }
    });
    if (!res.ok) throw new Error("Error al obtener reportes");
    return res.json();
};

export const obtenerReporte = async (id: string, token: string) => { 
    const res = await fetch(`${BASE_URL}/inspecciones/${id}`, {
        headers: {
            "Authorization": `Bearer ${token}` 
        }
    });
    if (!res.ok) throw new Error("Reporte no encontrado");
    return res.json();
};

// --- CONVERSIÓN ---
export const obtenerConversion = async (sku: string, cantidad: number, token: string) => {
    const res = await fetch(`${BASE_URL}/cambio?sku=${sku}&cantidad=${cantidad}`, {
        headers: {
            "Authorization": `Bearer ${token}`
        }
    });
    if (!res.ok) throw new Error("Error al obtener conversión");
    return res.json();
};