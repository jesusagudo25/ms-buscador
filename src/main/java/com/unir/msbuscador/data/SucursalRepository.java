package com.unir.msbuscador.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unir.msbuscador.model.pojo.Sucursal;

public interface SucursalRepository extends JpaRepository<Sucursal, Long>{
	List<Sucursal> findByNombre(String nombre);
	
	List<Sucursal> findByNombreContaining(String nombre);
}
