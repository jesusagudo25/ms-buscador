package com.unir.msbuscador.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.unir.msbuscador.model.pojo.Proveedor;
import com.unir.msbuscador.model.request.CreateProveedorRequest;
import com.unir.msbuscador.service.ProveedoresService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProveedoresController {
	private final ProveedoresService service;

	@GetMapping("/proveedores")
	public ResponseEntity<List<Proveedor>> getProveedores(@RequestHeader Map<String, String> headers) {

		log.info("headers: {}", headers);
		List<Proveedor> proveedores = service.getProveedores();

		if (proveedores != null) {
			return ResponseEntity.ok(proveedores);
		} else {
			return ResponseEntity.ok(Collections.emptyList());
		}
	}
	
	@GetMapping("/proveedores/{proveedorId}")
	public ResponseEntity<Proveedor> getProveedor(@PathVariable long proveedorId) {

		log.info("Request received for product {}", proveedorId);
		Proveedor proveedor = service.getProveedor(proveedorId);

		if (proveedor != null) {
			return ResponseEntity.ok(proveedor);
		} else {
			return ResponseEntity.notFound().build();
		}

	}
	
	@GetMapping("/proveedores/busquedas/{nombre}")
	public ResponseEntity<List<Proveedor>> searchProveedores(@PathVariable String nombre) {
		List<Proveedor> proveedores = service.searchProveedores(nombre);

		if (proveedores != null) {
			return ResponseEntity.ok(proveedores);
		} else {
			return ResponseEntity.ok(Collections.emptyList());
		}
	}

	@DeleteMapping("/proveedores/{proveedorId}")
	public ResponseEntity<Void> deleteProveedor(@PathVariable long proveedorId) {

		Boolean removed = service.removeProveedor(proveedorId);

		if (Boolean.TRUE.equals(removed)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@PostMapping("/proveedores")
	public ResponseEntity<Proveedor> createProveedor(@RequestBody CreateProveedorRequest request) {

		Proveedor createdProveedor = service.createProveedor(request);

		if (createdProveedor != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(createdProveedor);
		} else {
			return ResponseEntity.badRequest().build();
		}

	}
	
	@PutMapping("proveedores/{proveedorId}")
	public ResponseEntity<Proveedor> updateProveedor(@PathVariable long proveedorId, @RequestBody CreateProveedorRequest request){
		Proveedor updateProveedor = service.updateProveedor(proveedorId, request);

		if (updateProveedor != null) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(updateProveedor);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
}
