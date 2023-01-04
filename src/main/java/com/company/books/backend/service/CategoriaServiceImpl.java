package com.company.books.backend.service;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.books.backend.model.Categoria;
import com.company.books.backend.model.dao.ICategoriaDao;
import com.company.books.backend.response.CategoriaResponseRest;

@Service 
public class CategoriaServiceImpl implements ICategoriaService {

	private static final Logger log = LoggerFactory.getLogger(CategoriaServiceImpl.class);
	@Autowired
	private ICategoriaDao categoriaDao;
	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<CategoriaResponseRest> buscarCategoria() {
		log.info("inicio metodo buscarCategoria() ");
		CategoriaResponseRest response = new CategoriaResponseRest();
		try {
			
			List<Categoria> categoria=(List<Categoria>) categoriaDao.findAll();
			response.getCategoriaResponse().setCategoria(categoria);
			response.setMetada("respuesta ok","00","respuesta exitosa ");
		}catch(Exception e) {
			response.setMetada("respuesta nok","-1","ERROR AL CONSULTAR CATEGORIAS ");
            log.error("ERROR AL CONSULTAR CATEGORIA ",e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<CategoriaResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<CategoriaResponseRest>(response,HttpStatus.OK);
	}
	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<CategoriaResponseRest> buscarPorId(Long id) {
		
		log.info("Inicio metodo buscarPorId");
		CategoriaResponseRest response=new CategoriaResponseRest();
		List<Categoria> list =new ArrayList<>();
		try {
			Optional<Categoria> categoria = categoriaDao.findById(id);
			if(categoria.isPresent()) {
				list.add((categoria.get()));
				response.getCategoriaResponse().setCategoria(list);
			}else {
				log.error("Error en consulta categoria");
				response.setMetada("Respuesta nok", "-1", "Categoria no encontrada");
				 return new ResponseEntity<CategoriaResponseRest>(response,HttpStatus.NOT_FOUND);
			}
		}catch(Exception e) {
			log.error("Error en consulta categoria");
			response.setMetada("Respuesta nok", "-1", "Error al consular categoria ");
			 return new ResponseEntity<CategoriaResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.setMetada("respuesta ok","00","respuesta exitosa ");

		return new ResponseEntity<CategoriaResponseRest>(response,HttpStatus.OK);

	}
	@Override
	@Transactional
	public ResponseEntity<CategoriaResponseRest> crear(Categoria categoria) {
		log.info("Inicio metodo crear categoria");
		CategoriaResponseRest response=new CategoriaResponseRest();
		List<Categoria> list =new ArrayList<>();
		try {
			Categoria CategoriaGuardada= categoriaDao.save(categoria);
			if(CategoriaGuardada != null) {
				list.add(CategoriaGuardada);
				response.getCategoriaResponse().setCategoria(list);
			}
			else {
				log.error("Error en grabar categoria");
				response.setMetada("Respuesta nok", "-1", "Categoria no guardada");
				 return new ResponseEntity<CategoriaResponseRest>(response,HttpStatus.BAD_REQUEST);
			}
		}catch(Exception e) {
			log.error("Error en grabar categoria");
			response.setMetada("Respuesta nok", "-1", "Error al grabar categoria ");
			 return new ResponseEntity<CategoriaResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.setMetada("respuesta ok","00","respuesta exitosa ");

		return new ResponseEntity<CategoriaResponseRest>(response,HttpStatus.OK);
	}
	@Override
	@Transactional
	public ResponseEntity<CategoriaResponseRest> actualizar(Categoria categoria, Long id) {
		log.info("Inicio metodo actualizar categoria");
		CategoriaResponseRest response=new CategoriaResponseRest();
		List<Categoria> list =new ArrayList<>();
		try {
			Optional<Categoria> categoriaBusqueda= categoriaDao.findById(id);
			if(categoriaBusqueda.isPresent()) {
				
			categoriaBusqueda.get().setNombre(categoria.getNombre());
			categoriaBusqueda.get().getDescripccion();

			Categoria CategoriaActulizar=categoriaDao.save(categoriaBusqueda.get());
			if(CategoriaActulizar != null) {
				response.setMetada("Respuesta ok", "00", "Categoria  actulizada ");

				list.add(CategoriaActulizar);
				response.getCategoriaResponse().setCategoria(list);
			}
			else {
				log.error("Error en  actulizar categoria");
				response.setMetada("Respuesta nok", "-1", "Categoria no actulizada ");
				 return new ResponseEntity<CategoriaResponseRest>(response,HttpStatus.BAD_REQUEST);
			}
			
			}else {
			log.error("Error en  actulizar categoria");
			response.setMetada("Respuesta nok", "-1", "Categoria no actulizada ");
			 return new ResponseEntity<CategoriaResponseRest>(response,HttpStatus.NOT_FOUND);
		}
		}
		catch(Exception e) {
			log.error("Error en  actulizar categoria",e.getMessage());
			e.getStackTrace();
			response.setMetada("Respuesta nok", "-1", "Categoria no actulizada ");
			 return new ResponseEntity<CategoriaResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.setMetada("respuesta ok","00","respuesta exitosa ");

		 return new ResponseEntity<CategoriaResponseRest>(response,HttpStatus.OK);
	
   
	
}
	@Override
	@Transactional
	public ResponseEntity<CategoriaResponseRest> eliminar(Long id) {
		log.error("Inicio metodo elimar categoria");
		CategoriaResponseRest response=new CategoriaResponseRest();
     try {
    	 categoriaDao.deleteById(id);
 		response.setMetada("respuesta ok","00","categoria eliminada ");

     }
     catch(Exception e) {
			log.error("Error en  eliminar categoria",e.getMessage());
			e.getStackTrace();
			response.setMetada("Respuesta nok", "-1", "Categoria no eliminada ");
			 return new ResponseEntity<CategoriaResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	 return new ResponseEntity<CategoriaResponseRest>(response,HttpStatus.OK);

}
}
