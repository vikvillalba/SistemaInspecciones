package org.itson.catalogodefectos;

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
@Table(name = "defectos")
public class DefectoEntity extends PanacheEntityBase{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    
    @Column(unique = true, nullable = false)
    public String clave;
    public String nombre;
    public int tipoDefectoValue;
    
    public static DefectoEntity findByClave(String clave){
        return find("clave", clave).firstResult();
    }
    
    
}
