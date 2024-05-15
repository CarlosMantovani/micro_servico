package com.crusaders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableFeignClients
@EnableJpaRepositories
@ComponentScan
public class DemodesafioapialunoematriculaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemodesafioapialunoematriculaApplication.class, args);
	}

}
