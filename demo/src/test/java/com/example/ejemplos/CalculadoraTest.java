package com.example.ejemplos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.example.core.test.SpaceCamelCase;

// De forma predeterminada el orden es "aleaotrio" según algorítmo interno de JUnit
// Para dar una orden a los tests
@TestMethodOrder(MethodOrderer.DisplayName.class)
class CalculadoraTest {

	// Se crea el objeto calculadora
	Calculadora calc;

	@BeforeEach
	void setUp() throws Exception {

		// Usamos el ciclo de vida para ciertas situaciones generales.
		// Se declara que instancie una Calculadora antes de cada test.
		calc = new Calculadora();

	}

	// Se marca con la anotación nested para que sepa que funciona con las pruebas
	@Nested
	@DisplayName("Pruebas del método Suma")
	//@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
	@DisplayNameGeneration(SpaceCamelCase.class)
	class Suma {
		//
		@Nested
		class OK {
			
			@Test
			void test_Suma_Positivo_Positivo() {

				// Ya no es necesario instanciar una calculadora en cada test
				// var calc = new Calculadora();

				var rslt = calc.suma(2, 2);

				assertEquals(4, rslt);
			}

			@Test
			void testSumaPositivoNegativo() {
				var calc = new Calculadora();

				var rslt = calc.suma(1, -0.9);

				assertEquals(0.1, rslt);
			}

			@Test
			void testSumaNegativoPositivo() {
				var calc = new Calculadora();

				var rslt = calc.suma(-1, 5);

				assertEquals(4, rslt);
			}

			@Test
			void testSumaNegativoNegativo() {
				var calc = new Calculadora();

				var rslt = calc.suma(-1, -5);

				assertEquals(-6, rslt);
			}

			@Test
			void testSumaDecimales() {
				var calc = new Calculadora();

				var rslt = calc.suma(0.1, 0.2);

				assertEquals(0.3, rslt);
			}
			
			
			
			@Test
			// Se salta la prueba y no la hace
			//@Disabled
			//Solo que no pase una prueba, se detiene completamente
			void testSumaMultiple() {
				assertEquals(2, calc.suma(1,1));
				assertEquals(0, calc.suma(-1,1));
				assertEquals(0, calc.suma(1,-1));
				assertEquals(-2, calc.suma(-1,-1));
				assertEquals(0, calc.suma(0,0));
				assumeTrue(false, "La tengo a medias");
			}

			// Muestra información sobre el test
			// En este caso, le damos el nombre de los valores de los operandos y resultado
			@ParameterizedTest(name = "{0}+{1} = {2}")
			// Se le pasan los valores (Los datos de CSV vienen en colecciones)
			// Si puede hacer la transofmración de los valores, haría el cambio
			// Si pasaramos un String "1" lo transformaría, pero no si pasaramos "1€"
			@CsvSource(value = { "1,1,2", "0.1,0.2,0.3", "0,0,0", "-1,1,0", "1,-1,0", "-1,-1,-2" })
			void testSumaParametrizado(double op1, double op2, double rslt) {
				assertEquals(rslt, calc.suma(op1, op2));

			}
			
			//Ejemplo de Test Mock
			@Test
			void testSumaMock() {
				Calculadora calc = mock(Calculadora.class);
				//Le indicas el resultado que quieres que te de cuando se de X situación
				when(calc.suma(2, 2)).thenReturn(3.0);
				//Cuando se da la situación indicada, pasa la prueba (pese a que fuera erronea)
				assertEquals(3, calc.suma(2, 2));
			}
			
			//Ejemplo de Test Mock
			@Test
			void testSumaMockAny() {
				Calculadora calc = mock(Calculadora.class);
				//Le indicas el resultado que quieres que te de cuando se de X situación
				when(calc.suma(anyDouble(),anyDouble())).thenReturn(3.0);
				//Cuando se da la situación indicada, pasa la prueba (pese a que fuera erronea)
				assertEquals(3, calc.suma(2, 2));
			}

		}

		@Nested
		class KO {

		}

	}

	// Se marca con la anotación nested para que sepa que funciona con las pruebas
	@Nested
	class Dividir {
		//
		@Nested
		class OK {
			@Test
			void testDividirPorCero() {
				var calc = new Calculadora();

				var rslt = calc.divide(1, 1);

				assertEquals(1, rslt);
			}

		}

		@Nested
		class KO {
			@Test
			void testDividirPorCero() {
				var calc = new Calculadora();

				assertThrows(ArithmeticException.class, () -> calc.divide(1, 0.0));

			}

		}

	}

}
