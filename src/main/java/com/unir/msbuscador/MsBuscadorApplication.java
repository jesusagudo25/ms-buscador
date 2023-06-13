package com.unir.msbuscador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;

@SpringBootApplication
public class MsBuscadorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsBuscadorApplication.class, args);
	}

}
