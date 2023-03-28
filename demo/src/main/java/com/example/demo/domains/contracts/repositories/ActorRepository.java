package com.example.demo.domains.contracts.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.domains.entities.Actor;

public interface ActorRepository extends JpaRepository<Actor, Integer>, JpaSpecificationExecutor<Actor> {
	
	List<Actor> findTop5ByFirstNameStartingWithOrderByLastNameDesc(String prefijo);

	List<Actor> findTop5ByFirstNameStartingWith(String prefijo, Sort orden);

	// Consultamos todos los datos de actores donde la id del actor es menor al
	// parametro que se pasará
	@Query("SELECT a from Actor a WHERE a.actorId < :id")

	// Mediante el ":id" indicamos cual es la "columna" que deberá usar respecto al
	// objeto, en la tabla
	List<Actor> findConJPQL(@Param("id") int actorId);

	// Sobrecarga de método
	@Query(name = "Actor.findAll")
	List<Actor> findConJPQL();

	// Cuando queremos usar directamente SQL usamos estas líneas
	// El "?1" indica lugares donde se sustituirá por el valor apoetado
	// Si hubiera varios valores que esámos ofreciendo usaríamos ?1/?2/?3... para
	// indicar cual en cada sitio
	@Query(value = "SELECT * from actor WHERE actor_id <?1", nativeQuery = true)
	List<Actor> findConSQL(int actorId);
}
