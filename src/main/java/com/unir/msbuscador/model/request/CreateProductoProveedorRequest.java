package com.unir.msbuscador.model.request;

import com.unir.msbuscador.model.pojo.Proveedor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductoProveedorRequest {
	private String nombre;
	private String codigo;
	private Double precio;
	private int cantidad;
	private Proveedor idProveedor;
}
