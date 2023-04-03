package com.example.domains.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LanguageTest {

	@Test
	void testIsValid() {
		var fixture = new Language(0, "Mandarin");
		assertTrue(fixture.isValid());
	}

	@DisplayName("Name must be between 2 and 45. Can't be blank.")
	@ParameterizedTest(name = "fist name: -{0}- -> {1}")
	@CsvSource(value = {
			"'','ERRORES: firstName: size must be between 2 and 45, must not be blank.'", 
			"' ','ERRORES: firstName: size must be between 2 and 45, must not be blank.'", 
			"'   ','ERRORES: firstName: must not be blank.'", 
			"A,'ERRORES: firstName: size must be between 2 and 45.'",
			"12345678901234567890123456789012345678901234567890,'ERRORES: firstName: size must be between 2 and 45.'"})
	
	void testNombreIsInvalid(String value, String error) {
		var fixture = new Language(0, value);
		assertTrue(fixture.isInvalid());
		assertEquals(error, fixture.getErrorsMessage());
	}

}