package com.unir.msbuscador.data;

import java.util.List;
import java.util.Optional;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import com.unir.msbuscador.model.pojo.ProductoProveedor;

public interface ProductoProveedorRepository extends ElasticsearchRepository<ProductoProveedor, String>{
	
	List<ProductoProveedor> findByNombreContainingAndProveedorId(String nombre, String proveedorId);
	
	List<ProductoProveedor> findByProveedorId(String proveedorId);
	
	Optional<ProductoProveedor> findById(String id);
	
	ProductoProveedor findByCodigo(String codigo);
	
	void delete(ProductoProveedor productoProveedor);
	
	ProductoProveedor save(ProductoProveedor productoProveedor);
}
