package com.example.ejemplos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PersonaTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	// Pese a varios asserts, solo estás mirando una sola cosa.
	// Estas mirando que la persona se crée correctamente con los valores correctos.
	@Test
	void testCreatePersona() {
		// Mediante la annotación builder en la clase Persona
		// podermos llevar a cabo la línea constructora sin cortes.
		var p = Persona.builder().id(1).nombre("Ramón").apellidos("Juarez").build();
		// p=null;

		// Comprobamos que "p" no sea nulo. Que se haya creado correctamente.
		assertNotNull(p);
		// Preguntamos que se haya instanciado como Persona.
		assertTrue(p instanceof Persona, "No es instancia de Persona");
		
		//Ejecuta todos los asserts, y mria cual de ellos es el que falla
		assertAll("Validar propiedades", 
				() -> assertEquals(1, p.getId(), "id"),
				() -> assertEquals("Ramón", p.getNombre(), "getNombre"),
				() -> assertEquals("Juarez", p.getApellidos(), "getApellido")
				);
	}

}
