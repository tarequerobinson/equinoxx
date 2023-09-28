package com.example.demo;

import org.springframework.boot.SpringApplication;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.example.demo.config.SecurityConfig;
import com.example.demo.gleanernewswebscraper.NewsScraper;

@SpringBootApplication
@Import(SecurityConfig.class)
public class FinanceAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinanceAppApplication.class, args);
		System.out.print("Test Run");
		
		//new NewsScraper();

	}

}
