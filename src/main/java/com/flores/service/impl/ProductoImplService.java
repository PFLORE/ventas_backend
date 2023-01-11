package com.flores.service.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.flores.dto.ProductoDto;
import com.flores.entity.Producto;
import com.flores.repository.ProductoRepository;
import com.flores.service.ProductoService;

@Service
public class ProductoImplService implements ProductoService {

	@Autowired
	private ProductoRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<Producto> listar(int numeroPagina, int tamanioPagina, String buscarPor, String ordenDir) {
		
		Sort sort = ordenDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(buscarPor).ascending()
				: Sort.by(buscarPor).descending();
		
		Pageable paging = PageRequest.of(numeroPagina, tamanioPagina, sort);
		
		Page<Producto> productos = repository.findAll(paging);
		
		List<Producto> lista = productos.getContent();
		
		return lista;
	}
	
	@Override
	public Optional<Producto> obtenerPorId(Integer id) {
		Optional<Producto> entity = repository.findById(id);
		return entity;
	}
	
	@Override
	public Producto crear(Producto producto) {
		Producto entity = repository.save(producto);
		return entity;
	}
	
	@Override
	public void eliminar(Integer id) {
		repository.deleteById(id);
	}

	@Override
	public Producto convertToEntity(ProductoDto post) {
		Producto entity = modelMapper.map(post, Producto.class);
		return entity;
	}

	@Override
	public ProductoDto convertToDto(Producto post) {
		ProductoDto dto = modelMapper.map(post, ProductoDto.class);
		return dto;
	}
}
