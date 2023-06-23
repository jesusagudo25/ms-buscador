package com.unir.msbuscador.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.unir.msbuscador.data.DataAccessRepository;
import com.unir.msbuscador.data.ProveedorRepository;
import com.unir.msbuscador.model.pojo.Proveedor;
import com.unir.msbuscador.model.request.CreateProveedorRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProveedoresService{

	private final DataAccessRepository repository;

	public List<Proveedor> getProveedores() {

		List<Proveedor> proveedores = repository.getProveedores();
		return proveedores.isEmpty() ? null : proveedores;
	}
	
	public List<Proveedor> searchProveedores(String nombre){
		List<Proveedor> proveedores = repository.findByNombreContainingProveedor(nombre);
		
		if (proveedores != null) {
			return proveedores;
		} else {
			return null;
		}
	}

	public Proveedor getProveedor(String proveedorId) {
		return repository.findProveedorById(proveedorId).orElse(null);
	}

	public Boolean removeProveedor(String proveedorId) {

		Proveedor proveedor = repository.findProveedorById(proveedorId).orElse(null);

		if (proveedor != null) {
			repository.deleteProveedor(proveedor);
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	public Proveedor createProveedor(CreateProveedorRequest request) {

		if (request != null && StringUtils.hasLength(request.getNombre().trim())
				&& StringUtils.hasLength(request.getDireccion().trim()) && StringUtils.hasLength(request.getTelefono().trim()) 
				&& StringUtils.hasLength(request.getCorreo().trim())) {

			Proveedor proveedor = Proveedor.builder().nombre(request.getNombre()).direccion(request.getDireccion())
					.telefono(request.getTelefono()).correo(request.getCorreo()).build();

			return repository.saveOrUpdateProveedor(proveedor);
		} else {
			return null;
		}
	}
	
	public Proveedor updateProveedor(String proveedorId, CreateProveedorRequest request) {
		Proveedor proveedor = repository.findProveedorById(proveedorId).orElse(null);
		
		/*Validar entrada*/
		if (proveedor != null) {
			
			if (request != null && StringUtils.hasLength(request.getNombre().trim())
					&& StringUtils.hasLength(request.getDireccion().trim()) && StringUtils.hasLength(request.getTelefono().trim()) 
					&& StringUtils.hasLength(request.getCorreo().trim())) {
				
					proveedor.setNombre(request.getNombre());
					proveedor.setDireccion(request.getDireccion());
					proveedor.setTelefono(request.getTelefono());
					proveedor.setCorreo(request.getCorreo());
					return  repository.saveOrUpdateProveedor(proveedor);
			}
			else {
				return null;
			}
		} else {
			return null;
		}
	}

}
