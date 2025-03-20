package com.app.userprofile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
@EnableDiscoveryClient
public class UserProfileServiceApplication {

	private static final Logger logger = LoggerFactory.getLogger(UserProfileServiceApplication.class);

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure().directory("./").load();
		dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
		SpringApplication.run(UserProfileServiceApplication.class, args);
		logger.info("ProfileServiceApplication started, attempting Eureka registration");
	}

}
