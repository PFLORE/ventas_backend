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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(schema = "VENTAS", name = "cliente")
public class Cliente {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CLIENTE")
    @SequenceGenerator(sequenceName = "SEQ_CLIENTE", allocationSize = 1, name = "SEQ_CLIENTE")
	private Integer id;
	
	@NotNull
	@Size(max = 500)
	@Column(name = "nombres")
	private String nombres;
	
	@NotNull
	@Size(max = 700)
	@Column(name = "apellidos")
	private String apellidos;
	
	@NotNull
	@Size(max = 8)
	@Column(name = "dni", unique = true)
	private String dni;
	
	@Size(max = 20)
	@Column(name = "telefono")
	private String telefono;
	
	@Email
	@Column(name = "email")
	private String email;
	
	@OneToMany(mappedBy = "cliente")
	@JsonIgnoreProperties("cliente")
	private Set<Venta> ventas = new HashSet<>();
	
	public Cliente() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Venta> getVentas() {
		return ventas;
	}

	public void setVentas(Set<Venta> ventas) {
		this.ventas = ventas;
	}
}
