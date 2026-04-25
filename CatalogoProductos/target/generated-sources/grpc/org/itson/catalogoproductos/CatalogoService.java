package org.itson.catalogoproductos;

import io.quarkus.grpc.MutinyService;

@jakarta.annotation.Generated(value = "by Mutiny Grpc generator", comments = "Source: producto.proto")
public interface CatalogoService extends MutinyService {

    io.smallrye.mutiny.Uni<org.itson.catalogoproductos.ProductoResponse> verificarYObtenerProducto(org.itson.catalogoproductos.ProductoRequest request);

    io.smallrye.mutiny.Multi<org.itson.catalogoproductos.ProductoResponse> obtenerCatalogo(org.itson.catalogoproductos.CatalogoRequest request);
}
