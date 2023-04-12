package com.example.domains.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ActorTest {
	@Test
	void testIsValid() {
		var fixture = new Actor(0, "Sandz", "GREEN");
		assertTrue(fixture.isValid());
	}

	@DisplayName("Name must be between 2 and 45. Can't be blank.")
	@ParameterizedTest(name = "fist name: -{0}- -> {1}")
	@CsvSource(value = { "'','ERRORES: firstName: size must be between 2 and 45, must not be blank.'",
			"' ','ERRORES: firstName: must not be blank, size must be between 2 and 45.'",
			"'   ','ERRORES: firstName: must not be blank.'",
			"A,'ERRORES: firstName: size must be between 2 and 45.'",
			"12345678901234567890123456789012345678901234567890,'ERRORES: firstName: size must be between 2 and 45.'" })
	void testNameIsInvalid(String value, String error) {
		var fixture = new Actor(0, value, "GRILLO");
		assertTrue(fixture.isInvalid());
		assertEquals(error, fixture.getErrorsMessage());
	}

	@DisplayName("Last Name must be between 2 and 45. Can't be blank. Must be Uppercase.")
	@ParameterizedTest(name = "last name: -{0}- -> {1}")
	@CsvSource(value = { "'','ERRORES: lastName: size must be between 2 and 45, Must be uppercase.'",
			"' ','ERRORES: lastName: size must be between 2 and 45, Must be uppercase.'",
			"'   ','ERRORES: lastName: Must be uppercase.'",
			"A,'ERRORES: lastName: size must be between 2 and 45.'",
			"12345678901234567890123456789012345678901234567890,'ERRORES: lastName: Must be uppercase, size must be between 2 and 45.'" })
	void testLastNameIsInvalid(String value, String error) {
		var fixture = new Actor(0, "Sanz", value);
		assertTrue(fixture.isInvalid());
		assertEquals(error, fixture.getErrorsMessage());
	}

}