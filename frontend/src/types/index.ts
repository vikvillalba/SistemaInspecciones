export interface Producto {
  sku: string;
  nombre: string;
  costo_unitario: number;
  tipo: string;
  disponible: boolean;
}

export interface Defecto {
  codigo: string;
  nombre: string;
  gravedad: string;
}

export interface NuevoReporte {
  nombreInspector: string;
  skuProducto: string;
  codigoDefecto: string;
  tamanoMuestra: number;
  loteProducto: string;
  cantidadRechazada: number;
  comentarios?: string;
}

export interface Reporte {
  id_reporte: string;
  fecha_hora: { seconds: string; nanos: number };
  nombre_inspector: string;
  sku_producto: string;
  codigo_defecto: string;
  tamano_muestra: number;
  lote_producto: string;
  cantidad_rechazada: number;
  comentarios: string;
}

export interface Conversion {
  total_mxn: number;
  total_usd: number;
  tasa_usada: number;
}