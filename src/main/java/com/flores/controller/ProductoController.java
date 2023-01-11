package com.flores.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flores.dto.MensajeDto;
import com.flores.dto.ProductoDto;
import com.flores.entity.Producto;
import com.flores.service.ProductoService;
import com.flores.util.Constantes;
import com.flores.util.Utilidades;

@RestController
@RequestMapping(path = "producto")
public class ProductoController {

	@Autowired
	private ProductoService service;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping(path = "/listar")
	public ResponseEntity<?> listarPaginado(
			@RequestParam(value = "numeroPagina", defaultValue = Constantes.DEFAULT_PAGE_NUMBER, required = false) int numeroPagina,
			@RequestParam(value = "tamanioPagina", defaultValue = Constantes.DEFAULT_PAGE_SIZE, required = false) int tamanioPagina,
			@RequestParam(value = "buscarPor", defaultValue = Constantes.DEFAULT_SORT_BY, required = false) String buscarPor,
            @RequestParam(value = "ordenDir", defaultValue = Constantes.DEFAULT_SORT_DIRECTION, required = false) String ordenDir) {
		
		List<ProductoDto> lista = new LinkedList<>();
		
		service.listar(numeroPagina, tamanioPagina, buscarPor, ordenDir)
			.stream().map(service::convertToDto)
			.forEach(lista::add);
		
		if(lista.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity(new MensajeDto("Datos encontrados", lista), HttpStatus.OK);
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping(path = "/crear")
	public ResponseEntity<?> crear(@RequestBody ProductoDto post, BindingResult result) {
		try {
			
			if (result.hasErrors()) {
	            return Utilidades.validarCampos(result);
	        }
			
			Producto entity = service.convertToEntity(post);
			
			Producto producto = service.crear(entity);
			
			ProductoDto dto = service.convertToDto(producto);
			
			return new ResponseEntity(new MensajeDto("Producto creado correctamente", dto), 
					HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PutMapping(path = "/actualizar/{id}")
	public ResponseEntity<?> actualizar(@Valid @RequestBody ProductoDto post, 
			@PathVariable("id") Integer id, BindingResult result) {
		
		if (result.hasErrors()) {
            return Utilidades.validarCampos(result);
        }
		
		Optional<Producto> entityOptional = service.obtenerPorId(id);
		
		if(entityOptional.isPresent()) {
			Producto entity = service.convertToEntity(post);
			Producto cliente = service.crear(entity);
			
			ProductoDto dto = service.convertToDto(cliente);
			
			return new ResponseEntity(new MensajeDto("Producto actualizado", dto), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@DeleteMapping(path = "/eliminar/{id}")
	public ResponseEntity<?> eliminar(@PathVariable("id") Integer id) {
		
		service.eliminar(id);
		
		return new ResponseEntity(new MensajeDto("Producto eliminado"), HttpStatus.OK);
	}
}
