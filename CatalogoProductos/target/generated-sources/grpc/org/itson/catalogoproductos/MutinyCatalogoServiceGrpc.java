package org.itson.catalogoproductos;

import static org.itson.catalogoproductos.CatalogoServiceGrpc.getServiceDescriptor;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;

@jakarta.annotation.Generated(value = "by Mutiny Grpc generator", comments = "Source: producto.proto")
public final class MutinyCatalogoServiceGrpc implements io.quarkus.grpc.MutinyGrpc {

    private MutinyCatalogoServiceGrpc() {
    }

    public static MutinyCatalogoServiceStub newMutinyStub(io.grpc.Channel channel) {
        return new MutinyCatalogoServiceStub(channel);
    }

    /**
     * <pre>
     *  interfaz que declara los métodos que pueden llamarse
     * </pre>
     */
    public static class MutinyCatalogoServiceStub extends io.grpc.stub.AbstractStub<MutinyCatalogoServiceStub> implements io.quarkus.grpc.MutinyStub {

        private CatalogoServiceGrpc.CatalogoServiceStub delegateStub;

        private MutinyCatalogoServiceStub(io.grpc.Channel channel) {
            super(channel);
            delegateStub = CatalogoServiceGrpc.newStub(channel);
        }

        private MutinyCatalogoServiceStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
            delegateStub = CatalogoServiceGrpc.newStub(channel).build(channel, callOptions);
        }

        @Override
        protected MutinyCatalogoServiceStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new MutinyCatalogoServiceStub(channel, callOptions);
        }

        public io.smallrye.mutiny.Uni<org.itson.catalogoproductos.ProductoResponse> verificarYObtenerProducto(org.itson.catalogoproductos.ProductoRequest request) {
            return io.quarkus.grpc.stubs.ClientCalls.oneToOne(request, delegateStub::verificarYObtenerProducto);
        }

        public io.smallrye.mutiny.Multi<org.itson.catalogoproductos.ProductoResponse> obtenerCatalogo(org.itson.catalogoproductos.CatalogoRequest request) {
            return io.quarkus.grpc.stubs.ClientCalls.oneToMany(request, delegateStub::obtenerCatalogo);
        }
    }

    /**
     * <pre>
     *  interfaz que declara los métodos que pueden llamarse
     * </pre>
     */
    public static abstract class CatalogoServiceImplBase implements io.grpc.BindableService {

        private String compression;

        /**
         * Set whether the server will try to use a compressed response.
         *
         * @param compression the compression, e.g {@code gzip}
         */
        public CatalogoServiceImplBase withCompression(String compression) {
            this.compression = compression;
            return this;
        }

        public io.smallrye.mutiny.Uni<org.itson.catalogoproductos.ProductoResponse> verificarYObtenerProducto(org.itson.catalogoproductos.ProductoRequest request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public io.smallrye.mutiny.Multi<org.itson.catalogoproductos.ProductoResponse> obtenerCatalogo(org.itson.catalogoproductos.CatalogoRequest request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        @java.lang.Override
        public io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor()).addMethod(org.itson.catalogoproductos.CatalogoServiceGrpc.getVerificarYObtenerProductoMethod(), asyncUnaryCall(new MethodHandlers<org.itson.catalogoproductos.ProductoRequest, org.itson.catalogoproductos.ProductoResponse>(this, METHODID_VERIFICAR_YOBTENER_PRODUCTO, compression))).addMethod(org.itson.catalogoproductos.CatalogoServiceGrpc.getObtenerCatalogoMethod(), asyncServerStreamingCall(new MethodHandlers<org.itson.catalogoproductos.CatalogoRequest, org.itson.catalogoproductos.ProductoResponse>(this, METHODID_OBTENER_CATALOGO, compression))).build();
        }
    }

    private static final int METHODID_VERIFICAR_YOBTENER_PRODUCTO = 0;

    private static final int METHODID_OBTENER_CATALOGO = 1;

    private static final class MethodHandlers<Req, Resp> implements io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>, io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>, io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>, io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {

        private final CatalogoServiceImplBase serviceImpl;

        private final int methodId;

        private final String compression;

        MethodHandlers(CatalogoServiceImplBase serviceImpl, int methodId, String compression) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
            this.compression = compression;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch(methodId) {
                case METHODID_VERIFICAR_YOBTENER_PRODUCTO:
                    io.quarkus.grpc.stubs.ServerCalls.oneToOne((org.itson.catalogoproductos.ProductoRequest) request, (io.grpc.stub.StreamObserver<org.itson.catalogoproductos.ProductoResponse>) responseObserver, compression, serviceImpl::verificarYObtenerProducto);
                    break;
                case METHODID_OBTENER_CATALOGO:
                    io.quarkus.grpc.stubs.ServerCalls.oneToMany((org.itson.catalogoproductos.CatalogoRequest) request, (io.grpc.stub.StreamObserver<org.itson.catalogoproductos.ProductoResponse>) responseObserver, compression, serviceImpl::obtenerCatalogo);
                    break;
                default:
                    throw new java.lang.AssertionError();
            }
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public io.grpc.stub.StreamObserver<Req> invoke(io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch(methodId) {
                default:
                    throw new java.lang.AssertionError();
            }
        }
    }
}
