package org.itson.catalogoproductos;

import io.quarkus.grpc.GrpcService;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import java.util.List;

/**
 *
 * @author victoria
 */

@GrpcService
public class CatalogoServiceImpl implements CatalogoService{

    @Override
    @Blocking
    public Uni<ProductoResponse> verificarYObtenerProducto(ProductoRequest request) {
        // obtener producto de la base de datos
        ProductoEntity producto = ProductoEntity.findBySku(request.getSku());
        
        // mensaje de error si no existe el producto solicitado
        if(producto == null){
            return Uni.createFrom().failure(new RuntimeException("El producto solicitado no fue encontrado."));
        }
        
        // mapea respuesta
        ProductoResponse response = ProductoResponse.newBuilder()
                .setSku(producto.sku)
                .setNombre(producto.nombre)
                .setCostoUnitario(producto.costoUnitario)
                .setDisponible(producto.disponible)
                .setTipo(TipoProducto.forNumber(producto.tipoProductoValue))
                .build();
        
        // regresa objeto
        return Uni.createFrom().item(response);
    }

    @Override
    @Blocking
    public Multi<ProductoResponse> obtenerCatalogo(CatalogoRequest request) {
        // obtiene todos los productos de la base de datos
        List<ProductoEntity> productos = ProductoEntity.listAll();
        
        // Multi para transmitir los productos uno por uno
        return Multi.createFrom().iterable(productos)
                .map(producto -> ProductoResponse.newBuilder()
                    .setSku(producto.sku)
                    .setNombre(producto.nombre)
                    .setCostoUnitario(producto.costoUnitario)
                    .setDisponible(producto.disponible)
                    .setTipo(TipoProducto.forNumber(producto.tipoProductoValue))
                    .build()
                );
                
    }
    
}
