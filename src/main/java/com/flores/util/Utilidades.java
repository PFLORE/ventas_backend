package com.flores.util;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.flores.dto.MensajeDto;

public class Utilidades {
	
	@SuppressWarnings({ "rawtypes"})
	public static ResponseEntity<?> validarCampos(BindingResult result) {
		MensajeDto objeto = new MensajeDto();
		result.getFieldErrors().forEach((a) -> {
			objeto.setMensaje("El campo " + a.getField() + " " + a.getDefaultMessage());
		});
		return ResponseEntity.badRequest().body(objeto);
	}
}
