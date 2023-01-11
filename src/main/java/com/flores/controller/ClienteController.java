package com.flores.controller;

import java.util.LinkedList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flores.dto.ClienteDto;
import com.flores.dto.MensajeDto;
import com.flores.entity.Cliente;
import com.flores.service.ClienteService;
import com.flores.util.Utilidades;

@RestController
@RequestMapping(path = "cliente")
public class ClienteController {

	@Autowired
	private ClienteService service;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping(path = "/listar")
	public ResponseEntity<?> listar() {
		List<ClienteDto> clientes = new LinkedList<>();
		
		service.listar().stream().map(service::convertToDto)
			.forEach(clientes::add);
		
		if(clientes.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity(new MensajeDto("Datos encontrados", clientes), HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping(path = "/crear")
	public ResponseEntity<?> crear(@Valid @RequestBody ClienteDto post, BindingResult result) {
		
		try {
			
			if (result.hasErrors()) {
	            return Utilidades.validarCampos(result);
	        }
			
			Cliente entity = service.convertToEntity(post);
			
			Cliente cliente = service.crear(entity);
			
			ClienteDto dto = service.convertToDto(cliente);
			
			return new ResponseEntity(new MensajeDto("Cliente creado correctamente", dto), 
					HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
