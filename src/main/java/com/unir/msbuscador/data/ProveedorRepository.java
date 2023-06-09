package com.unir.msbuscador.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unir.msbuscador.model.pojo.Proveedor;

public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
	List<Proveedor> findByNombre(String nombre);
	
	List<Proveedor> findByNombreContaining(String nombre);
}
