package com.example.domains.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import com.example.domains.contracts.repository.ActorRepository;
import com.example.domains.entities.Actor;
import com.example.domains.entities.Film;
import com.example.domains.entities.Language;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

@SpringBootTest
@ComponentScan(basePackages = "com.example")
class ActorServiceImplTest {

	@Autowired
	ActorRepository dao;
	
	@Autowired
	ActorServiceImpl srv;

	@Test
	@DisplayName("getAll")
	void testGetAll() {
		assertThat(srv.getAll().size()).isGreaterThanOrEqualTo(200);
	}

	@Test
	@DisplayName("getOne")
	void testGetOne() {
		var item = srv.getOne(1);
		assertTrue(item.isPresent());
	}
	
	@Test
	@DisplayName("Get invalid ID")
	void testNotFoundGet() {
		var item = srv.getOne(300);
		assertFalse(item.isPresent());
	}
	
	@Test
	@DisplayName("Add Actor")
	void testAdd() throws DuplicateKeyException, InvalidDataException {
		
		var fullSize = srv.getAll().size();
		var actor = new Actor(0, "Jhon", "SNOW");
		var result = srv.add(actor);
		assertEquals(fullSize+1, srv.getAll().size());
		srv.deleteById(result.getActorId());
		
	}
	
	@Test
	@DisplayName("Add null actor")
	void testNullAdd() throws DuplicateKeyException, InvalidDataException {
		assertThrows(InvalidDataException.class, () -> srv.add(null));
	}

	@Test
	@DisplayName("Add invalid actor")
	void testInvalidAdd() throws DuplicateKeyException, InvalidDataException {
		assertThrows(InvalidDataException.class, () -> srv.add(new Actor(0,"     ", "      ")));
	}
		
	@Test
	@DisplayName("Modify null actor")
	void testNullModify() throws NotFoundException, InvalidDataException {
		assertThrows(InvalidDataException.class, () -> srv.modify(null));
	}
	
	@Test
	@DisplayName("Modify invalid actor")
	void testInvalidModify() throws NotFoundException, InvalidDataException {
		assertThrows(InvalidDataException.class, () -> srv.modify(new Actor(0," ","Invalid")));
	}
	
	@Test
	@DisplayName("Modify Not Found Actor")
	void testNotFoundModify() throws NotFoundException, InvalidDataException {
		assertThrows(NotFoundException.class, () -> srv.modify(new Actor(0,"Inexistent","WHERE")));
	}
	@Test
	@DisplayName("Modify actor")
	void testModify() throws NotFoundException, InvalidDataException {
		var actor = new Actor(0, "Hola", "MUNDO");
		srv.add(actor);
		actor.setLastName("CIELO");
		var result = srv.modify(actor);
		assertEquals("CIELO", result.getLastName());
		assertEquals(actor.getActorId(), result.getActorId());
		srv.deleteById(result.getActorId());
	}
	
	@Test
	@DisplayName("Delete by id")
	void testDeleteById() throws InvalidDataException, NotFoundException{
		
		var actor = new Actor(0, "Someone", "AGAIN");
		var addActID = srv.add(actor).getActorId();
		var fullSize = srv.getAll().size();
		srv.deleteById(addActID);
		assertEquals(fullSize-1, srv.getAll().size());
	}
	
	@Test
	@DisplayName("Delete by Non Existing Id")
	void testDeleteByNonExistingId() throws InvalidDataException, NotFoundException{
		
		var actor = new Actor(0, "Name", "ACTOR");
		var addActId = srv.add(actor).getActorId();
		var fullSize = srv.getAll().size();
		srv.deleteById(addActId+1);
		assertEquals(fullSize, srv.getAll().size());
		srv.deleteById(addActId);
	}
	
	@Test
	@DisplayName("Delete null")
	void testDelete() {
		assertThrows(InvalidDataException.class, () -> srv.delete(null));
	}
}
