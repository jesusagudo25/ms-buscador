package com.unir.msbuscador.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateSucursalRequest {
	private String nombre;
	private String direccion;
	private String telefono;
	private String correo;
}
