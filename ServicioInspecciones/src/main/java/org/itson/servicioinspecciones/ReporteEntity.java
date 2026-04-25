package org.itson.servicioinspecciones;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;

/**
 *
 * @author victoria
 */
@Entity
@Table(name = "reportes")
public class ReporteEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public String id;

    @Column(unique = true, nullable = false)
    public Instant fechaHora;
    public String nombreInspector;
    public String skuProducto;
    public String codigoDefecto;
    public int tamanoMuestra;
    public String lote;
    public int cantidadRechazada;
    public String comentarios;


}
