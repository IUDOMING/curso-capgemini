package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.ioc.Rango;
import com.example.demo.ioc.StringService;
import com.example.demo.ioc.UnaTonteria;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

	}

	@Autowired
	@Qualifier("Remote")
	private StringService srv;

	@Autowired
	@Qualifier("Local")
	private StringService srvLocal;

	// Introducimos el valor por defecto que se encuentra en application.properties,
	// usando el nombre que tiene en
	// dicho documento
	// Por lo general, la propia inyección te hace el casting si es posible
	//Sin valor: cuando no encuentra el valor
	@Value("${mi.valor:(Sin valor)}")
	private String config;

	@Autowired
	private Rango rango;

	/*
	 * @Autowired //Busca donde está la anotación @Components o derivados y lo
	 * instancia. private StringServiceImpl srv;
	 */

	@Autowired
	UnaTonteria tonteria;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Aplicación arrancada");

		// Inyección manual
		/*
		 * StringRepository dao = new StringRepositoryImpl(); dao = new
		 * StringRepositoryImplMock(); var srv= new StringServiceImpl(dao);
		 */
		System.out.println(srv.get(1));
		System.out.println(srvLocal.get(1));
		System.out.println(rango.toString());
		System.out.println(rango.getMax() + rango.getMin());
		//System.out.println(tonteria.dimeAlgo());
		System.out.println(tonteria != null ? tonteria.dimeAlgo() : "Tonteria nula");
		System.out.println(config);
		

	}

}
