package com.mc.customer.servicing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(scanBasePackages = "com.mc.customer.servicing")
@EnableCaching
public class CustomerServicingApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServicingApplication.class, args);
	}

}
