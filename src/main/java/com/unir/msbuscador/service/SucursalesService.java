package com.unir.msbuscador.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.unir.msbuscador.data.SucursalRepository;
import com.unir.msbuscador.model.pojo.Sucursal;
import com.unir.msbuscador.model.request.CreateSucursalRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.unir.msbuscador.data.DataAccessRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class SucursalesService{

	private final DataAccessRepository repository;

	public List<Sucursal> getSucursales() {

		List<Sucursal> sucursales = repository.getSucursales();
		return sucursales.isEmpty() ? null : sucursales;
	}
	
	public List<Sucursal> searchSucursales(String nombre){
		List<Sucursal> sucursales = repository.findByNombreContainingSucursal(nombre);
		
		if (sucursales != null) {
			return sucursales;
		} else {
			return null;
		}
	}

	public Sucursal getSucursal(String sucursalId) {
		return repository.findSucursalById(sucursalId).orElse(null);
	}

	public Boolean removeSucursal(String sucursalId) {

		Sucursal sucursal = repository.findSucursalById(sucursalId).orElse(null);

		if (sucursal != null) {
			repository.deleteSucursal(sucursal);
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	public Sucursal createSucursal(CreateSucursalRequest request) {

		if (request != null && StringUtils.hasLength(request.getNombre().trim())
				&& StringUtils.hasLength(request.getDireccion().trim()) && StringUtils.hasLength(request.getTelefono().trim()) 
				&& StringUtils.hasLength(request.getCorreo().trim())) {

			Sucursal sucursal = Sucursal.builder().nombre(request.getNombre()).direccion(request.getDireccion())
					.telefono(request.getTelefono()).correo(request.getCorreo()).build();

			return repository.saveOrUpdateSucursal(sucursal);
		} else {
			return null;
		}
	}
	
	public Sucursal updateSucursal(String sucursalId, CreateSucursalRequest request) {
		Sucursal sucursal = repository.findSucursalById(sucursalId).orElse(null);
		
		/*Validar entrada*/
		if (sucursal != null) {
			
			if (request != null && StringUtils.hasLength(request.getNombre().trim())
					&& StringUtils.hasLength(request.getDireccion().trim()) && StringUtils.hasLength(request.getTelefono().trim()) 
					&& StringUtils.hasLength(request.getCorreo().trim())) {

				sucursal.setNombre(request.getNombre());
				sucursal.setDireccion(request.getDireccion());
				sucursal.setTelefono(request.getTelefono());
				sucursal.setCorreo(request.getCorreo());
				return  repository.saveOrUpdateSucursal(sucursal);
			}
			else {
				return null;
			}
		} else {
			return null;
		}
	}
}
