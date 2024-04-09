package com.example.springjpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.example.springjpa.repository")

@EntityScan(basePackages = "com.example.springjpa.entities")
@SpringBootApplication
public class SpringJpaOneApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaOneApplication.class, args);
	}
}
