package com.example.demo.ioc;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

import com.example.core.test.SpaceCamelCase;
import com.example.demo.domains.core.validations.Validation;

class ValidationTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Nested
	@DisplayName("Pruebas de la validación NIFs")
	// @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
	@DisplayNameGeneration(SpaceCamelCase.class)
	class Validaciones {

		@Nested
		class OK {

			@ParameterizedTest(name = "Caso: {0}")
			@CsvSource(value = { "12345678Z", "45673254S", "47927767E", 
								 "77619560B", "77619559X", "34570706G",
								 "93259475h", "28859421x", "93237260k"})
			@NullSource
			void comprobaciónEnTanda(String nif) {
				assertTrue(Validation.validarDNI(nif));
			}

		}

		@Nested
		class KO {


			}
			@ParameterizedTest(name = "Caso: {0}")
			@CsvSource(value = { "00000000T", "00000001R", "99999999R", "0123", 
								 "01234a67Z", "012345678-", "0123456789",
								 "B77619560", "ABCDEFGHY","ABCDE FGH9"})
			@EmptySource
			void comprobaciónInvalidosEnTanda(String nif) {
				assertFalse(Validation.validarDNI(nif));
			}

		}
	}


