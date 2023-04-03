package com.example.domains.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CategoryTest {
	@Test
	void testIsValid() {
		var fixture = new Category(0, "Terror");
		assertTrue(fixture.isValid());
	}

	@DisplayName("Category must be between and 25. Can't be blank.")
	@ParameterizedTest(name = "language: -{0}- -> {1}")
	@CsvSource(value = { "'','ERRORES: name: must not be blank.'",
			"' ','ERRORES: name: must not be blank.'",
			"'   ','ERRORES: name: must not be blank.'",
			"12345678901234567890123456789012345678901234567890,'ERRORES: name: size must be between 0 and 25.'" })
	void testNameIsInvalid(String value, String error) {
		var fixture = new Category(0, value);
		assertTrue(fixture.isInvalid());
		assertEquals(error, fixture.getErrorsMessage());
	}

}