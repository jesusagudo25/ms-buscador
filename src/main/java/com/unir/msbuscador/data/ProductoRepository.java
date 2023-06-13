package com.unir.msbuscador.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.unir.msbuscador.model.pojo.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long>  {
	List<Producto> findByNombre(String nombre);
	
	List<Producto> findByNombreAndCodigo(String nombre, String codigo);
	
	List<Producto> findByNombreContaining(String nombre);
	
	List<Producto> findByCodigo(String codigo);
}
