package com.flores.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.flores.dto.ProductoDto;
import com.flores.entity.Producto;

@Service
public interface ProductoService {

	List<Producto> listar(int numeroPagina, int tamanioPagina, String buscarPor, String ordenDir);
	
	Optional<Producto> obtenerPorId(Integer id);
	
	Producto crear(Producto producto);
	
	void eliminar(Integer id);
	
	Producto convertToEntity(ProductoDto post);
	
	ProductoDto convertToDto(Producto post);
}
