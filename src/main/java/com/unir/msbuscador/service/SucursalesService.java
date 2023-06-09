package com.unir.msbuscador.service;

import java.util.List;

import com.unir.msbuscador.model.pojo.Sucursal;
import com.unir.msbuscador.model.request.CreateSucursalRequest;

public interface SucursalesService {
	List<Sucursal> getSucursales();
	
	List<Sucursal> searchSucursales(String nombre);
	
	Sucursal getSucursal(long sucursalId);
	
	Boolean removeSucursal(long sucursalId);
	
	Sucursal createSucursal(CreateSucursalRequest request);
	
	Sucursal updateSucursal(long sucursalId, CreateSucursalRequest request);
}
