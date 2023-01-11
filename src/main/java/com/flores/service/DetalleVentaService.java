package com.flores.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.flores.dto.DetalleVentaDto;
import com.flores.entity.DetalleVenta;

@Service
public interface DetalleVentaService {

	List<DetalleVenta> crear(List<DetalleVenta> detalleVentas);
	
	DetalleVenta convertToEntity(DetalleVentaDto post);
	
	DetalleVentaDto convertToDto(DetalleVenta post);
}
