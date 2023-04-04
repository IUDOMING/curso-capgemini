package com.example.domains.entities.dtos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.domains.entities.Film;
import com.example.domains.entities.Language;

class FilmShortDTOTest {

	@Test
	void testFilmShortDTO() {
		var filmDTO = FilmShortDTO.from(new Film("Film Title", new Language(1)));
		assertEquals(FilmShortDTO.class,filmDTO.getClass());
		
		assertAll("Attributes",
				() -> assertEquals("Film Title",filmDTO.getTitle()),
				() -> assertEquals(0,filmDTO.getFilmId()));
	}

}