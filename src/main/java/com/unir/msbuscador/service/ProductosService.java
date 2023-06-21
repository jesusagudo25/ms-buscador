package com.unir.msbuscador.service;

import java.util.List;

import com.unir.msbuscador.model.pojo.Producto;
import com.unir.msbuscador.model.request.CreateProductoRequest;

public interface ProductosService {
	
	List<Producto> getProductos();
	
	List<Producto> findByNombreContaining(String nombre);
	
	List<Producto> findByNombreAndCodigo(String nombre, String codigo);
	
	Producto getProducto(String codigo);
	
	Boolean removeProducto(String codigo);
	
	Producto createProducto(CreateProductoRequest request);
	
	Producto updateProducto(String codigo, CreateProductoRequest request);
	
	Producto updateProductoCantidad(String codigo,Integer nuevaCantidad);
}
