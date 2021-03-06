package com.istore.db.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.istore.db.service")
@EnableEurekaClient
public class DbServiceRunner {
	public static void main(String[] args) {
		SpringApplication.run(DbServiceRunner.class, args);
	}
}

