package com.flores.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(schema = "VENTAS", name = "venta")
public class Venta {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VENTA")
    @SequenceGenerator(sequenceName = "SEQ_VENTA", allocationSize = 1, name = "SEQ_VENTA")
	private Integer id;
	
	@NotNull
	@Column(name = "fecha")
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	@JsonIgnoreProperties("ventas")
	private Cliente cliente;
	
	@OneToMany(mappedBy = "venta")
	@JsonIgnoreProperties("venta")
	private Set<DetalleVenta> detalleVentas = new HashSet<>();
	
	public Venta() {}

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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Set<DetalleVenta> getDetalleVentas() {
		return detalleVentas;
	}

	public void setDetalleVentas(Set<DetalleVenta> detalleVentas) {
		this.detalleVentas = detalleVentas;
	}
}
