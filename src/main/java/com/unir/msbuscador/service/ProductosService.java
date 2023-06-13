package com.unir.msbuscador.service;

import java.util.List;

import com.unir.msbuscador.model.pojo.Producto;
import com.unir.msbuscador.model.request.CreateProductoRequest;

public interface ProductosService {
	
	List<Producto> getProductos();
	
	List<Producto> findByNombreContaining(String nombre);
	
	List<Producto> findByNombreAndCodigo(String nombre, String codigo);
	
	List<Producto> findByNombre(String nombre);
	
	List<Producto> findByCodigo(String codigo);
	
	Producto getProducto(long productoId);
	
	Boolean removeProducto(long productoId);
	
	Producto createProducto(CreateProductoRequest request);
	
	Producto updateProducto(long productoId, CreateProductoRequest request);
	
	Producto updateProductoCantidad(long productoId,Integer nuevaCantidad);
}
