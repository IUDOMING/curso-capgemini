package com.example.domains.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;

import com.example.domains.contracts.repository.FilmRepository;
import com.example.domains.contracts.services.FilmServices;
import com.example.domains.entities.Actor;
import com.example.domains.entities.Film;
import com.example.domains.entities.Language;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

@DataJpaTest
@ComponentScan(basePackages = "com.example")
class FilmServiceImplTest {

	@MockBean
	FilmRepository dao;

	@Autowired
	FilmServices srv;

	@Test
	void testGetAll_isNotEmpty() {
		List<Film> list = new ArrayList<>(
				Arrays.asList(new Film("Hola mundo", new Language(2), (byte)1, new BigDecimal(10.0), 1, new BigDecimal(10.0)),
						new Film("Hola mundo", new Language(2), (byte)1, new BigDecimal(10.0), 1, new BigDecimal(10.0)), 
						new Film("Hola mundo", new Language(2), (byte)1, new BigDecimal(10.0), 1, new BigDecimal(10.0))));

		when(dao.findAll()).thenReturn(list);
		var rslt = srv.getAll();
		assertThat(rslt.size()).isEqualTo(3);
	}

	@Test
	void testGetOne_valid() {
		List<Film> list = new ArrayList<>(
				Arrays.asList(new Film(1, "Fraiz", "FRANK", null, null, null, null, null, null, null, null),
						new Film(2, "Samuel", "JACKSON", null, null, null, null, null, null, null, null),
						new Film(3, "Varios", "ALEATORIOS", null, null, null, null, null, null, null, null)));

		when(dao.findById(1))
				.thenReturn(Optional.of(new Film(1, "Fraiz", "FRANK", null, null, null, null, null, null, null, null)));
		var rslt = srv.getOne(1);
		assertThat(rslt.isPresent()).isTrue();

	}

	@Test
	void testGetOne_notfound() {
		when(dao.findById(1)).thenReturn(Optional.empty());
		var rslt = srv.getOne(1);
		assertThat(rslt.isEmpty()).isTrue();

	}

	@Test
	void testAdd() throws DuplicateKeyException, InvalidDataException {
		when(dao.save(any(Film.class))).thenReturn(null, null);
		assertThrows(InvalidDataException.class, () -> srv.add(null));
		verify(dao, times(0)).save(null);
	}

	@Test
	void testModify() throws NotFoundException, InvalidDataException {
		when(dao.save(any(Film.class))).thenReturn(null, null);
		assertThrows(InvalidDataException.class, () -> srv.modify(null));
		verify(dao, times(0)).save(null);
	}

	@Test
	void testDelete() throws InvalidDataException {
		List<Film> list = new ArrayList<>(
				Arrays.asList(new Film(1, "Fraiz", "FRANK", null, null, null, null, null, null, null, null),
						new Film(2, "Samuel", "JACKSON", null, null, null, null, null, null, null, null),
						new Film(3, "Varios", "ALEATORIOS", null, null, null, null, null, null, null, null)));

		when(dao.findAll()).thenReturn(list);
		dao.delete(new Film(1, "Fraiz", "FRANK", null, null, null, null, null, null, null, null));
		var rslt = srv.getOne(2);

		assertThat(rslt.isPresent()).isFalse();

	}

	@Test
	void testDeleteById() {
		List<Film> list = new ArrayList<>(
				Arrays.asList(new Film(1, "Fraiz", "FRANK", null, null, null, null, null, null, null, null),
						new Film(2, "Samuel", "JACKSON", null, null, null, null, null, null, null, null),
						new Film(3, "Varios", "ALEATORIOS", null, null, null, null, null, null, null, null)));

		when(dao.findAll()).thenReturn(list);
		dao.deleteById(3);
		var rslt = srv.getOne(3);
		assertThat(rslt.isPresent()).isFalse();

	}

}