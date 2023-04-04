package com.example.domains.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import com.example.domains.contracts.repository.FilmRepository;
import com.example.domains.entities.Film;
import com.example.domains.entities.Film.Rating;
import com.example.domains.entities.Language;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

@SpringBootTest
@ComponentScan(basePackages = "com.example")
class FilmServiceImplTest {

	@Autowired
	FilmRepository dao;

	@Autowired
	FilmServiceImpl srv;

	@Test
	@DisplayName("getAll")
	void testGetAll() {
		assertThat(srv.getAll().size()).isGreaterThanOrEqualTo(1000);
	}

	@Test
	@DisplayName("getOne")
	void testGetOne() {
		var item = srv.getOne(1);
		assertTrue(item.isPresent());
	}

	@Test
	@DisplayName("Add film")
	void testAdd() throws DuplicateKeyException, InvalidDataException {
		var originalSize = srv.getAll().size();
		var film = new Film(0, "Film Title", "Description of the film", new Short("1990"), new Language(3),
				new Language(5), (byte) 10, new BigDecimal(20), 230, new BigDecimal(20), Rating.GENERAL_AUDIENCES);
		var result = srv.add(film);
		assertEquals(originalSize + 1, srv.getAll().size());
		srv.deleteById(result.getFilmId());

	}

	@Test
	@DisplayName("Modify film")
	void testModify() throws NotFoundException, InvalidDataException, DuplicateKeyException {

		var film = new Film(0, "Film Title", "Description of the film", new Short("1990"), new Language(3),
				new Language(5), (byte) 10, new BigDecimal(20), 230, new BigDecimal(20), Rating.GENERAL_AUDIENCES);
		var addFilm = srv.add(film);
		addFilm.setDescription("Film with modified description");
		var result = srv.modify(addFilm);
		assertEquals("Film with modified description", result.getDescription());
		assertEquals(addFilm.getFilmId(), result.getFilmId());// */
		srv.deleteById(result.getFilmId());
	}


	@Test
	@DisplayName("Modify film not found")
	void testModifyNotFound() throws NotFoundException, InvalidDataException {
		var film = new Film(0, "Film Title", "Description of the film", new Short("1990"), new Language(3),
				new Language(5), (byte) 10, new BigDecimal(20), 230, new BigDecimal(20), Rating.GENERAL_AUDIENCES);
		assertThrows(NotFoundException.class, () -> srv.modify(film));
	}

	@Test
	@DisplayName("Delete by id")
	void testDeleteById() throws InvalidDataException, NotFoundException, DuplicateKeyException {

		var film = new Film(0, "Film Title", "Description of the film", new Short("1990"), new Language(3),
				new Language(5), (byte) 10, new BigDecimal(20), 230, new BigDecimal(20), Rating.GENERAL_AUDIENCES);
		var addFilmID = srv.add(film).getFilmId();
		var fullSize = srv.getAll().size();
		srv.deleteById(addFilmID);
		assertEquals(fullSize - 1, srv.getAll().size());
	}

	@Test
	@DisplayName("Delete by id not exists")
	void testDeleteByIdNotExists() throws InvalidDataException, NotFoundException, DuplicateKeyException {

		var film = new Film(0, "Film Title", "Description of the film", new Short("1990"), new Language(3),
				new Language(5), (byte) 10, new BigDecimal(20), 230, new BigDecimal(20), Rating.GENERAL_AUDIENCES);
		var addFilmID = srv.add(film).getFilmId();
		var fullSize = srv.getAll().size();
		srv.deleteById(addFilmID + 1);
		assertEquals(fullSize, srv.getAll().size());
		srv.deleteById(addFilmID);
	}

}