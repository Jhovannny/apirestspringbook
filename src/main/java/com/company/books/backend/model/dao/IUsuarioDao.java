package com.company.books.backend.model.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.company.books.backend.model.Usuario;

public interface IUsuarioDao  extends CrudRepository <Usuario,Long> {
	
	@Query("select u from Usuario u where u.nombreUsuario=?1")
	public Usuario findByNombreUsuarios(String nombreUsuario);
	public Usuario findByIdNombreUsuarioV2(String nombreUsuario);

}
