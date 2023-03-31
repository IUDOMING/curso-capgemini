package com.example.demo.domains.contracts.repositories;

import static org.junit.jupiter.api.Assertions.fail;

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
		var item = new Actor(0 , "Pepito", "GRILLO");
		
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
