package com.crusaders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@ComponentScan
public class DemodesafioapicursoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemodesafioapicursoApplication.class, args);
	}

}
