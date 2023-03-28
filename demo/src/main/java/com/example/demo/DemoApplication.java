package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import com.example.demo.domains.contracts.repositories.ActorRepository;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	ActorRepository dao;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Aplicación arrancada");
//		(new EjemplosC()).run();
//		var actor = new Actor(0, "Pepito", "grillo");
//		dao.save(actor);
//		dao.deleteById(201);
//		var item = dao.findById(201);
//		if(item.isPresent()) {
//			var actor = item.get();
//			actor.setLastName(actor.getLastName().toUpperCase());
//			dao.save(actor);
//			dao.findAll().forEach(System.out::println);
//		} else {
//			System.out.println("Actor no encontrado");
//		}
//		dao.findTop5ByFirstNameStartingWithOrderByLastNameDesc("P")
//			.forEach(System.out::println);
//		dao.findTop5ByFirstNameStartingWith("P", Sort.by("LastName").descending())
//			.forEach(System.out::println);
//		dao.findTop5ByFirstNameStartingWith("P", Sort.by("FirstName"))
//		.forEach(System.out::println);
//		dao.findConJPQL().forEach(System.out::println);
//		dao.findConJPQL(5).forEach(System.out::println);
//		dao.findConSQL(5).forEach(System.out::println);

		// Mediante JpaSpecificationExecute
//		dao.findAll((root, query, builder) -> builder.lessThan(root.get("actorId"), 5))
//		.forEach(System.out::println);
//		dao.findAll((root, query, builder) -> builder.greaterThan(root.get("actorId"), 200))
//		.forEach(System.out::println);

		// Cuando usamos FetchType EAGER
		// En un método a parte se debería definir @Transactional para evitar que se
		// cierre la consulta
		// Y pueda conseguir más datos dentro de la tabla. (No cierra la conexión hasta
		// que se acaba el método)
		var item = dao.findById(1);
		if (item.isPresent()) {
			var actor = item.get();
			System.out.println(actor);
			actor.getFilmActors().forEach(o -> System.out.println(o.getFilm().getTitle()));
			// dao.findAll().forEach(System.out::println);
		} else {
			System.out.println("Actor no encontrado");
		}
	}

}
