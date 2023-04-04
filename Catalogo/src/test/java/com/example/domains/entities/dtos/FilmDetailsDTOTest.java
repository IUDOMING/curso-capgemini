package com.example.domains.entities.dtos;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.example.domains.entities.Actor;
import com.example.domains.entities.Film;
import com.example.domains.entities.Film.Rating;
import com.example.domains.entities.Language;

class FilmDetailsDTOTest {

	@Test
	void testFromFilm() {
		List<String> filmActorList = new ArrayList<String>();
		List<String> filmCategoryList = new ArrayList<String>();
		var film = new FilmDetailsDTO(0, "Film Title", "Description of the film", new Short("1894"), new Language(3),
				new Language(5), (byte) 10, new BigDecimal(20), new BigDecimal(20), 230, Rating.GENERAL_AUDIENCES,
				filmActorList, filmCategoryList);
		var filmDTO = FilmDetailsDTO.from(film);
		assertEquals(Film.class,filmDTO.getClass());
		
		assertAll("Attributes",
				() -> assertEquals(0,filmDTO.getFilmId()),
				() -> assertEquals("Film Title", filmDTO.getTitle()),
				() -> assertEquals("Description of the film",filmDTO.getDescription()),
				() -> assertEquals(230, filmDTO.getLength()),
				() -> assertEquals(Rating.GENERAL_AUDIENCES, filmDTO.getRating()),
				() -> assertEquals(new Short("1894"), filmDTO.getReleaseYear()),
				() -> assertEquals((byte) 10, filmDTO.getRentalDuration()),
				() -> assertEquals(new BigDecimal(20.0), filmDTO.getRentalRate()),
				() -> assertEquals(new BigDecimal(20), filmDTO.getReplacementCost()),
				() -> assertEquals(new Language(3), filmDTO.getLanguage()),
				() -> assertEquals(new Language(5), filmDTO.getLanguageVO())
				);
	}

	//conversión de FilmDTO a Film
	
	@Test
	void testFromFilmDTO() {
		var film = new Film(0, "Film Title", "Description of the film", new Short("1894"),
				new Language(3), new Language(5), (byte) 10, new BigDecimal(20),
				230, new BigDecimal(20), Rating.GENERAL_AUDIENCES);
		var filmDTO = FilmDetailsDTO.from(film);
		assertEquals(FilmDetailsDTO.class,filmDTO.getClass());
		
		assertAll("Attributes",
				() -> assertEquals(0,filmDTO.getFilmId()),
				() -> assertEquals("Film Title", filmDTO.getTitle()),
				() -> assertEquals("Description of the film",filmDTO.getDescription()),
				() -> assertEquals(230, filmDTO.getLength()),
				() -> assertEquals(Rating.GENERAL_AUDIENCES, filmDTO.getRating()),
				() -> assertEquals(new Short("1894"), filmDTO.getReleaseYear()),
				() -> assertEquals((byte) 10, filmDTO.getRentalDuration()),
				() -> assertEquals(new BigDecimal(20.0), filmDTO.getRentalRate()),
				() -> assertEquals(new BigDecimal(20), filmDTO.getReplacementCost()),
				() -> assertEquals(new Language(3), filmDTO.getLanguage()),
				() -> assertEquals(new Language(5), filmDTO.getLanguageVO())
				);
	}

}
