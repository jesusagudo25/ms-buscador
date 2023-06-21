package com.unir.msbuscador.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unir.msbuscador.model.pojo.Producto;
import com.unir.msbuscador.model.pojo.ProductoProveedor;

public interface ProductoProveedorRepository extends JpaRepository<ProductoProveedor, Long>{
	List<ProductoProveedor> findByNombre(String nombre);
	
	List<ProductoProveedor> findByNombreContainingAndProveedorId(String nombre, long proveedorId);
	
	List<ProductoProveedor> findByProveedorId(long proveedorId);
	
	ProductoProveedor findByCodigo(String codigo);
}
