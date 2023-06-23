package com.unir.msbuscador.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.unir.msbuscador.data.DataAccessRepository;
import com.unir.msbuscador.data.ProductoRepository;
import com.unir.msbuscador.model.pojo.Producto;
import com.unir.msbuscador.model.request.CreateProductoRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductosService {
	
	private final DataAccessRepository repository;

	public List<Producto> getProductos() {

		List<Producto> productos = repository.getProductos();
		return productos.isEmpty() ? null : productos;
	}
	
	public List<Producto> findByNombreContaining(String nombre) {

		List<Producto> productos = repository.findByNombreContainingProducto(nombre);
		return productos.isEmpty() ? null : productos;
	}

	public Producto getProducto(String codigo) {
		return repository.findProductoByCodigo(codigo);
	}

	public Boolean removeProducto(String codigo) {

		Producto producto = repository.findProductoByCodigo(codigo);

		if (producto != null) {
			repository.deleteProducto(producto);
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	public Producto createProducto(CreateProductoRequest request) {

		if (request != null && StringUtils.hasLength(request.getNombre().trim())
				&& StringUtils.hasLength(request.getCodigo().trim()) && request.getPrecio() != null) {

			Producto producto = Producto.builder().nombre(request.getNombre()).codigo(request.getCodigo())
					.precio(request.getPrecio()).cantidad(request.getCantidad()).build();

			return repository.saveOrUpdateProducto(producto);
		} else {
			return null;
		}
	}
	
	public Producto updateProducto(String codigo, CreateProductoRequest request) {
		Producto producto = repository.findProductoByCodigo(codigo);
		
		/*Validar entrada*/
		if (producto != null) {
			if (request != null && StringUtils.hasLength(request.getNombre().trim())
					&& StringUtils.hasLength(request.getCodigo().trim()) && request.getPrecio() != null) {
				producto.setNombre(request.getNombre());
				producto.setCodigo(request.getCodigo());
				producto.setPrecio(request.getPrecio());
				producto.setCantidad(request.getCantidad());
				return  repository.saveOrUpdateProducto(producto);
			}
			else {
				return null;
			}
		} else {
			return null;
		}
	}
	
	public Producto updateProductoCantidad(String codigo, Integer nuevaCantidad) {
		Producto producto = repository.findProductoByCodigo(codigo);
		
		if(producto != null) {
			producto.setCantidad(nuevaCantidad);
			
			return repository.saveOrUpdateProducto(producto);
		}
		else {
			return null;
		}
	}


}
