package com.ironhack.opportunity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
public class OpportunityApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpportunityApplication.class, args);
	}

}
