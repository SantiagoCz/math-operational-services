package com.santiagocz.matrices_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MatricesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MatricesServiceApplication.class, args);
	}

}
