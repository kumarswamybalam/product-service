package com.balam.service.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductServiceApplication {

	public static void main(String[] args) {
		setProfile();
		SpringApplication.run(ProductServiceApplication.class, args);
	}

	private static void setProfile() {
		if(System.getProperty("environment") == null || System.getProperty("environment").equals(null)) {
			System.setProperty("environment", "local");
		}
	}
}
