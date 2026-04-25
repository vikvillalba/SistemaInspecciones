package org.itson.catalogodefectos;

import io.quarkus.grpc.GrpcService;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import java.util.List;

/**
 *
 * @author victoria
 */
@GrpcService
public class CatalogoDefectosImpl implements DefectosService {

    @Override
    @Blocking
    public Uni<DefectoResponse> obtenerDefecto(DefectoRequest request) {
        DefectoEntity defecto = DefectoEntity.findByClave(request.getCodigoDefecto());

        if (defecto == null) {
            return Uni.createFrom().failure(new RuntimeException("El defecto solicitado no fue encontrado."));
        }

        DefectoResponse response = DefectoResponse.newBuilder()
                .setCodigo(defecto.clave)
                .setNombre(defecto.nombre)
                .setGravedad(Gravedad.forNumber(defecto.tipoDefectoValue))
                .build();

        return Uni.createFrom().item(response);
    }

    @Override
    @Blocking
    public Multi<DefectoResponse> obtenerDefectosPorGravedad(GravedadRequest request) {

        PanacheQuery<DefectoEntity> query;
        if (request.getNivel() == Gravedad.TODOS) {
            query = DefectoEntity.findAll();
        } else {
            query = DefectoEntity.find("tipoDefectoValue", request.getNivelValue());
        }

        return Multi.createFrom().items(query.stream())
                .onItem().transform((DefectoEntity defecto) -> {
                    return DefectoResponse.newBuilder()
                            .setCodigo(defecto.clave)
                            .setNombre(defecto.nombre)
                            .setGravedadValue(defecto.tipoDefectoValue)
                            .build();
                });

    }

}
