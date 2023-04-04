package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;

import com.example.demo.DemoApplicationTests.IoCTestConfig;
import com.example.demo.ioc.StringRepository;
import com.example.demo.ioc.StringRepositoryImplMock;

@SpringBootTest
//@Disabled
class DemoApplicationTests {

	@Autowired
	StringRepository dao;

	@Test
	void contextLoads() {

		assertEquals("Soy el StringRepositoryImpl", dao.load());

	}

	public static class IoCTestConfig {
		@Bean
		StringRepository getServicio() {
			return new StringRepositoryImplMock();
		}
	}

	// Estamos configurando el test para que use el ioCTestConfig
	// Con ello, instanciamos un objeto del Mock porque así se lo indicamos
	// (StringRepositoryImplMock)
	@Nested

	// Al ser una clase "general" puede usar el ContextConfiguration
	// INdica de que clase va a coger la configurarión
	@ContextConfiguration(classes = IoCTestConfig.class)
	class IoCTest {
		@Autowired
		StringRepository dao;

		@Test
		void contextLoads() {

			assertEquals("Soy el doble de StringRepository y Mock", dao.load());

		}

	}

	// Puedes fijar el comportamiento de los tests
	@Nested
	public static class IoCUnicoTestConfig {

		// Como es una clase interior, no puede usar el ContextConfiguration
		// Al estra dentro de otra clase, tienes que definir que esa es la configuración
		// del test
		// de la propia clase
		@TestConfiguration
		public static class IoCParticularTestConfig {
			@Bean
			StringRepository getServicio() {
				return new StringRepositoryImplMock();
			}
		}

		@Autowired
		StringRepository dao;

		@Test
		void contextLoads() {

			assertEquals("Soy el doble de StringRepository y Mock", dao.load());

		}

	}

}
