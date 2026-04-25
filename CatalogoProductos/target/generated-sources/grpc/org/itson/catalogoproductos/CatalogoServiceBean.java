package org.itson.catalogoproductos;

import io.grpc.BindableService;
import io.quarkus.grpc.GrpcService;
import io.quarkus.grpc.MutinyBean;

@jakarta.annotation.Generated(value = "by Mutiny Grpc generator", comments = "Source: producto.proto")
public class CatalogoServiceBean extends MutinyCatalogoServiceGrpc.CatalogoServiceImplBase implements BindableService, MutinyBean {

    private final CatalogoService delegate;

    CatalogoServiceBean(@GrpcService CatalogoService delegate) {
        this.delegate = delegate;
    }

    @Override
    public io.smallrye.mutiny.Uni<org.itson.catalogoproductos.ProductoResponse> verificarYObtenerProducto(org.itson.catalogoproductos.ProductoRequest request) {
        try {
            return delegate.verificarYObtenerProducto(request);
        } catch (UnsupportedOperationException e) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }
    }

    @Override
    public io.smallrye.mutiny.Multi<org.itson.catalogoproductos.ProductoResponse> obtenerCatalogo(org.itson.catalogoproductos.CatalogoRequest request) {
        try {
            return delegate.obtenerCatalogo(request);
        } catch (UnsupportedOperationException e) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }
    }
}
