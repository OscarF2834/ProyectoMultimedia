package com.example.mongodb.proyecto.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection = "pedidos")
public class Pedidos {

	@Id
	private String id;
	private String fechapedido;
	private String observaciones;
	
	public Pedidos() {
		// TODO Auto-generated constructor stub
	}

	public Pedidos(String id, String fechapedido, String observaciones) {
		super();
		this.id = id;
		this.fechapedido = fechapedido;
		this.observaciones = observaciones;
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
	
	
	
}
