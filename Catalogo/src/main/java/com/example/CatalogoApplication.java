package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.domains.contracts.repository.ActorRepository;
import com.example.domains.entities.Actor;


@SpringBootApplication
public class CatalogoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CatalogoApplication.class, args);

	}
	
	@Autowired
	ActorRepository dao;
	public void run(String... args) throws Exception {

		Actor act = new Actor(202, "Demo","GRILLADO");
		dao.delete(act);
		
	}
}
