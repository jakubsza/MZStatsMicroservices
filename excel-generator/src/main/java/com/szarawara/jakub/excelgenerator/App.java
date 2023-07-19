package com.szarawara.jakub.excelgenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@EnableDiscoveryClient
@ComponentScan
public class App {

	public static void main(String[] args) {
		// Tell Boot to look for generator-server.yml
		System.setProperty("spring.config.name", "generator-server");
		SpringApplication.run(App.class, args);
	}

}
