/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.servicioinspecciones;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;
 
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Publica eventos de defecto en el broker RabbitMQ cada vez que se registra un
 * reporte de inspección.
 *
 * @author erika
 */
@ApplicationScoped
public class PublicadorEventos {
 
    private static final Logger LOG = Logger.getLogger(PublicadorEventos.class.getName());
    private static final String EXCHANGE    = "defectos.exchange";
    private static final String ROUTING_KEY = "defecto.%s";
 
    @ConfigProperty(name = "rabbitmq.host",     defaultValue = "localhost")
    String host;
 
    @ConfigProperty(name = "rabbitmq.port",     defaultValue = "5672")
    int port;
 
    @ConfigProperty(name = "rabbitmq.username", defaultValue = "guest")
    String username;
 
    @ConfigProperty(name = "rabbitmq.password", defaultValue = "guest")
    String password;
 
    private ConnectionFactory factory;
    private final ObjectMapper mapper = new ObjectMapper();
 
    @PostConstruct
    void init() {
        factory = new ConnectionFactory();
        factory.setHost(host);
        factory.setPort(port);
        factory.setUsername(username);
        factory.setPassword(password);
        LOG.info("PublicadorEventos configurado → " + host + ":" + port);
    }
 
    public void publicarDefecto(
            ReporteEntity entity,
            org.itson.catalogodefectos.DefectoResponse defecto) {
 
        try (Connection conn = factory.newConnection();
             Channel channel = conn.createChannel()) {
 
            ObjectNode payload = mapper.createObjectNode();
            payload.put("id_reporte",         entity.id);
            payload.put("fecha_hora",         formatearFecha(entity.fechaHora));
            payload.put("nombre_inspector",   entity.nombreInspector);
            payload.put("sku_producto",       entity.skuProducto);
            payload.put("codigo_defecto",     entity.codigoDefecto);
            payload.put("nombre_defecto",     defecto.getNombre());
            payload.put("gravedad_defecto",   defecto.getGravedad().name());
            payload.put("lote_producto",      entity.lote);
            payload.put("tamano_muestra",     entity.tamanoMuestra);
            payload.put("cantidad_rechazada", entity.cantidadRechazada);
            payload.put("comentarios",        entity.comentarios != null ? entity.comentarios : "");
 
            byte[] body = mapper.writeValueAsBytes(payload);
 
            String gravedad   = defecto.getGravedad().name().toLowerCase();
            String routingKey = String.format(ROUTING_KEY, gravedad);
 
            AMQP.BasicProperties props = new AMQP.BasicProperties.Builder()
                    .contentType("application/json")
                    .deliveryMode(2)
                    .build();
 
            channel.basicPublish(EXCHANGE, routingKey, props, body);
 
            LOG.info(String.format("✓ Evento publicado — routing_key=%s id_reporte=%s",
                    routingKey, entity.id));
 
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "✗ Error al publicar en broker: " + e.getMessage(), e);
        }
    }
 
    private String formatearFecha(Instant instant) {
        return DateTimeFormatter.ISO_INSTANT
                .withZone(ZoneOffset.UTC)
                .format(instant);
    }
}
 