package com.unir.msbuscador.service;

import java.util.List;

import com.unir.msbuscador.model.pojo.ProductoProveedor;
import com.unir.msbuscador.model.pojo.Proveedor;
import com.unir.msbuscador.model.request.CreateProductoProveedorRequest;

public interface ProductosProveedoresService {
	List<ProductoProveedor> getProductos();
	
	ProductoProveedor getProducto(String codigo);
	
	List<ProductoProveedor> getAllProductosByProveedorId(long proveedorId);
	
	List<ProductoProveedor> searchProductosByProveedorId(long proveedorId, String name);
	
	Boolean removeProducto(String codigo);
	
	ProductoProveedor createProducto(Proveedor proveedorId, CreateProductoProveedorRequest request);
	
	ProductoProveedor updateProducto(String codigo, CreateProductoProveedorRequest request);
	
	ProductoProveedor updateProductoCantidad(String codigo,Integer nuevaCantidad);
}
