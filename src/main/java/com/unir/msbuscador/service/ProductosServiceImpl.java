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
	public List<Producto> searchProductos(String nombre){
		List<Producto> productos = repository.findByNombreContaining(nombre);
		
		if (productos != null) {
			return productos;
		} else {
			return null;
		}
	}

	@Override
	public Producto getProducto(long productoId) {
		return repository.findById(Long.valueOf(productoId)).orElse(null);
	}

	@Override
	public Boolean removeProducto(long productoId) {

		Producto producto = repository.findById(Long.valueOf(productoId)).orElse(null);

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
	public Producto updateProducto(long productoId, CreateProductoRequest request) {
		Producto producto = repository.findById(Long.valueOf(productoId)).orElse(null);
		
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
	public Producto updateProductoCantidad(long productoId, Integer nuevaCantidad) {
		Producto producto = repository.findById(Long.valueOf(productoId)).orElse(null);
		
		if(producto != null) {
			producto.setCantidad(nuevaCantidad);
			
			return repository.save(producto);
		}
		else {
			return null;
		}
	}
	
	@Override
	public Producto getProductoByCodigo(String codigo) {
		Producto producto = repository.findByCodigo(codigo);
		
		if(producto != null) {
			return producto;
		}
		else {
			return null;
		}
	}

}
