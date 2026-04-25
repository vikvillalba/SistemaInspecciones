package org.itson.servicioinspecciones;

import io.quarkus.grpc.GrpcClient;
import io.quarkus.grpc.GrpcService;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.transaction.Transactional;
import org.itson.catalogodefectos.DefectoRequest;
import org.itson.catalogodefectos.DefectosService;
import org.itson.catalogoproductos.CatalogoService;
import org.itson.catalogoproductos.ProductoRequest;
import org.itson.servicioinspeccion.NuevoReporteRequest;
import org.itson.servicioinspeccion.ReporteRequest;
import org.itson.servicioinspeccion.ReporteResponse;
import org.itson.servicioinspeccion.ReportesService;

/**
 *
 * @author victoria
 */
@GrpcService
public class ServicioInspeccionesImpl implements ReportesService {

    @GrpcClient("catalogo-productos")
    CatalogoService productosClient;

    @GrpcClient("catalogo-defectos")
    DefectosService defectosClient;

@Override
    @Transactional
    public Uni<ReporteResponse> registrarReporte(NuevoReporteRequest request) {
        // vaalidar que el producto y el defecto si existen
        return Uni.combine().all().unis(
                productosClient.verificarYObtenerProducto(ProductoRequest.newBuilder().setSku(request.getSkuProducto()).build())
                        .onFailure().transform(t -> new io.grpc.StatusRuntimeException(io.grpc.Status.NOT_FOUND.withDescription("Producto no encontrado"))),
                defectosClient.obtenerDefecto(DefectoRequest.newBuilder().setCodigoDefecto(request.getCodigoDefecto()).build())
                        .onFailure().transform(t -> new io.grpc.StatusRuntimeException(io.grpc.Status.NOT_FOUND.withDescription("Defecto no encontrado")))
        ).asTuple().onItem().transformToUni(tuple -> {

            ReporteEntity entity = new ReporteEntity();
            entity.nombreInspector = request.getNombreInspector();
            entity.skuProducto = request.getSkuProducto();
            entity.codigoDefecto = request.getCodigoDefecto();
            entity.lote = request.getLoteProducto();
            entity.tamanoMuestra = request.getTamanoMuestra();
            entity.cantidadRechazada = request.getCantidadRechazada();
            entity.comentarios = request.getComentarios();
            entity.fechaHora = java.time.Instant.ofEpochSecond(request.getFechaHora().getSeconds());

            entity.persistAndFlush();
            return Uni.createFrom().item(entity);
        }).map(this::mapToResponse); 
    }

    @Override
    @Blocking
    public Uni<ReporteResponse> obtenerReporte(ReporteRequest request) {
        String idReporte = request.getIdReporte();
        
        return Uni.createFrom().item(() -> ReporteEntity.findById(idReporte))
            .onItem().ifNotNull().transform(entity -> mapToResponse((ReporteEntity) entity))
            .onItem().ifNull().failWith(() -> 
                new io.grpc.StatusRuntimeException(io.grpc.Status.NOT_FOUND
                    .withDescription("El reporte con ID " + idReporte + " no existe.")));
    }

    @Override
    @Blocking
    public Multi<ReporteResponse> obtenerReportes(ReporteRequest request) {
        PanacheQuery<ReporteEntity> query;
        
        switch(request.getFiltro()){
            case PRODUCTO:
                query = ReporteEntity.find("skuProducto", request.getSkuProducto());
                break;
            case LOTE:
                query = ReporteEntity.find("lote", request.getLoteProducto());
                break;
            case INSPECTOR:
                query = ReporteEntity.find("nombreInspector", request.getNombreInspector());
                break;
            case DEFECTO:
                query = ReporteEntity.find("codigoDefecto", request.getTipoDefecto());
                break;
            case TODOS:
            default:
                query = ReporteEntity.findAll();
                break;
        }
        
        return Multi.createFrom().items(() -> query.stream())
                .map(entity -> mapToResponse((ReporteEntity)entity));
    }

    private ReporteResponse mapToResponse(ReporteEntity reporte) {
        return ReporteResponse.newBuilder()
                .setIdReporte(reporte.id != null ? reporte.id : "0")
                .setNombreInspector(reporte.nombreInspector)
                .setSkuProducto(reporte.skuProducto)
                .setCodigoDefecto(reporte.codigoDefecto)
                .setLoteProducto(reporte.lote)
                .setTamanoMuestra(reporte.tamanoMuestra)
                .setCantidadRechazada(reporte.cantidadRechazada)
                .setComentarios(reporte.comentarios)
                .setFechaHora(com.google.protobuf.Timestamp.newBuilder()
                        .setSeconds(reporte.fechaHora.getEpochSecond())
                        .build())
                .build();
    }

}
