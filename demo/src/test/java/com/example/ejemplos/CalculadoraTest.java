package com.example.ejemplos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class CalculadoraTest {
	
	//Se crea el objeto calculadora
	Calculadora calc;

	@BeforeEach
	void setUp() throws Exception {
		
		//Usamos el ciclo de vida para ciertas situaciones generales.
		//Se declara que instancie una Calculadora antes de cada test.
		calc = new Calculadora();
		
	}
	
	//Se marca con la anotación nested para que sepa que funciona con las pruebas
	@Nested
	@DisplayName("Pruebas del método Suma")
	@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
	class Suma{
		//
		@Nested
		class OK{
			@Test
			void test_Suma_Positivo_Positivo() {
				
				//Ya no es necesario instanciar una calculadora en cada test
				//var calc = new Calculadora();
				
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
			
			
		}	
		@Nested
		class KO{
			
			
		}
		
	}
	
	//Se marca con la anotación nested para que sepa que funciona con las pruebas
	@Nested
	class Dividir{
		//
		@Nested
		class OK{
			
			void testDividirPorCero() {
				var calc = new Calculadora();
								
				var rslt = calc.divide(1, 0.0);
				
				assertEquals(Double.POSITIVE_INFINITY, rslt);
			}
			
						
		}	
		@Nested
		class KO{
			@Test
			void testDividirPorCero() {
				var calc = new Calculadora();
								
				assertThrows(ArithmeticException.class, ()->calc.divide(1, 0));

			}
			
			
		}
		
	}





}
