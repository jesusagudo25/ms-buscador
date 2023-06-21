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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unir.msbuscador.model.pojo.ProductoProveedor;
import com.unir.msbuscador.model.pojo.Proveedor;
import com.unir.msbuscador.model.request.CreateProductoProveedorRequest;
import com.unir.msbuscador.model.request.CreateProveedorRequest;
import com.unir.msbuscador.service.ProductosProveedoresService;
import com.unir.msbuscador.service.ProveedoresService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProveedoresController {
	private final ProveedoresService proveedorService;
	
	private final ProductosProveedoresService productoProveedorService;

	@GetMapping("/proveedores")
	public ResponseEntity<List<Proveedor>> getProveedores(
			@RequestParam(value = "nombre", required = false) String nombre,
			@RequestParam(value = "direccion", required = false) String direccion
	    ) {
		
		List<Proveedor> proveedores;
		
		if(nombre != null) {
			proveedores = proveedorService.searchProveedores(nombre);
		}
		else {
			proveedores = proveedorService.getProveedores();			
		}


		if (proveedores != null) {
			return ResponseEntity.ok(proveedores);
		} else {
			return ResponseEntity.ok(Collections.emptyList());
		}
	}
	
	@GetMapping("/proveedores/{proveedorId}/productos")
	public ResponseEntity<List<ProductoProveedor>> getProductosProveedores(
			@PathVariable long proveedorId,
			@RequestParam(value = "nombre", required = false) String nombre
			){
		
		List<ProductoProveedor> productoProveedores;
		
		if(nombre != null) {
			productoProveedores = productoProveedorService.searchProductosByProveedorId(proveedorId, nombre);
		}
		else {
			productoProveedores = productoProveedorService.getAllProductosByProveedorId(proveedorId);
		}
		
		if (productoProveedores != null) {
			return ResponseEntity.ok(productoProveedores);
		} else {
			return ResponseEntity.ok(Collections.emptyList());
		}
		
	}
	
	@GetMapping("/proveedores/{proveedorId}")
	public ResponseEntity<Proveedor> getProveedor(@PathVariable long proveedorId) {

		log.info("Request received for product {}", proveedorId);
		Proveedor proveedor = proveedorService.getProveedor(proveedorId);

		if (proveedor != null) {
			return ResponseEntity.ok(proveedor);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@DeleteMapping("/proveedores/{proveedorId}")
	public ResponseEntity<Void> deleteProveedor(@PathVariable long proveedorId) {

		Boolean removed = proveedorService.removeProveedor(proveedorId);

		if (Boolean.TRUE.equals(removed)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}

	}
	
	@DeleteMapping("/proveedores/{proveedorId}/productos/{productoCodigo}")
	public ResponseEntity<Void> deleteProducto(@PathVariable String productoCodigo) {

		Boolean removed = productoProveedorService.removeProducto(productoCodigo);

		if (Boolean.TRUE.equals(removed)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@PostMapping("/proveedores")
	public ResponseEntity<Proveedor> createProveedor(@RequestBody CreateProveedorRequest request) {

		Proveedor createdProveedor = proveedorService.createProveedor(request);

		if (createdProveedor != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(createdProveedor);
		} else {
			return ResponseEntity.badRequest().build();
		}

	}
	
	@PostMapping("/proveedores/{proveedorId}/productos")
	public ResponseEntity<ProductoProveedor> createProductoProveedor(@PathVariable Proveedor proveedorId, @RequestBody CreateProductoProveedorRequest request) {

		ProductoProveedor createdProducto = productoProveedorService.createProducto(proveedorId, request);

		if (createdProducto != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(createdProducto);
		} else {
			return ResponseEntity.badRequest().build();
		}

	}
	
	@GetMapping("/proveedores/{proveedorId}/productos/{productoCodigo}")
	public ResponseEntity<ProductoProveedor> getProductoProveedor(@PathVariable String productoCodigo) {

		ProductoProveedor producto = productoProveedorService.getProducto(productoCodigo);

		if (producto != null) {
			return ResponseEntity.ok(producto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	@PutMapping("proveedores/{proveedorId}")
	public ResponseEntity<Proveedor> updateProveedor(@PathVariable long proveedorId, @RequestBody CreateProveedorRequest request){
		Proveedor updateProveedor = proveedorService.updateProveedor(proveedorId, request);

		if (updateProveedor != null) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(updateProveedor);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PutMapping("/proveedores/{proveedorId}/productos/{productoCodigo}")
	public ResponseEntity<ProductoProveedor> updateProducto(@PathVariable String productoCodigo, @RequestBody CreateProductoProveedorRequest request){
		ProductoProveedor updateProducto = productoProveedorService.updateProducto(productoCodigo, request);

		if (updateProducto != null) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(updateProducto);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PatchMapping("/proveedores/{proveedorId}/productos/{productoCodigo}")
	public ResponseEntity<ProductoProveedor> updateProductoCantidad(@PathVariable String productoCodigo, @RequestBody Map<String, Integer> requestBody){
		Integer nuevaCantidad = requestBody.get("nuevaCantidad");
		ProductoProveedor updateProducto = productoProveedorService.updateProductoCantidad(productoCodigo, nuevaCantidad);

		if (updateProducto != null) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(updateProducto);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
}
