package com.example.domains.entities.dtos;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.domains.entities.Actor;

class ActorDTOTest {

	@Test
	void testTransformToActor() {
		var actor = ActorDTO.from(new ActorDTO(0, "Sangui", "GEMINI"));
		assertEquals(Actor.class, actor.getClass());

		assertAll( () -> assertEquals(0, actor.getActorId()),
				() -> assertEquals("Sangui", actor.getFirstName()),
				() -> assertEquals("GEMINI", actor.getLastName()));
	}

	@Test
	void testFTransformToDTO() {
		var actorDTO = ActorDTO.from(new Actor(0, "Masiem", "LOREPI"));
		assertEquals(ActorDTO.class, actorDTO.getClass());

		assertAll(() -> assertEquals(0, actorDTO.getActorId()),
				() -> assertEquals("Masiem", actorDTO.getFirstName()),
				() -> assertEquals("LOREPI", actorDTO.getLastName()));
	}

}