package com.example.demo;

import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PruebasApplication {

	public static void main(String[] args) {
		SpringApplication.run(PruebasApplication.class, args);

		System.out.println("Prueba");

		//For check
		//GermanClockBase gClock = new GermanClockBase();
		//System.out.println(gClock.convert("23:59:59"));

	}

}
