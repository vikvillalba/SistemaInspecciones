package org.itson.catalogoproductos;

import java.util.function.BiFunction;
import io.quarkus.grpc.MutinyClient;

@jakarta.annotation.Generated(value = "by Mutiny Grpc generator", comments = "Source: producto.proto")
public class CatalogoServiceClient implements CatalogoService, MutinyClient<MutinyCatalogoServiceGrpc.MutinyCatalogoServiceStub> {

    private final MutinyCatalogoServiceGrpc.MutinyCatalogoServiceStub stub;

    public CatalogoServiceClient(String name, io.grpc.Channel channel, BiFunction<String, MutinyCatalogoServiceGrpc.MutinyCatalogoServiceStub, MutinyCatalogoServiceGrpc.MutinyCatalogoServiceStub> stubConfigurator) {
        this.stub = stubConfigurator.apply(name, MutinyCatalogoServiceGrpc.newMutinyStub(channel));
    }

    private CatalogoServiceClient(MutinyCatalogoServiceGrpc.MutinyCatalogoServiceStub stub) {
        this.stub = stub;
    }

    public CatalogoServiceClient newInstanceWithStub(MutinyCatalogoServiceGrpc.MutinyCatalogoServiceStub stub) {
        return new CatalogoServiceClient(stub);
    }

    @Override
    public MutinyCatalogoServiceGrpc.MutinyCatalogoServiceStub getStub() {
        return stub;
    }

    @Override
    public io.smallrye.mutiny.Uni<org.itson.catalogoproductos.ProductoResponse> verificarYObtenerProducto(org.itson.catalogoproductos.ProductoRequest request) {
        return stub.verificarYObtenerProducto(request);
    }

    @Override
    public io.smallrye.mutiny.Multi<org.itson.catalogoproductos.ProductoResponse> obtenerCatalogo(org.itson.catalogoproductos.CatalogoRequest request) {
        return stub.obtenerCatalogo(request);
    }
}
