package com.crusaders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableJpaRepositories
@ComponentScan
public class DemodesafioapialunoematriculaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemodesafioapialunoematriculaApplication.class, args);
	}

}
