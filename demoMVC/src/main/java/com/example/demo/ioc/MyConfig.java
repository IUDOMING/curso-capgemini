package com.example.demo.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
public class MyConfig {

	@Bean
	//Si hay @Profiles, todos los métodos deberían llevar @Profile
	@Profile("tool")
	UnaTonteria unaTonteriaX() {
		// Estas creando un método que devolverá el objeto UnaTonteria
		// Por ello, el return devuelve el Objeto y se desarrolla ya que viene de una
		// interfaz.
		return new UnaTonteria() {

			@Override
			public String dimeAlgo() {
				return "Dice una tonteria con tool";
			}
		};

	}
	@Bean
	@Profile("default")
	UnaTonteria unaTonteria() {
		return new UnaTonteria() {
			@Override
			public String dimeAlgo() {
				return "Dice una tonteria";
			}
		}; 
	}
	@Bean
	@Profile("test")
	UnaTonteria unaTonteriaTest() {
		return new UnaTonteria() {
			@Override
			public String dimeAlgo() {
				return "Dice una tonteria para el test";
			}
		}; 
	}
	@Bean
	@Profile("prod")
	UnaTonteria unaTonteriaProd() {
		return new UnaTonteria() {
			@Override
			public String dimeAlgo() {
				return "Dice una tonteria en producción";
			}
		}; 
	}

}
