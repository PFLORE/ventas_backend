package com.flores.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flores.dto.DetalleVentaDto;
import com.flores.entity.DetalleVenta;
import com.flores.repository.DetalleVentaRepository;
import com.flores.service.DetalleVentaService;

@Service
public class DetalleVentaImplService implements DetalleVentaService {

	@Autowired
	private DetalleVentaRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<DetalleVenta> crear(List<DetalleVenta> detalleVentas) {
		
		List<DetalleVenta> lista = repository.saveAll(detalleVentas);
		
		return lista;
	}

	@Override
	public DetalleVenta convertToEntity(DetalleVentaDto post) {
		DetalleVenta entity = modelMapper.map(post, DetalleVenta.class);
		return entity;
	}

	@Override
	public DetalleVentaDto convertToDto(DetalleVenta post) {
		DetalleVentaDto dto = modelMapper.map(post, DetalleVentaDto.class);
		return dto;
	}

}
