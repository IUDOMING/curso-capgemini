package com.example.ejemplos;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.ioc.PersonaRepository;

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

		// Ejecuta todos los asserts, y mria cual de ellos es el que falla
		assertAll("Validar propiedades", () -> assertEquals(1, p.getId(), "id"),
				() -> assertEquals("Ramón", p.getNombre(), "getNombre"),
				() -> assertEquals("Juarez", p.getApellidos().get(), "getApellido")

		);
	}

	// Para hacer multiples repeticiónes de un test, generando mismo test varias
	// veces
	// Forma correcta
	@RepeatedTest(value = 5, name = "{displayName} {currentRepetition}/{totalRepetitions}")
	void repeatedTest(RepetitionInfo repetitionInfo) {
		var p = Persona.builder()
				// Al "id" le estoy pasando el valor de la repetición
				.id(repetitionInfo.getCurrentRepetition())
				// Al "nombre" le paso el valor de Ramón + numero repetición
				.nombre("Ramón" + repetitionInfo.getCurrentRepetition()).apellidos("Juarez").build();
		// p=null;

		// Comprobamos que "p" no sea nulo. Que se haya creado correctamente.
		assertNotNull(p);
		// Preguntamos que se haya instanciado como Persona.
		assertTrue(p instanceof Persona, "No es instancia de Persona");

		// Ejecuta todos los asserts, y mria cual de ellos es el que falla
		assertAll("Validar propiedades",
				// Mira que el valor sea igual al numero de repetición
				() -> assertEquals(repetitionInfo.getCurrentRepetition(), p.getId(), "id"),
				// Mira que el valor sea igual a Ramón + numero de repetición
				() -> assertEquals("Ramón"
						+ (repetitionInfo.getCurrentRepetition() % 3 == 0 ? "" : repetitionInfo.getCurrentRepetition()),
						p.getNombre(), "getNombre"),
				() -> assertEquals("Juarez", p.getApellidos().get(), "getApellido"));

	}

	@Nested
	class PersonaRepositoryTest {
		@Mock
		PersonaRepository dao;
		
		@Test
		void testLoad() {
			PersonaRepository dao= mock(PersonaRepository.class);
			when(dao.load()).thenReturn(Persona.builder().id(1).nombre("Ramón").apellidos("Juarez").build());
			//Aquí viene la prueba
			var p=dao.load();
			assertTrue(p instanceof Persona, "No es instancia de Persona");
			assertAll("Validar propiedades",
					() -> assertEquals(1, p.getId(), "id"),
					() -> assertEquals("Ramón",	p.getNombre(), "getNombre"),
					//Como getApellido implementa Optional, se ha de aplicar el get
					//Si no el resultado da un array Optional<Juarez>
					() -> assertEquals("Juarez", p.getApellidos().get(), "getApellido"));
		}

	}
	@Nested
	class PersonaRepositoryTestP {
		PersonaRepository dao;

		@BeforeEach
		void setUp() throws Exception {
			dao = mock(PersonaRepository.class);
			when(dao.load()).thenReturn(Persona.builder().id(1).nombre("Pepito").apellidos("Grillo").build());
			doThrow(new IllegalArgumentException()).when(dao).save(null);

			// ...
		}

		
		@Test
		void testLoad() {
			var p = dao.load();

			assertTrue(p instanceof Persona, "No es instancia de persona");
			assertAll("Validar propiedades",
				() -> assertEquals(1, p.getId(), "id"),
				() -> assertEquals("Pepito", p.getNombre(), "getNombre"),
				() -> assertEquals("Grillo", p.getApellidos().get(), "getApellidos")
			);
		}
		
		@Test
		void testSave() throws InvalidDataException {
			assertThrows(IllegalArgumentException.class, () -> dao.save(null));
		}
	}
	
}
	


