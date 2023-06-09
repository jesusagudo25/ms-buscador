package com.unir.msbuscador.service;

import java.util.List;

import com.unir.msbuscador.model.pojo.Proveedor;
import com.unir.msbuscador.model.request.CreateProveedorRequest;

public interface  ProveedoresService {
	
	List<Proveedor> getProveedores();
	
	List<Proveedor> searchProveedores(String nombre);
	
	Proveedor getProveedor(long proveedorId);
	
	Boolean removeProveedor(long proveedorId);
	
	Proveedor createProveedor(CreateProveedorRequest request);
	
	Proveedor updateProveedor(long proveedorId, CreateProveedorRequest request);
}
