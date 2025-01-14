package com.example.tiendaElectronica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TiendaElectronicaApplication {

	public static void main(String[] args) {

		SpringApplication.run(TiendaElectronicaApplication.class, args);
	}

}
