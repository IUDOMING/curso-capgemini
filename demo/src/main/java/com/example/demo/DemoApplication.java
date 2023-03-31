package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.example.demo.domains.contracts.services.ActorService;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	ActorService srv;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Aplicación arrancada");
//		(new EjemplosC()).run();
//		var actor = new Actor(0, "Pepito2", "grillo2");
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
//		var item = dao.findById(1);
//		if (item.isPresent()) {
//			var actor = item.get();
//			System.out.println(actor);
//			actor.getFilmActors().forEach(o -> System.out.println(o.getFilm().getTitle()));
//			// dao.findAll().forEach(System.out::println);
//		} else {
//			System.out.println("Actor no encontrado");
//		}

		// Valida la información y en caso de fallo da información sobre las violaciones
		// en las reglas especificadas.
		// Forma manual de hacerlo
//		var actor = new Actor(0, "Pepito", "grillo");
//		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
//		var err = validator.validate(actor);
//		if (err.size() > 0) {
//			err.forEach(e -> System.out.println(e.getPropertyPath() + ": " + e.getMessage()));
//		} else
//			dao.save(actor);
		// Mediante clases y anotaciones
//		if(actor.isInvalid())
//			System.out.println(actor.getErrorsMessage());
//		else
//			dao.save(actor);

		// Pides la página 1 (la primera página es la 0, y cada página tiene 20 filas)
		// Mostrará del 21 al 40
//		var rslt=dao.findAll(PageRequest.of(1, 20, Sort.by("actorId")));
//		rslt.getContent().forEach(System.out::println);

		// Haciendo la forma manual para la proyección
//		var rslt=dao.findAll(PageRequest.of(1, 20, Sort.by("actorId")));
//		rslt.getContent().stream().map(item -> ActorDTO.from(item).forEach(System.out::println));

//		dao.findByActorIdNotNull().forEach(System.out::println);
//		dao.findByActorIdNotNull().forEach(item -> System.out.println(item.getActorId()+ " " + item.getNombre()));
//		dao.findAllBy(ActorShort.class).forEach(item -> System.out.println(item.getActorId()+ " " + item.getNombre()));
//		dao.findAllBy(ActorDTO.class).forEach(System.out::println);

		// Obtenemos la info en formato JSON
//		ObjectMapper objectMapper = new ObjectMapper();
//		dao.findAllBy(ActorDTO.class).stream().map(item -> {
//			try {
//				return objectMapper.writeValueAsString(item);
//			} catch (JsonProcessingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			return null;
//		}).forEach(System.out::println);
		
		
		//Updateing branch
	}

}
