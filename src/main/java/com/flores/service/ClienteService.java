package com.flores.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.flores.dto.ClienteDto;
import com.flores.entity.Cliente;

@Service
public interface ClienteService {

	List<Cliente> listar();
	
	Cliente crear(Cliente cliente);
	
	Cliente convertToEntity(ClienteDto post);
	
	ClienteDto convertToDto(Cliente post);
}
