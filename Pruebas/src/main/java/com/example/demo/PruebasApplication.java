package com.example.demo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PruebasApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(PruebasApplication.class, args);

		System.out.println("Prueba");

	}

	@Autowired
	private GermanClockBase gClock;

	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Aplicación arrancada");
		System.out.println(gClock.convert("23:59:59"));

	}

}
