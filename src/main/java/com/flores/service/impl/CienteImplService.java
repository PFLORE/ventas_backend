package com.flores.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flores.dto.ClienteDto;
import com.flores.entity.Cliente;
import com.flores.repository.ClienteRepository;
import com.flores.service.ClienteService;

@Service
public class CienteImplService implements ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<Cliente> listar() {
		List<Cliente> lista = repository.findAll();
		return lista;
	}
	
	@Override
	public Cliente crear(Cliente cliente) {
		Cliente entity = repository.save(cliente);
		return entity;
	}

	@Override
	public Cliente convertToEntity(ClienteDto post) {
		Cliente entity = modelMapper.map(post, Cliente.class);
		return entity;
	}

	@Override
	public ClienteDto convertToDto(Cliente post) {
		ClienteDto dto = modelMapper.map(post, ClienteDto.class);
		return dto;
	}
}
