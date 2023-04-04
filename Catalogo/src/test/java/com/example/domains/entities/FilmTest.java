package com.example.domains.entities;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.example.domains.entities.Film.Rating;

class FilmTest {

	@Test
	@DisplayName("Partial Constructor")
	void testIsValid() {
		var item = new Film("English", new Language(0), (byte) 7, new BigDecimal(20), 123, new BigDecimal(20));
		assertTrue(item.isValid());
	}

	@Test
	@DisplayName("Complete Construcotr")
	void testValidComplete() {
		var item = new Film(0, "Film Title", "Description of the film", new Short("1990"), new Language(3),
				new Language(5), (byte) 10, new BigDecimal(20), 230, new BigDecimal(20), Rating.GENERAL_AUDIENCES);
		assertTrue(item.isValid());
	}

	@DisplayName("Invalid title")
	@ParameterizedTest(name = "Title: {0}, Error: {1}")
	@CsvSource(value = { "'  ','ERRORES: title: must not be blank.'",
			"'123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890','ERRORES: title: size must be between 0 and 128.'" })
	void testInvalidTest(String title, String errMsg) {
		var item = new Film(title, new Language(0), (byte) 7, new BigDecimal(20), 123, new BigDecimal(20));
		assertTrue(item.isInvalid());
		assertEquals(errMsg, item.getErrorsMessage());
	}

	@DisplayName("Language is Null")
	@Test
	void testInvalidLanguage() {
		var item = new Film("English", null, (byte) 7, new BigDecimal(20), 123, new BigDecimal(20));
		assertTrue(item.isInvalid());
		assertEquals("ERRORES: language: must not be null.", item.getErrorsMessage());
	}

	@DisplayName("Lenght is under 0")
	@ParameterizedTest(name = "Length: {0}, Error: {1}")
	@CsvSource(value = { "0,'ERRORES: length: must be greater than 0.'" })
	void testInvalidLength(Integer length, String errMsg) {
		var item = new Film("English", new Language(0), (byte) 7, new BigDecimal(20), length, new BigDecimal(20));
		assertTrue(item.isInvalid());
		assertEquals(errMsg, item.getErrorsMessage());
	}

	@Test
	@DisplayName("Invalid releaseYear")
	void testInvalidRealseseYEar() {
		var item = new Film(0, "Film Title", "Description of the film", new Short("1894"), new Language(3),
				new Language(5), (byte) 10, new BigDecimal(20), 230, new BigDecimal(20), Rating.GENERAL_AUDIENCES);
		assertEquals("ERRORES: releaseYear: must be greater than or equal to 1895.", item.getErrorsMessage());
	}

	@Test
	@DisplayName("Invalid rentalDuration")
	void testInvalidRentalDuration() {
		var item = new Film("English", new Language(0), (byte) -1, new BigDecimal(20), 123, new BigDecimal(20));
		assertTrue(item.isInvalid());
		assertEquals("ERRORES: rentalDuration: must be greater than 0.", item.getErrorsMessage());
	}

	@Test
	@DisplayName("Invalid rentalRate")
	void testInvalidRentalRate() {
		var item = new Film("English", new Language(0), (byte) 7, new BigDecimal(-20), 123, new BigDecimal(20));
		assertTrue(item.isInvalid());
		assertEquals("ERRORES: rentalRate: must be greater than 0.0, must be greater than 0.", item.getErrorsMessage());
	}

	@Test
	@DisplayName("Invalid replacementCost")
	void testInvalidRemplacementCost() {
		var item = new Film("English", new Language(0), (byte) 7, new BigDecimal(20), 123, new BigDecimal(-20));

		assertTrue(item.isInvalid());
		assertEquals("ERRORES: replacementCost: must be greater than 0.0.", item.getErrorsMessage());
	}

	@Test
	@DisplayName("Merge Test")
	void testMergeValid() {

		var film1 = new Film(0, "Film Title", "Description of the film", new Short("1990"), new Language(3),
				new Language(5), (byte) 10, new BigDecimal(20), 230, new BigDecimal(20), Rating.GENERAL_AUDIENCES);

		film1.addActor(new Actor(1, "James", "BOND"));
		film1.addActor(new Actor(2, "Samuel", "JACKSON"));
		film1.addActor(new Actor(3, "Jhon", "SNOW"));
		film1.addCategory(new Category(1, "Terror"));
		film1.addCategory(new Category(2, "Comedia"));
		film1.addCategory(new Category(3, "Romantica"));

		var film2 = new Film(0, "Film Title", "Description of the film", new Short("1990"), new Language(3),
				new Language(5), (byte) 10, new BigDecimal(20), 230, new BigDecimal(20), Rating.GENERAL_AUDIENCES);

		film1.addActor(new Actor(2, "Doc", "TIMETRAVEL"));
		film1.addActor(new Actor(3, "Someone", "IDONTKNOW"));
		film1.addActor(new Actor(4, "Should", "WORK"));
		film1.addCategory(new Category(1, "Drama"));
		film1.addCategory(new Category(3, "Thriller"));
		film1.addCategory(new Category(5, "Suspense"));

		var filmComb = film1.merge(film2);

		assertAll(
				() -> assertEquals(film2.getFilmId(), filmComb.getFilmId()),
				() -> assertEquals(film2.getDescription(), filmComb.getDescription()),
				() -> assertEquals(film2.getLanguage(), filmComb.getLanguage()),
				() -> assertEquals(film2.getLanguageVO(), filmComb.getLanguageVO()),
				() -> assertEquals(film2.getActors(), filmComb.getActors()),
				() -> assertEquals(film2.getCategories(), filmComb.getCategories()),
				() -> assertEquals(film2.getLength(), filmComb.getLength()),
				() -> assertEquals(film2.getRating(), filmComb.getRating()),
				() -> assertEquals(film2.getRentalRate(), filmComb.getRentalRate()),
				() -> assertEquals(film2.getRentalDuration(), filmComb.getRentalDuration()),
				() -> assertEquals(film2.getTitle(), filmComb.getTitle()),
				() -> assertEquals(film2.getReplacementCost(), filmComb.getReplacementCost()));

	}

}
