package com.crusaders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DemodesafioapicursoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemodesafioapicursoApplication.class, args);
	}

}
