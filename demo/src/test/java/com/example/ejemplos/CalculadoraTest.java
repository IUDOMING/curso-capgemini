package com.example.ejemplos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalculadoraTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testSuma() {
		
		var calc = new Calculadora();
		
		var rslt = calc.suma(2, 2);
		
		assertEquals(4, rslt);
		
	}
	
	@Test
	void testSumaPositivoNegativo() {
		
		var calc = new Calculadora();
		
		var rslt = calc.suma(3, -1);
		
		assertEquals(2, rslt);
		
	}
	
	@Test
	void testSumaNegativoPositivo() {
		
		var calc = new Calculadora();
		
		var rslt = calc.suma(-1, 5);
		
		assertEquals(4, rslt);
		
	}
	
	@Test
	void testSumaDecimales() {
		
		var calc = new Calculadora();
		
		var rslt = calc.suma(0.2, 0.5);
		
		assertEquals(0.7, rslt);
		
	}


}
