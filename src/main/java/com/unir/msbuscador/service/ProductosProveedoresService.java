package com.unir.msbuscador.service;

import java.util.List;

import com.unir.msbuscador.model.pojo.ProductoProveedor;
import com.unir.msbuscador.model.pojo.Proveedor;
import com.unir.msbuscador.model.request.CreateProductoProveedorRequest;

public interface ProductosProveedoresService {
	List<ProductoProveedor> getProductos();
	
	ProductoProveedor getProducto(long productoId);
	
	List<ProductoProveedor> getAllProductosByProveedorId(long proveedorId);
	
	List<ProductoProveedor> searchProductosByProveedorId(long proveedorId, String name);
	
	Boolean removeProducto(long productoId);
	
	ProductoProveedor createProducto(Proveedor proveedorId, CreateProductoProveedorRequest request);
	
	ProductoProveedor updateProducto(ProductoProveedor producto, CreateProductoProveedorRequest request);
	
	ProductoProveedor updateProductoCantidad(long productoId,Integer nuevaCantidad);
}
