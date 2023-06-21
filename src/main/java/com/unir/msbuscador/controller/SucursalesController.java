package com.unir.msbuscador.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unir.msbuscador.model.pojo.Sucursal;
import com.unir.msbuscador.model.request.CreateSucursalRequest;
import com.unir.msbuscador.service.SucursalesService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SucursalesController {
	private final SucursalesService service;

	@GetMapping("/sucursales")
	public ResponseEntity<List<Sucursal>> getSucursales(
			@RequestParam(value = "nombre", required = false) String nombre,
			@RequestParam(value = "direccion", required = false) String direccion
	    ) {
		
		List<Sucursal> sucursales;
		
		if(nombre != null) {
			sucursales = service.searchSucursales(nombre);
		}
		else {
			sucursales = service.getSucursales();
		}

		if (sucursales != null) {
			return ResponseEntity.ok(sucursales);
		} else {
			return ResponseEntity.ok(Collections.emptyList());
		}
	}
	
	@GetMapping("/sucursales/{sucursalId}")
	public ResponseEntity<Sucursal> getSucursal(@PathVariable long sucursalId) {

		log.info("Request received for product {}", sucursalId);
		Sucursal sucursal = service.getSucursal(sucursalId);

		if (sucursal != null) {
			return ResponseEntity.ok(sucursal);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@DeleteMapping("/sucursales/{sucursalId}")
	public ResponseEntity<Void> deleteSucursal(@PathVariable long sucursalId) {

		Boolean removed = service.removeSucursal(sucursalId);

		if (Boolean.TRUE.equals(removed)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@PostMapping("/sucursales")
	public ResponseEntity<Sucursal> createSucursal(@RequestBody CreateSucursalRequest request) {

		Sucursal createdSucursal = service.createSucursal(request);

		if (createdSucursal != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(createdSucursal);
		} else {
			return ResponseEntity.badRequest().build();
		}

	}
	
	@PutMapping("sucursales/{sucursalId}")
	public ResponseEntity<Sucursal> updateSucursal(@PathVariable long sucursalId, @RequestBody CreateSucursalRequest request){
		Sucursal updateSucursal = service.updateSucursal(sucursalId, request);

		if (updateSucursal != null) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(updateSucursal);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
}
