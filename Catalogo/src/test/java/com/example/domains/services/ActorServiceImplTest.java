package com.example.domains.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import com.example.domains.contracts.repository.ActorRepository;
import com.example.domains.entities.Actor;
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
	@DisplayName("Get not existent actor ")
	void testGetOneNotFound() {
		var item = srv.getOne(300);
		assertFalse(item.isPresent());
	}
	
	@Test
	void testAdd() throws DuplicateKeyException, InvalidDataException {
		
		var fullSize = srv.getAll().size();
		var actor = new Actor(0, "Jhon", "SNOW");
		var result = srv.add(actor);
		assertEquals(fullSize+1, srv.getAll().size());
		srv.deleteById(result.getActorId());
		
	}
		
	
	@Test
	@DisplayName("Modify actor")
//	@Disabled
	//Something is not working here
	void testModify() throws NotFoundException, InvalidDataException {
		var actor = new Actor(0, "Hola", "MUNDO");
		var adAct = srv.add(actor);
		adAct.setLastName("CIELO");
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
	@DisplayName("Delete by id not exists")
	void testDeleteByIdNotExists() throws InvalidDataException, NotFoundException{
		
		var actor = new Actor(0, "But", "INDRECIBLE");
		var addedActorId = srv.add(actor).getActorId();
		var originalSize = srv.getAll().size();
		srv.deleteById(addedActorId+1);
		assertEquals(originalSize, srv.getAll().size());
		srv.deleteById(addedActorId);
	}
}
