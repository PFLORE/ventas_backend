package com.flores.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flores.dto.DetalleVentaDto;
import com.flores.dto.MensajeDto;
import com.flores.dto.VentaDto;
import com.flores.entity.DetalleVenta;
import com.flores.entity.Venta;
import com.flores.service.DetalleVentaService;
import com.flores.service.VentaService;

@RestController
@RequestMapping(path = "venta")
@CrossOrigin("*")
public class VentaController {

	@Autowired
	private VentaService ventaService;
	
	@Autowired
	private DetalleVentaService detalleService;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping(path = "/crear")
	public ResponseEntity<?> crear(@RequestBody VentaDto post) {
		
		List<DetalleVentaDto> detalleDto = new ArrayList<>();
		
		List<DetalleVentaDto> detalleTDto = post.getDetalleVentas();
		
		post.setDetalleVentas(new ArrayList<>());
		
		Venta entity = ventaService.convertToEntity(post);
		
		Venta ventaInsert = ventaService.crear(entity);
		
		List<DetalleVenta> detalles = detalleTDto.stream()
				.map(detalleService::convertToEntity).collect(Collectors.toList());
		
		detalles.stream().forEach(a -> a.setVenta(ventaInsert));
				
		List<DetalleVenta> detalleInsert = detalleService.crear(detalles);
		detalleInsert.stream().map(detalleService::convertToDto).forEach(detalleDto::add);
		
		VentaDto ventaDto = ventaService.convertToDto(ventaInsert);
		detalleDto.stream().forEach(a -> a.setVenta(null));
		ventaDto.setDetalleVentas(detalleDto);
		
		detalleDto.stream().forEach(a -> a.setSubTotal(a.getCantidad()*a.getProducto().getPrecio()));
		
		Double total = detalleDto.stream()
				.mapToDouble(a -> {	return a.getSubTotal(); }).sum();
		
		ventaDto.setTotal(total);
		ventaDto.setDetalleVentas(new ArrayList<>());
		
		return new ResponseEntity(new MensajeDto("Venta registrada", ventaDto), HttpStatus.CREATED);
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping(path = "/listar")
	public ResponseEntity<?> listar(@RequestParam(required = false) String fecha) throws ParseException {
		
//		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		List<VentaDto> lista = new ArrayList<>();
		List<Venta> listaT = new ArrayList<>();
		
		
		
		if(fecha.equals("null")) {
			ventaService.listar().stream().forEach(listaT::add);
		} else {
//			Date fechaD = formato.parse(fecha);
//			java.sql.Date fechaConvertida = new java.sql.Date(fechaD.getTime());
//			System.out.println(fechaConvertida);
			ventaService.buscarPorFecha(fecha).stream().forEach(listaT::add);
		}
		
		if(listaT.size() == 0) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		
		listaT.stream().map(ventaService::convertToDto).forEach(lista::add);
	
		lista.forEach(a -> a.getDetalleVentas()
				.forEach(b -> b.setSubTotal(b.getCantidad()*b.getProducto().getPrecio())));
		
		lista.forEach(a -> a.setTotal(
					a.getDetalleVentas().stream()
						.mapToDouble(b -> {	return b.getSubTotal(); }).sum()
				));
		
		return new ResponseEntity(new MensajeDto("Listado existente", lista), HttpStatus.OK);
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping(path = "/obtener/{id}")
	public ResponseEntity<?> obtenerPorId(@PathVariable("id") Integer id) {
		
		VentaDto dto = ventaService.obtenerPorId(id).map(ventaService::convertToDto).orElse(null);
		
		if(dto != null) {
			dto.getDetalleVentas().forEach(b -> b.setSubTotal(b.getCantidad()*b.getProducto().getPrecio()));
			dto.setTotal(dto.getDetalleVentas().stream().mapToDouble(b -> {	return b.getSubTotal(); }).sum() );
			
			return new ResponseEntity(new MensajeDto("Registro encontrado", dto), HttpStatus.OK);
			
		} else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		
	}
}
