package com.szarawara.jakub.statscollector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@EnableDiscoveryClient
@ComponentScan
public class App {

	public static void main(String[] args) {
		// Tell Boot to look for stats-collector.yml
		System.setProperty("spring.config.name", "stats-collector");
		SpringApplication.run(App.class, args);
	}

}
