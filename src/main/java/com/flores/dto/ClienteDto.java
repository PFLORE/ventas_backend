package com.flores.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class ClienteDto {

	private Integer id;
	
	@NotBlank(message = "es requerido")
	private String nombres;
	
	@NotBlank(message = "es requerido")
	private String apellidos;
	
	@NotBlank(message = "es requerido")
	@Pattern(regexp = "^[0-9]{8}$", 
		message = "debe tener una longitud de 8 caracteres y solo números")
	private String dni;
	
//	@Pattern(regexp = "^[0-9]", 
//			message = "debe contener solo números")
	private String telefono;
	
	@Email
	private String email;
	
	public ClienteDto() {}

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
}
