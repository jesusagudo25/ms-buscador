package com.unir.msbuscador.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.unir.msbuscador.data.DataAccessRepository;
import com.unir.msbuscador.data.ProductoProveedorRepository;
import com.unir.msbuscador.data.ProveedorRepository;
import com.unir.msbuscador.model.pojo.ProductoProveedor;
import com.unir.msbuscador.model.pojo.Proveedor;
import com.unir.msbuscador.model.request.CreateProductoProveedorRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductosProveedoresService{
	
	private final DataAccessRepository repository;

	public List<ProductoProveedor> getAllProductosByProveedorId(String proveedorId){
		if(!repository.existsProveedorById(proveedorId)) {
			return null;
		}
		
		List<ProductoProveedor> productos = repository.findByProveedorId(proveedorId);
		if (productos != null) {
			return productos;
		} else {
			return null;
		}
	}
	
	public List<ProductoProveedor> searchProductosByProveedorId(String proveedorId, String name){
		List<ProductoProveedor> productos = repository.findByNombreContainingAndProveedorId(name, proveedorId);
		
		if (productos != null) {
			return productos;
		} else {
			return null;
		}
	}

	public ProductoProveedor getProducto(String codigo) {
		return repository.findProductoProveedorByCodigo(codigo);
	}

	public Boolean removeProducto(String codigo) {

		ProductoProveedor producto = repository.findProductoProveedorByCodigo(codigo);

		if (producto != null) {
			repository.deleteProductoProveedor(producto);
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	public ProductoProveedor createProducto(String proveedorId, CreateProductoProveedorRequest request) {

		if (request != null && StringUtils.hasLength(request.getNombre().trim())
				&& StringUtils.hasLength(request.getCodigo().trim()) && request.getPrecio() != null) {

			ProductoProveedor producto = ProductoProveedor.builder().nombre(request.getNombre()).codigo(request.getCodigo())
					.precio(request.getPrecio()).cantidad(request.getCantidad()).proveedorId(proveedorId).build();

			return repository.saveOrUpdateProductoProveedor(producto);
		} else {
			return null;
		}
	}
	
	public ProductoProveedor updateProducto(String codigo, CreateProductoProveedorRequest request) {
		ProductoProveedor producto = repository.findProductoProveedorByCodigo(codigo);
		
		if (request != null && StringUtils.hasLength(request.getNombre().trim())
				&& StringUtils.hasLength(request.getCodigo().trim()) && request.getPrecio() != null) {
			
			producto.setNombre(request.getNombre());
			producto.setCodigo(request.getCodigo());
			producto.setPrecio(request.getPrecio());
			producto.setCantidad(request.getCantidad());
			return  repository.saveOrUpdateProductoProveedor(producto);
		}
		else {
			return null;
		}
	}
	
	public ProductoProveedor updateProductoCantidad(String codigo, Integer nuevaCantidad) {
		ProductoProveedor producto = repository.findProductoProveedorByCodigo(codigo);
		
		if(producto != null) {
			producto.setCantidad(nuevaCantidad);
			
			return repository.saveOrUpdateProductoProveedor(producto);
		}
		else {
			return null;
		}
	}
}
