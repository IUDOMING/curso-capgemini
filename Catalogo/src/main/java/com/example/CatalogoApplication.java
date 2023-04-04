package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.domains.contracts.repository.CategoryRepository;
import com.example.domains.entities.Category;
import com.example.domains.services.CategoryServiceImpl;

@SpringBootApplication
public class CatalogoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CatalogoApplication.class, args);

	}

	@Autowired
	CategoryRepository dao;

	@Autowired
	CategoryServiceImpl srv;

	public void run(String... args) throws Exception {

		var c1 = new Category(0, "Adios");
		srv.add(c1);
		c1.setName("Buenas");
		srv.modify(c1);

	}
}
