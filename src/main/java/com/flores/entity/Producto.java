package com.flores.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(schema = "VENTAS", name = "producto")
public class Producto {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PRODUCTO")
    @SequenceGenerator(sequenceName = "SEQ_PRODUCTO", allocationSize = 1, name = "SEQ_PRODUCTO")
	private Integer id;
	
	@NotNull
	@Size(max = 500)
	@Column(name = "nombre")
	private String nombre;
	
	@NotNull
	@Column(name = "precio", precision = 10, scale = 2)
	private double precio;
	
	@OneToMany(mappedBy = "producto")
	@JsonIgnoreProperties("producto")
	private Set<DetalleVenta> detalleVentas = new HashSet<>();
	
	public Producto() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Set<DetalleVenta> getDetalleVentas() {
		return detalleVentas;
	}

	public void setDetalleVentas(Set<DetalleVenta> detalleVentas) {
		this.detalleVentas = detalleVentas;
	}
}
