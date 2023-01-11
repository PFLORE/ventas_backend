package com.flores.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = Include.NON_NULL)
public class DetalleVentaDto {

	private Integer id;
	@JsonIgnoreProperties("detalleVentas")
	private VentaDto venta;
	private ProductoDto producto;
	private Integer cantidad;
	private Double subTotal;
	
	public DetalleVentaDto() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public VentaDto getVenta() {
		return venta;
	}

	public void setVenta(VentaDto venta) {
		this.venta = venta;
	}

	public ProductoDto getProducto() {
		return producto;
	}

	public void setProducto(ProductoDto producto) {
		this.producto = producto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}
}
