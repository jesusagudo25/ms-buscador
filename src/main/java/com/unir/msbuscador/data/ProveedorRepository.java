package com.unir.msbuscador.data;

import java.util.List;
import java.util.Optional;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.unir.msbuscador.model.pojo.Proveedor;

public interface ProveedorRepository extends ElasticsearchRepository<Proveedor, String> {
	
	List<Proveedor> findAll();
	
	List<Proveedor> findByNombreContaining(String nombre);
	
	Optional<Proveedor> findById(String id);
	
	void delete(Proveedor product);
	
	Proveedor save(Proveedor save);
}
