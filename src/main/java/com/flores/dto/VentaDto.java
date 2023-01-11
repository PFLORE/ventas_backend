package com.flores.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class VentaDto {

	private Integer id;
	private Date fecha;
	private ClienteDto cliente;
	@JsonIgnoreProperties("venta")
	private List<DetalleVentaDto> detalleVentas;
	private Double total;
	
	public VentaDto() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public ClienteDto getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDto cliente) {
		this.cliente = cliente;
	}

	public List<DetalleVentaDto> getDetalleVentas() {
		return detalleVentas;
	}

	public void setDetalleVentas(List<DetalleVentaDto> detalleVentas) {
		this.detalleVentas = detalleVentas;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}
}
