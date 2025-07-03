package com.pizzeria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.pizzeria")
@ServletComponentScan(basePackages = "com.pizzeria.servlets")
public class PizzeriaApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(PizzeriaApplication.class, args);
	}
}