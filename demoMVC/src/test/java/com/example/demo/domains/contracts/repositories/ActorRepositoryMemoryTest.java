package com.example.demo.domains.contracts.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.example.demo.domains.entities.Actor;


@DataJpaTest
class ActorRepositoryMemoryTest {
	@Autowired
	private TestEntityManager em;

	@Autowired
	ActorRepository dao;

	@BeforeEach
	void setUp() throws Exception {
		var item = new Actor(0, "Pepito", "GRILLO");
		item.setLastUpdate(Timestamp.valueOf("2019-01-01 00:00:00"));
		em.persist(item);
		item = new Actor(0, "Carmelo", "COTON");
		item.setLastUpdate(Timestamp.valueOf("2019-01-01 00:00:00"));
		em.persist(item);
		item = new Actor(0, "Capitan", "TAN");
		item.setLastUpdate(Timestamp.valueOf("2019-01-01 00:00:00"));
		em.persist(item);
	}

	@Test
	void testAll() {
		assertEquals(3, dao.findAll().size());
		assertEquals(1, dao.findAll().get(0).getActorId());
	}
	@Test
	void testOne() {
		var item = dao.findById(1);
		
		assertTrue(item.isPresent());
		assertEquals("Pepito", item.get().getFirstName());
	}
	@Test
	void testSave() {
		var item = dao.save(new Actor(0, "Demo", "GUARDAR"));
		
		assertNotNull(item);
		assertEquals(4, item.getActorId());
	}

}