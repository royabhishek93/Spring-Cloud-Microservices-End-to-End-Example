package com.abhishek.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class StockServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockServicesApplication.class, args);
	}

}
