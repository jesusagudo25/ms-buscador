package com.unir.msbuscador.data;

import java.util.List;
import java.util.Optional;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.unir.msbuscador.model.pojo.Sucursal;

public interface SucursalRepository extends ElasticsearchRepository<Sucursal, String>{
	
	List<Sucursal> findAll();
	
	List<Sucursal> findByNombreContaining(String nombre);
	
	Optional<Sucursal> findById(String id);
	
	void delete(Sucursal product);
	
	Sucursal save(Sucursal save);
}
