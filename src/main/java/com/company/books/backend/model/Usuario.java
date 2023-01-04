package com.company.books.backend.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name="usuarios")
public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
   @GeneratedValue(strategy =GenerationType.IDENTITY)
	private long id;
	@Column(unique = true,length=  20)
   private String Nombre;
	@Column(length=  65)
	   private String password;
	private Boolean habilitado;
    @ManyToMany(fetch =FetchType.LAZY,cascade=CascadeType.ALL)
    @JoinTable(name="usuario_role",
    joinColumns = @JoinColumn(name="usuario_id"),
    inverseJoinColumns=@JoinColumn(name="role_id"),
    uniqueConstraints = {@UniqueConstraint(columnNames= {"usuario_id","role_id"})})
    private List<Role> roles;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getHabilitado() {
		return habilitado;
	}
	public void setHabilitado(Boolean habilitado) {
		this.habilitado = habilitado;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
    
}
