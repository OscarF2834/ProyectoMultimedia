package com.example.mongodb.proyecto.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection = "empleado")
public class Empleado {
	
	 @Id
	 private String id;
	 private String user;
	 private String password;
	 private String identificacion;
	 private String nombre;
	 private String apellido;
	 private String telefono;
	 private String cargo;
	 private int salario;
	 private String oficios;
	 
	 
	public Empleado() {
		// TODO Auto-generated constructor stub
	}


	public Empleado(String id, String user, String password, String identificacion, String nombre, String apellido,
			String telefono, String cargo, int salario, String oficios) {
		super();
		this.id = id;
		this.user = user;
		this.password = password;
		this.identificacion = identificacion;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.cargo = cargo;
		this.salario = salario;
		this.oficios = oficios;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getIdentificacion() {
		return identificacion;
	}


	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public String getCargo() {
		return cargo;
	}


	public void setCargo(String cargo) {
		this.cargo = cargo;
	}


	public int getSalario() {
		return salario;
	}


	public void setSalario(int salario) {
		this.salario = salario;
	}


	public String getOficios() {
		return oficios;
	}


	public void setOficios(String oficios) {
		this.oficios = oficios;
	}
	 
	 

}
