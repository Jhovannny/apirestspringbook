package com.company.books.backend.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "Categoria")
public class Categoria implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2164553723990982332L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)

    private long id;
	private String nombre;
	private String descripccion;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripccion() {
		return descripccion;
	}
	public void setDescripccion(String descripccion) {
		this.descripccion = descripccion;
	}
	
	
	
}
