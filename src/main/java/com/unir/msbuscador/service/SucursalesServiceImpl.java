package com.unir.msbuscador.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.unir.msbuscador.data.SucursalRepository;
import com.unir.msbuscador.model.pojo.Sucursal;
import com.unir.msbuscador.model.request.CreateSucursalRequest;

@Service
public class SucursalesServiceImpl implements SucursalesService{

	@Autowired
	private SucursalRepository repository;

	@Override
	public List<Sucursal> getSucursales() {

		List<Sucursal> sucursales = repository.findAll();
		return sucursales.isEmpty() ? null : sucursales;
	}
	
	@Override
	public List<Sucursal> searchSucursales(String nombre){
		List<Sucursal> sucursales = repository.findByNombreContaining(nombre);
		
		if (sucursales != null) {
			return sucursales;
		} else {
			return null;
		}
	}


	@Override
	public Sucursal getSucursal(long sucursalId) {
		return repository.findById(Long.valueOf(sucursalId)).orElse(null);
	}

	@Override
	public Boolean removeSucursal(long sucursalId) {

		Sucursal sucursal = repository.findById(Long.valueOf(sucursalId)).orElse(null);

		if (sucursal != null) {
			repository.delete(sucursal);
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	@Override
	public Sucursal createSucursal(CreateSucursalRequest request) {

		if (request != null && StringUtils.hasLength(request.getNombre().trim())
				&& StringUtils.hasLength(request.getDireccion().trim()) && StringUtils.hasLength(request.getTelefono().trim()) 
				&& StringUtils.hasLength(request.getCorreo().trim())) {

			Sucursal sucursal = Sucursal.builder().nombre(request.getNombre()).direccion(request.getDireccion())
					.telefono(request.getTelefono()).correo(request.getCorreo()).build();

			return repository.save(sucursal);
		} else {
			return null;
		}
	}
	
	@Override
	public Sucursal updateSucursal(long sucursalId, CreateSucursalRequest request) {
		Sucursal sucursal = repository.findById(Long.valueOf(sucursalId)).orElse(null);
		
		/*Validar entrada*/
		if (sucursal != null) {
			
			if (request != null && StringUtils.hasLength(request.getNombre().trim())
					&& StringUtils.hasLength(request.getDireccion().trim()) && StringUtils.hasLength(request.getTelefono().trim()) 
					&& StringUtils.hasLength(request.getCorreo().trim())) {

				sucursal.setNombre(request.getNombre());
				sucursal.setDireccion(request.getDireccion());
				sucursal.setTelefono(request.getTelefono());
				sucursal.setCorreo(request.getCorreo());
				return  repository.save(sucursal);
			}
			else {
				return null;
			}
		} else {
			return null;
		}
	}
}
