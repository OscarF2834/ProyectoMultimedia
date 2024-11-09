package com.example.mongodb.proyecto.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection = "reservas")
public class Reservas {
	
	@Id
	private String id;
	private String fechareserva;
	private String horareserva;
	private int numeropersonas;
	
	
	public Reservas() {
		// TODO Auto-generated constructor stub
	}


	public Reservas(String id, String fechareserva, String horareserva, int numeropersonas) {
		super();
		this.id = id;
		this.fechareserva = fechareserva;
		this.horareserva = horareserva;
		this.numeropersonas = numeropersonas;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getFechareserva() {
		return fechareserva;
	}


	public void setFechareserva(String fechareserva) {
		this.fechareserva = fechareserva;
	}


	public int getNumeropersonas() {
		return numeropersonas;
	}


	public void setNumeropersonas(int numeropersonas) {
		this.numeropersonas = numeropersonas;
	}


	public String getHorareserva() {
		return horareserva;
	}


	public void setHorareserva(String horareserva) {
		this.horareserva = horareserva;
	}
	
	

}
