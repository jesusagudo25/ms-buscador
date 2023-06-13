package com.unir.msbuscador.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.unir.msbuscador.data.ProductoProveedorRepository;
import com.unir.msbuscador.data.ProveedorRepository;
import com.unir.msbuscador.model.pojo.ProductoProveedor;
import com.unir.msbuscador.model.pojo.Proveedor;
import com.unir.msbuscador.model.request.CreateProductoProveedorRequest;

@Service
public class ProductosProveedoresServiceImpl implements ProductosProveedoresService{
	
	@Autowired
	ProveedorRepository proveedorRepository;
	
	@Autowired
	ProductoProveedorRepository productoProveedorRepository;

	@Override
	public List<ProductoProveedor> getProductos() {

		List<ProductoProveedor> productos = productoProveedorRepository.findAll();
		return productos.isEmpty() ? null : productos;
	}
	
	@Override
	public List<ProductoProveedor> getAllProductosByProveedorId(long proveedorId){
		if(!proveedorRepository.existsById(proveedorId)) {
			return null;
		}
		
		List<ProductoProveedor> productos = productoProveedorRepository.findByProveedorId(proveedorId);
		if (productos != null) {
			return productos;
		} else {
			return null;
		}
	}
	
	@Override
	public List<ProductoProveedor> searchProductosByProveedorId(long proveedorId, String name){
		List<ProductoProveedor> productos = productoProveedorRepository.findByNombreContainingAndProveedorId(name, proveedorId);
		
		if (productos != null) {
			return productos;
		} else {
			return null;
		}
	}

	@Override
	public ProductoProveedor getProducto(long productoId) {
		return productoProveedorRepository.findById(Long.valueOf(productoId)).orElse(null);
	}

	@Override
	public Boolean removeProducto(long productoId) {

		ProductoProveedor producto = productoProveedorRepository.findById(Long.valueOf(productoId)).orElse(null);

		if (producto != null) {
			productoProveedorRepository.delete(producto);
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	@Override
	public ProductoProveedor createProducto(Proveedor proveedorId, CreateProductoProveedorRequest request) {

		if (request != null && StringUtils.hasLength(request.getNombre().trim())
				&& StringUtils.hasLength(request.getCodigo().trim()) && request.getPrecio() != null) {

			ProductoProveedor producto = ProductoProveedor.builder().nombre(request.getNombre()).codigo(request.getCodigo())
					.precio(request.getPrecio()).cantidad(request.getCantidad()).proveedor(proveedorId).build();

			return productoProveedorRepository.save(producto);
		} else {
			return null;
		}
	}
	
	@Override
	public ProductoProveedor updateProducto(ProductoProveedor producto, CreateProductoProveedorRequest request) {
		
		if (request != null && StringUtils.hasLength(request.getNombre().trim())
				&& StringUtils.hasLength(request.getCodigo().trim()) && request.getPrecio() != null) {
			
			producto.setNombre(request.getNombre());
			producto.setCodigo(request.getCodigo());
			producto.setPrecio(request.getPrecio());
			producto.setCantidad(request.getCantidad());
			return  productoProveedorRepository.save(producto);
		}
		else {
			return null;
		}
	}
	
	@Override
	public ProductoProveedor updateProductoCantidad(long productoId, Integer nuevaCantidad) {
		ProductoProveedor producto = productoProveedorRepository.findById(Long.valueOf(productoId)).orElse(null);
		
		if(producto != null) {
			producto.setCantidad(nuevaCantidad);
			
			return productoProveedorRepository.save(producto);
		}
		else {
			return null;
		}
	}
}
