package com.flores.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flores.dto.VentaDto;
import com.flores.entity.Venta;
import com.flores.repository.VentaRepository;
import com.flores.service.VentaService;

@Service
public class VentaImplService implements VentaService {

	@Autowired
	private VentaRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public Venta crear(Venta venta) {
		Venta entity = repository.save(venta);
		return entity;
	}

	@Override
	public List<Venta> listar() {
		List<Venta> lista = repository.findAll();
		return lista;
	}
	
	@Override
	public List<Venta> buscarPorFecha(String fecha) {
		List<Venta> lista = repository.findByFecha(fecha);
		return lista;
	}

	@Override
	public Optional<Venta> obtenerPorId(Integer id) {
		Optional<Venta> entity = repository.findById(id);
		return entity;
	}
	
	@Override
	public Venta convertToEntity(VentaDto post) {
		Venta entity = modelMapper.map(post, Venta.class);
		return entity;
	}

	@Override
	public VentaDto convertToDto(Venta post) {
		VentaDto dto = modelMapper.map(post, VentaDto.class);
		return dto;
	}

}
