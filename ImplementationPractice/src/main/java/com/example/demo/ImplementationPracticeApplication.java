package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.domains.contracts.services.ActorService;
import com.example.demo.domains.entities.Actor;

@SpringBootApplication
public class ImplementationPracticeApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ImplementationPracticeApplication.class, args);
	}
	@Autowired
	ActorService srv;
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Aplicación arrancada");
		srv.add(new Actor (0, "Domine", "PATRIE"));

	}

}
