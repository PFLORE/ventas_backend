package com.flores.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(schema = "VENTAS", name = "detalle_venta")
public class DetalleVenta {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DETALLE_VENTA")
    @SequenceGenerator(sequenceName = "SEQ_DETALLE_VENTA", allocationSize = 1, name = "SEQ_DETALLE_VENTA")
	private Integer id;
	
	@NotNull
	@Column(name = "cantidad")
	private Integer cantidad;
	
	@ManyToOne
	@JoinColumn(name = "id_producto")
	@JsonIgnoreProperties("detalleVentas")
	private Producto producto;
	
	@ManyToOne
	@JoinColumn(name = "id_venta")
	@JsonIgnoreProperties("detalleVentas")
	private Venta venta;
	
	public DetalleVenta() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}
}
