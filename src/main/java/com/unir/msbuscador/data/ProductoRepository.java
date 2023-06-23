package com.unir.msbuscador.data;

import java.util.List;
import java.util.Optional;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import com.unir.msbuscador.model.pojo.Producto;

public interface ProductoRepository extends ElasticsearchRepository<Producto, String>  {
	
	List<Producto> findAll();
	
	List<Producto> findByNombreContaining(String nombre);
	
	Optional<Producto> findById(String id);
	
	Producto findByCodigo(String codigo);
	
	void delete(Producto product);
	
	Producto save(Producto save);
}
