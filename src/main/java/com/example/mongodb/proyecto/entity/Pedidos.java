package com.example.mongodb.proyecto.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection = "pedidos")
public class Pedidos {

	@Id
	private String id;
	private String fechapedido;
	
	public Pedidos() {
		// TODO Auto-generated constructor stub
	}

	public Pedidos(String id, String fechapedido) {
		super();
		this.id = id;
		this.fechapedido = fechapedido;
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
	
	
	
}
