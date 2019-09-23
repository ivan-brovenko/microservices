package com.istore.db.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.istore.db.service")
public class DbServiceRunner {
	public static void main(String[] args) {
		SpringApplication.run(DbServiceRunner.class, args);
	}
}

