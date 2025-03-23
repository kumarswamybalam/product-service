package com.balam.service.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.AbstractEnvironment;

@SpringBootApplication
@Import(RootConfig.class)
public class ProductServiceApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		setProfile();
		SpringApplication.run(ProductServiceApplication.class, args);
	}

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		setProfile();
		return application.sources(ProductServiceApplication.class);
	}

	public static void setProfile() {
		if (System.getProperty("environment") == null || System.getProperty("environment").equals("null")) {
			System.setProperty("environment", "local");
		}

		if (System.getProperty("region") == null || System.getProperty("region").equals("null")) {
			System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, System.getProperty("environment") + "-" + System.getProperty("region"));
			if(!System.getProperty("farm").equals("null")) {
				System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, System.getProperty("environment") + "-" + System.getProperty("region") + "-" + System.getProperty("farm") + "," + System.getProperty("spring.profiles.active"));
			}
			System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, System.getProperty("environment") + "," + System.getProperty("spring.profiles.active"));
		} else {
			System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, System.getProperty("environment"));
		}
	}
}
