package com.unir.msbuscador.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unir.msbuscador.model.pojo.Producto;
import com.unir.msbuscador.model.request.CreateProductoRequest;
import com.unir.msbuscador.service.ProductosService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductosController {

	private final ProductosService service;

	@GetMapping("/productos")
	public ResponseEntity<List<Producto>> getProductos(
			@RequestParam(value = "nombre", required = false) String nombre,
		    @RequestParam(value = "codigo", required = false) String codigo
    ) {
		List<Producto> productos;
		
		if (nombre != null && codigo != null) {
			productos = service.findByNombreAndCodigo(nombre, codigo);
	    } else if (nombre != null) {
	    	productos = service.findByNombreContaining(nombre);
	    } else if (codigo != null) {
	    	productos = service.findByCodigo(codigo);
	    } else {	    	
	    	productos = service.getProductos();
	    }
		
		if (productos != null) {
			return ResponseEntity.ok(productos);
		} else {
			return ResponseEntity.ok(Collections.emptyList());
		}

	}
	
	@GetMapping("/productos/{productoId}")
	public ResponseEntity<Producto> getProducto(@PathVariable long productoId) {

		log.info("Request received for product {}", productoId);
		Producto producto = service.getProducto(productoId);

		if (producto != null) {
			return ResponseEntity.ok(producto);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@DeleteMapping("/productos/{productoId}")
	public ResponseEntity<Void> deleteProducto(@PathVariable long productoId) {

		Boolean removed = service.removeProducto(productoId);

		if (Boolean.TRUE.equals(removed)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@PostMapping("/productos")
	public ResponseEntity<Producto> createProducto(@RequestBody CreateProductoRequest request) {

		Producto createdProducto = service.createProducto(request);

		if (createdProducto != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(createdProducto);
		} else {
			return ResponseEntity.badRequest().build();
		}

	}
	
	@PutMapping("productos/{productoId}")
	public ResponseEntity<Producto> updateProducto(@PathVariable long productoId, @RequestBody CreateProductoRequest request){
		Producto updateProducto = service.updateProducto(productoId, request);

		if (updateProducto != null) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(updateProducto);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PatchMapping("productos/{productoId}")
	public ResponseEntity<Producto> updateProductoCantidad(@PathVariable long productoId, @RequestBody Map<String, Integer> requestBody){
		Integer nuevaCantidad = requestBody.get("nuevaCantidad");
		Producto updateProducto = service.updateProductoCantidad(productoId, nuevaCantidad);

		if (updateProducto != null) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(updateProducto);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
}
