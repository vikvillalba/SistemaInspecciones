package org.itson.catalogoproductos;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author victoria
 */
@Entity
@Table(name = "productos")
public class ProductoEntity extends PanacheEntityBase {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id; // id interno en la bd
    
    @Column(unique = true, nullable = false)
    public String sku; // id publico
    public String nombre;
    public Double costoUnitario;
    public int tipoProductoValue; // guarda el enum del .proto
    public Boolean disponible;
    
    public static ProductoEntity findBySku(String sku){
        return find("sku", sku).firstResult();
    }
}
