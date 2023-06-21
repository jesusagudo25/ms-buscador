package com.unir.msbuscador.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.unir.msbuscador.data.ProductoRepository;
import com.unir.msbuscador.model.pojo.Producto;
import com.unir.msbuscador.model.request.CreateProductoRequest;

@Service
public class ProductosServiceImpl implements ProductosService {
	
	@Autowired
	private ProductoRepository repository;

	@Override
	public List<Producto> getProductos() {

		List<Producto> productos = repository.findAll();
		return productos.isEmpty() ? null : productos;
	}
	
	@Override
	public List<Producto> findByNombreAndCodigo(String nombre, String codigo){
		List<Producto> productos = repository.findByNombreAndCodigo(nombre, codigo);
		
		if (productos != null) {
			return productos;
		} else {
			return null;
		}
	}
	
	@Override
	public List<Producto> findByNombreContaining(String nombre) {

		List<Producto> productos = repository.findByNombreContaining(nombre);
		return productos.isEmpty() ? null : productos;
	}

	@Override
	public Producto getProducto(String codigo) {
		return repository.findByCodigo(codigo);
	}

	@Override
	public Boolean removeProducto(String codigo) {

		Producto producto = repository.findByCodigo(codigo);

		if (producto != null) {
			repository.delete(producto);
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	@Override
	public Producto createProducto(CreateProductoRequest request) {

		if (request != null && StringUtils.hasLength(request.getNombre().trim())
				&& StringUtils.hasLength(request.getCodigo().trim()) && request.getPrecio() != null) {

			Producto producto = Producto.builder().nombre(request.getNombre()).codigo(request.getCodigo())
					.precio(request.getPrecio()).cantidad(request.getCantidad()).build();

			return repository.save(producto);
		} else {
			return null;
		}
	}
	
	@Override
	public Producto updateProducto(String codigo, CreateProductoRequest request) {
		Producto producto = repository.findByCodigo(codigo);
		
		/*Validar entrada*/
		if (producto != null) {
			if (request != null && StringUtils.hasLength(request.getNombre().trim())
					&& StringUtils.hasLength(request.getCodigo().trim()) && request.getPrecio() != null) {
				producto.setNombre(request.getNombre());
				producto.setCodigo(request.getCodigo());
				producto.setPrecio(request.getPrecio());
				producto.setCantidad(request.getCantidad());
				return  repository.save(producto);
			}
			else {
				return null;
			}
		} else {
			return null;
		}
	}
	
	@Override
	public Producto updateProductoCantidad(String codigo, Integer nuevaCantidad) {
		Producto producto = repository.findByCodigo(codigo);
		
		if(producto != null) {
			producto.setCantidad(nuevaCantidad);
			
			return repository.save(producto);
		}
		else {
			return null;
		}
	}


}
