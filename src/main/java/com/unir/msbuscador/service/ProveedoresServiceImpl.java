package com.unir.msbuscador.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.unir.msbuscador.data.ProveedorRepository;
import com.unir.msbuscador.model.pojo.Proveedor;
import com.unir.msbuscador.model.request.CreateProveedorRequest;

@Service
public class ProveedoresServiceImpl implements ProveedoresService{

	@Autowired
	private ProveedorRepository repository;

	@Override
	public List<Proveedor> getProveedores() {

		List<Proveedor> proveedores = repository.findAll();
		return proveedores.isEmpty() ? null : proveedores;
	}
	
	@Override
	public List<Proveedor> searchProveedores(String nombre){
		List<Proveedor> proveedores = repository.findByNombreContaining(nombre);
		
		if (proveedores != null) {
			return proveedores;
		} else {
			return null;
		}
	}

	@Override
	public Proveedor getProveedor(long proveedorId) {
		return repository.findById(Long.valueOf(proveedorId)).orElse(null);
	}

	@Override
	public Boolean removeProveedor(long proveedorId) {

		Proveedor proveedor = repository.findById(Long.valueOf(proveedorId)).orElse(null);

		if (proveedor != null) {
			repository.delete(proveedor);
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	@Override
	public Proveedor createProveedor(CreateProveedorRequest request) {

		if (request != null && StringUtils.hasLength(request.getNombre().trim())
				&& StringUtils.hasLength(request.getDireccion().trim()) && StringUtils.hasLength(request.getTelefono().trim()) 
				&& StringUtils.hasLength(request.getCorreo().trim())) {

			Proveedor proveedor = Proveedor.builder().nombre(request.getNombre()).direccion(request.getDireccion())
					.telefono(request.getTelefono()).correo(request.getCorreo()).build();

			return repository.save(proveedor);
		} else {
			return null;
		}
	}
	
	@Override
	public Proveedor updateProveedor(long proveedorId, CreateProveedorRequest request) {
		Proveedor proveedor = repository.findById(Long.valueOf(proveedorId)).orElse(null);
		
		/*Validar entrada*/
		if (proveedor != null) {
			
			if (request != null && StringUtils.hasLength(request.getNombre().trim())
					&& StringUtils.hasLength(request.getDireccion().trim()) && StringUtils.hasLength(request.getTelefono().trim()) 
					&& StringUtils.hasLength(request.getCorreo().trim())) {
				
					proveedor.setNombre(request.getNombre());
					proveedor.setDireccion(request.getDireccion());
					proveedor.setTelefono(request.getTelefono());
					proveedor.setCorreo(request.getCorreo());
					return  repository.save(proveedor);
			}
			else {
				return null;
			}
		} else {
			return null;
		}
	}

}
