package org.itson.catalogoproductos;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * interfaz que declara los métodos que pueden llamarse
 * </pre>
 */
@io.quarkus.Generated(value = "by gRPC proto compiler (version 1.65.1)", comments = "Source: producto.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class CatalogoServiceGrpc {

    private CatalogoServiceGrpc() {
    }

    public static final java.lang.String SERVICE_NAME = "producto.CatalogoService";

    // Static method descriptors that strictly reflect the proto.
    private static volatile io.grpc.MethodDescriptor<org.itson.catalogoproductos.ProductoRequest, org.itson.catalogoproductos.ProductoResponse> getVerificarYObtenerProductoMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/' + "VerificarYObtenerProducto", requestType = org.itson.catalogoproductos.ProductoRequest.class, responseType = org.itson.catalogoproductos.ProductoResponse.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<org.itson.catalogoproductos.ProductoRequest, org.itson.catalogoproductos.ProductoResponse> getVerificarYObtenerProductoMethod() {
        io.grpc.MethodDescriptor<org.itson.catalogoproductos.ProductoRequest, org.itson.catalogoproductos.ProductoResponse> getVerificarYObtenerProductoMethod;
        if ((getVerificarYObtenerProductoMethod = CatalogoServiceGrpc.getVerificarYObtenerProductoMethod) == null) {
            synchronized (CatalogoServiceGrpc.class) {
                if ((getVerificarYObtenerProductoMethod = CatalogoServiceGrpc.getVerificarYObtenerProductoMethod) == null) {
                    CatalogoServiceGrpc.getVerificarYObtenerProductoMethod = getVerificarYObtenerProductoMethod = io.grpc.MethodDescriptor.<org.itson.catalogoproductos.ProductoRequest, org.itson.catalogoproductos.ProductoResponse>newBuilder().setType(io.grpc.MethodDescriptor.MethodType.UNARY).setFullMethodName(generateFullMethodName(SERVICE_NAME, "VerificarYObtenerProducto")).setSampledToLocalTracing(true).setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(org.itson.catalogoproductos.ProductoRequest.getDefaultInstance())).setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(org.itson.catalogoproductos.ProductoResponse.getDefaultInstance())).setSchemaDescriptor(new CatalogoServiceMethodDescriptorSupplier("VerificarYObtenerProducto")).build();
                }
            }
        }
        return getVerificarYObtenerProductoMethod;
    }

    private static volatile io.grpc.MethodDescriptor<org.itson.catalogoproductos.CatalogoRequest, org.itson.catalogoproductos.ProductoResponse> getObtenerCatalogoMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/' + "ObtenerCatalogo", requestType = org.itson.catalogoproductos.CatalogoRequest.class, responseType = org.itson.catalogoproductos.ProductoResponse.class, methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
    public static io.grpc.MethodDescriptor<org.itson.catalogoproductos.CatalogoRequest, org.itson.catalogoproductos.ProductoResponse> getObtenerCatalogoMethod() {
        io.grpc.MethodDescriptor<org.itson.catalogoproductos.CatalogoRequest, org.itson.catalogoproductos.ProductoResponse> getObtenerCatalogoMethod;
        if ((getObtenerCatalogoMethod = CatalogoServiceGrpc.getObtenerCatalogoMethod) == null) {
            synchronized (CatalogoServiceGrpc.class) {
                if ((getObtenerCatalogoMethod = CatalogoServiceGrpc.getObtenerCatalogoMethod) == null) {
                    CatalogoServiceGrpc.getObtenerCatalogoMethod = getObtenerCatalogoMethod = io.grpc.MethodDescriptor.<org.itson.catalogoproductos.CatalogoRequest, org.itson.catalogoproductos.ProductoResponse>newBuilder().setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING).setFullMethodName(generateFullMethodName(SERVICE_NAME, "ObtenerCatalogo")).setSampledToLocalTracing(true).setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(org.itson.catalogoproductos.CatalogoRequest.getDefaultInstance())).setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(org.itson.catalogoproductos.ProductoResponse.getDefaultInstance())).setSchemaDescriptor(new CatalogoServiceMethodDescriptorSupplier("ObtenerCatalogo")).build();
                }
            }
        }
        return getObtenerCatalogoMethod;
    }

    /**
     * Creates a new async stub that supports all call types for the service
     */
    public static CatalogoServiceStub newStub(io.grpc.Channel channel) {
        io.grpc.stub.AbstractStub.StubFactory<CatalogoServiceStub> factory = new io.grpc.stub.AbstractStub.StubFactory<CatalogoServiceStub>() {

            @java.lang.Override
            public CatalogoServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
                return new CatalogoServiceStub(channel, callOptions);
            }
        };
        return CatalogoServiceStub.newStub(factory, channel);
    }

    /**
     * Creates a new blocking-style stub that supports unary and streaming output calls on the service
     */
    public static CatalogoServiceBlockingStub newBlockingStub(io.grpc.Channel channel) {
        io.grpc.stub.AbstractStub.StubFactory<CatalogoServiceBlockingStub> factory = new io.grpc.stub.AbstractStub.StubFactory<CatalogoServiceBlockingStub>() {

            @java.lang.Override
            public CatalogoServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
                return new CatalogoServiceBlockingStub(channel, callOptions);
            }
        };
        return CatalogoServiceBlockingStub.newStub(factory, channel);
    }

    /**
     * Creates a new ListenableFuture-style stub that supports unary calls on the service
     */
    public static CatalogoServiceFutureStub newFutureStub(io.grpc.Channel channel) {
        io.grpc.stub.AbstractStub.StubFactory<CatalogoServiceFutureStub> factory = new io.grpc.stub.AbstractStub.StubFactory<CatalogoServiceFutureStub>() {

            @java.lang.Override
            public CatalogoServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
                return new CatalogoServiceFutureStub(channel, callOptions);
            }
        };
        return CatalogoServiceFutureStub.newStub(factory, channel);
    }

    /**
     * <pre>
     * interfaz que declara los métodos que pueden llamarse
     * </pre>
     */
    public interface AsyncService {

        /**
         */
        default void verificarYObtenerProducto(org.itson.catalogoproductos.ProductoRequest request, io.grpc.stub.StreamObserver<org.itson.catalogoproductos.ProductoResponse> responseObserver) {
            io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getVerificarYObtenerProductoMethod(), responseObserver);
        }

        /**
         */
        default void obtenerCatalogo(org.itson.catalogoproductos.CatalogoRequest request, io.grpc.stub.StreamObserver<org.itson.catalogoproductos.ProductoResponse> responseObserver) {
            io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getObtenerCatalogoMethod(), responseObserver);
        }
    }

    /**
     * Base class for the server implementation of the service CatalogoService.
     * <pre>
     * interfaz que declara los métodos que pueden llamarse
     * </pre>
     */
    public static abstract class CatalogoServiceImplBase implements io.grpc.BindableService, AsyncService {

        @java.lang.Override
        public io.grpc.ServerServiceDefinition bindService() {
            return CatalogoServiceGrpc.bindService(this);
        }
    }

    /**
     * A stub to allow clients to do asynchronous rpc calls to service CatalogoService.
     * <pre>
     * interfaz que declara los métodos que pueden llamarse
     * </pre>
     */
    public static class CatalogoServiceStub extends io.grpc.stub.AbstractAsyncStub<CatalogoServiceStub> {

        private CatalogoServiceStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @java.lang.Override
        protected CatalogoServiceStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new CatalogoServiceStub(channel, callOptions);
        }

        /**
         */
        public void verificarYObtenerProducto(org.itson.catalogoproductos.ProductoRequest request, io.grpc.stub.StreamObserver<org.itson.catalogoproductos.ProductoResponse> responseObserver) {
            io.grpc.stub.ClientCalls.asyncUnaryCall(getChannel().newCall(getVerificarYObtenerProductoMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         */
        public void obtenerCatalogo(org.itson.catalogoproductos.CatalogoRequest request, io.grpc.stub.StreamObserver<org.itson.catalogoproductos.ProductoResponse> responseObserver) {
            io.grpc.stub.ClientCalls.asyncServerStreamingCall(getChannel().newCall(getObtenerCatalogoMethod(), getCallOptions()), request, responseObserver);
        }
    }

    /**
     * A stub to allow clients to do synchronous rpc calls to service CatalogoService.
     * <pre>
     * interfaz que declara los métodos que pueden llamarse
     * </pre>
     */
    public static class CatalogoServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<CatalogoServiceBlockingStub> {

        private CatalogoServiceBlockingStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @java.lang.Override
        protected CatalogoServiceBlockingStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new CatalogoServiceBlockingStub(channel, callOptions);
        }

        /**
         */
        public org.itson.catalogoproductos.ProductoResponse verificarYObtenerProducto(org.itson.catalogoproductos.ProductoRequest request) {
            return io.grpc.stub.ClientCalls.blockingUnaryCall(getChannel(), getVerificarYObtenerProductoMethod(), getCallOptions(), request);
        }

        /**
         */
        public java.util.Iterator<org.itson.catalogoproductos.ProductoResponse> obtenerCatalogo(org.itson.catalogoproductos.CatalogoRequest request) {
            return io.grpc.stub.ClientCalls.blockingServerStreamingCall(getChannel(), getObtenerCatalogoMethod(), getCallOptions(), request);
        }
    }

    /**
     * A stub to allow clients to do ListenableFuture-style rpc calls to service CatalogoService.
     * <pre>
     * interfaz que declara los métodos que pueden llamarse
     * </pre>
     */
    public static class CatalogoServiceFutureStub extends io.grpc.stub.AbstractFutureStub<CatalogoServiceFutureStub> {

        private CatalogoServiceFutureStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @java.lang.Override
        protected CatalogoServiceFutureStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new CatalogoServiceFutureStub(channel, callOptions);
        }

        /**
         */
        public com.google.common.util.concurrent.ListenableFuture<org.itson.catalogoproductos.ProductoResponse> verificarYObtenerProducto(org.itson.catalogoproductos.ProductoRequest request) {
            return io.grpc.stub.ClientCalls.futureUnaryCall(getChannel().newCall(getVerificarYObtenerProductoMethod(), getCallOptions()), request);
        }
    }

    private static final int METHODID_VERIFICAR_YOBTENER_PRODUCTO = 0;

    private static final int METHODID_OBTENER_CATALOGO = 1;

    private static final class MethodHandlers<Req, Resp> implements io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>, io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>, io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>, io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {

        private final AsyncService serviceImpl;

        private final int methodId;

        MethodHandlers(AsyncService serviceImpl, int methodId) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch(methodId) {
                case METHODID_VERIFICAR_YOBTENER_PRODUCTO:
                    serviceImpl.verificarYObtenerProducto((org.itson.catalogoproductos.ProductoRequest) request, (io.grpc.stub.StreamObserver<org.itson.catalogoproductos.ProductoResponse>) responseObserver);
                    break;
                case METHODID_OBTENER_CATALOGO:
                    serviceImpl.obtenerCatalogo((org.itson.catalogoproductos.CatalogoRequest) request, (io.grpc.stub.StreamObserver<org.itson.catalogoproductos.ProductoResponse>) responseObserver);
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public io.grpc.stub.StreamObserver<Req> invoke(io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch(methodId) {
                default:
                    throw new AssertionError();
            }
        }
    }

    public static io.grpc.ServerServiceDefinition bindService(AsyncService service) {
        return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor()).addMethod(getVerificarYObtenerProductoMethod(), io.grpc.stub.ServerCalls.asyncUnaryCall(new MethodHandlers<org.itson.catalogoproductos.ProductoRequest, org.itson.catalogoproductos.ProductoResponse>(service, METHODID_VERIFICAR_YOBTENER_PRODUCTO))).addMethod(getObtenerCatalogoMethod(), io.grpc.stub.ServerCalls.asyncServerStreamingCall(new MethodHandlers<org.itson.catalogoproductos.CatalogoRequest, org.itson.catalogoproductos.ProductoResponse>(service, METHODID_OBTENER_CATALOGO))).build();
    }

    private static abstract class CatalogoServiceBaseDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {

        CatalogoServiceBaseDescriptorSupplier() {
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
            return org.itson.catalogoproductos.Producto.getDescriptor();
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
            return getFileDescriptor().findServiceByName("CatalogoService");
        }
    }

    private static final class CatalogoServiceFileDescriptorSupplier extends CatalogoServiceBaseDescriptorSupplier {

        CatalogoServiceFileDescriptorSupplier() {
        }
    }

    private static final class CatalogoServiceMethodDescriptorSupplier extends CatalogoServiceBaseDescriptorSupplier implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {

        private final java.lang.String methodName;

        CatalogoServiceMethodDescriptorSupplier(java.lang.String methodName) {
            this.methodName = methodName;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
            return getServiceDescriptor().findMethodByName(methodName);
        }
    }

    private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

    public static io.grpc.ServiceDescriptor getServiceDescriptor() {
        io.grpc.ServiceDescriptor result = serviceDescriptor;
        if (result == null) {
            synchronized (CatalogoServiceGrpc.class) {
                result = serviceDescriptor;
                if (result == null) {
                    serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME).setSchemaDescriptor(new CatalogoServiceFileDescriptorSupplier()).addMethod(getVerificarYObtenerProductoMethod()).addMethod(getObtenerCatalogoMethod()).build();
                }
            }
        }
        return result;
    }
}
