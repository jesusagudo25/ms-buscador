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
import org.springframework.web.bind.annotation.RestController;

import com.unir.msbuscador.model.pojo.ProductoProveedor;
import com.unir.msbuscador.model.pojo.Proveedor;
import com.unir.msbuscador.model.request.CreateProductoProveedorRequest;
import com.unir.msbuscador.service.ProductosProveedoresService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductosProveedoresController {
	
	private final ProductosProveedoresService service;
	
	@GetMapping("/productos-proveedores")
	public ResponseEntity<List<ProductoProveedor>> getProductos(@RequestHeader Map<String, String> headers) {

		log.info("headers: {}", headers);
		List<ProductoProveedor> productos = service.getProductos();

		if (productos != null) {
			return ResponseEntity.ok(productos);
		} else {
			return ResponseEntity.ok(Collections.emptyList());
		}
	}

	@GetMapping("/proveedores/{proveedorId}/productos")
	public ResponseEntity<List<ProductoProveedor>> getAllProductosByProveedorId(@PathVariable long proveedorId) {

		List<ProductoProveedor> productos = service.getAllProductosByProveedorId(proveedorId);

		if (productos != null) {
			return ResponseEntity.ok(productos);
		} else {
			return ResponseEntity.ok(Collections.emptyList());
		}

	}
	
	@GetMapping("/proveedores/{proveedorId}/productos/busquedas/{nombre}")
	public ResponseEntity<List<ProductoProveedor>> searchProductoByProveedorId(@PathVariable long proveedorId, @PathVariable String nombre) {
		List<ProductoProveedor> productos = service.searchProductosByProveedorId(proveedorId, nombre);

		if (productos != null) {
			return ResponseEntity.ok(productos);
		} else {
			return ResponseEntity.ok(Collections.emptyList());
		}
	}
	
	@GetMapping("/productos-proveedores/{productoId}")
	public ResponseEntity<ProductoProveedor> getProducto(@PathVariable long productoId) {
		log.info("Request received for product {}", productoId);
		ProductoProveedor producto = service.getProducto(productoId);

		if (producto != null) {
			return ResponseEntity.ok(producto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/productos-proveedores/{productoId}")
	public ResponseEntity<Void> deleteProducto(@PathVariable long productoId) {

		Boolean removed = service.removeProducto(productoId);

		if (Boolean.TRUE.equals(removed)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@PostMapping("/proveedores/{proveedorId}/productos")
	public ResponseEntity<ProductoProveedor> createProducto(@PathVariable Proveedor proveedorId, @RequestBody CreateProductoProveedorRequest request) {

		ProductoProveedor createdProducto = service.createProducto(proveedorId, request);

		if (createdProducto != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(createdProducto);
		} else {
			return ResponseEntity.badRequest().build();
		}

	}
	
	@PutMapping("/productos-proveedores/{producto}")
	public ResponseEntity<ProductoProveedor> updateProducto(@PathVariable ProductoProveedor producto, @RequestBody CreateProductoProveedorRequest request){
		ProductoProveedor updateProducto = service.updateProducto(producto, request);

		if (updateProducto != null) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(updateProducto);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PatchMapping("/productos-proveedores/{productoId}/cantidad")
	public ResponseEntity<ProductoProveedor> updateProductoCantidad(@PathVariable long productoId, @RequestBody Map<String, Integer> requestBody){
		Integer nuevaCantidad = requestBody.get("nuevaCantidad");
		ProductoProveedor updateProducto = service.updateProductoCantidad(productoId, nuevaCantidad);

		if (updateProducto != null) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(updateProducto);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

}
