package com.unir.msbuscador.service;

import java.util.List;

import com.unir.msbuscador.model.pojo.Producto;
import com.unir.msbuscador.model.request.CreateProductoRequest;

public interface ProductosService {
	
	List<Producto> getProductos();
	
	List<Producto> searchProductos(String nombre);
	
	Producto getProducto(long productoId);
	
	Boolean removeProducto(long productoId);
	
	Producto createProducto(CreateProductoRequest request);
	
	Producto updateProducto(long productoId, CreateProductoRequest request);
	
	Producto updateProductoCantidad(long productoId,Integer nuevaCantidad);
	
	Producto getProductoByCodigo(String codigo);
}
