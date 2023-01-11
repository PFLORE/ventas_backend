package com.flores.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.flores.dto.VentaDto;
import com.flores.entity.Venta;

@Service
public interface VentaService {

	Venta crear(Venta venta);
	
	List<Venta> listar();
	
	List<Venta> buscarPorFecha(String fecha);
	
	Optional<Venta> obtenerPorId(Integer id);
	
	Venta convertToEntity(VentaDto post);
	
	VentaDto convertToDto(Venta post);
}
