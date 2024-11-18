package com.example.mongodb.proyecto.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pedidos")
public class Pedidos {

    @Id
    private String id;
    private String fechapedido;
    private String observaciones;
    private String clienteId; // Referencia al cliente
    private String productoId; // Referencia al producto
    private String clienteNombre; // Añadir campo para el nombre del cliente
    private String productoNombre; // Añadir campo para el nombre del producto

    // Getters y setters
    public String getClienteNombre() {
        return clienteNombre;
    }


    public String getProductoNombre() {
        return productoNombre;
    }

    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFechapedido() {
        return fechapedido;
    }

    public void setFechapedido(String fechapedido) {
        this.fechapedido = fechapedido;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getClienteId() {
        return clienteId;
    }


    public String getProductoId() {
        return productoId;
    }

}
