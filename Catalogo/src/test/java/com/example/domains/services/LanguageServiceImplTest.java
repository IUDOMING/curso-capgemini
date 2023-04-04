package com.example.domains.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import com.example.domains.contracts.repository.LanguageRepository;
import com.example.domains.entities.Language;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

@SpringBootTest
@ComponentScan(basePackages = "com.example")
class LanguageServiceImplTest {

	@Autowired
	LanguageRepository dao;

	@Autowired
	LanguageServiceImpl srv;

	@Test
	@DisplayName("getAll")
	void testGetAll() {
		assertThat(srv.getAll().size()).isEqualTo(6);
	}

	@Test
	@DisplayName("getOne")
	void testGetOne() {
		var item = srv.getOne(1);
		assertTrue(item.isPresent());
	}

	@Test
	@DisplayName("Get invalid ID")
	void testGetOneNotFound() {
		var item = srv.getOne(8);
		assertFalse(item.isPresent());
	}

	@Test
	@DisplayName("Add Language")
	void testAdd() throws DuplicateKeyException, InvalidDataException {
		var fullSize = srv.getAll().size();
		var language = new Language(0, "Russian");
		var result = srv.add(language);
		assertEquals(fullSize + 1, srv.getAll().size());
		srv.deleteById(result.getLanguageId());

	}

	@Test
	@DisplayName("Modify Language")
	void testModify() throws NotFoundException, InvalidDataException {

		var language = new Language(0, "German");
		var addLang = srv.add(language);
		addLang.setName("Language modified");
		var result = srv.modify(language);
		assertEquals("Language modified", result.getName());
		assertEquals(language.getLanguageId(), result.getLanguageId());
		srv.deleteById(result.getLanguageId());
	}

	@Test
	@DisplayName("Delete by id")
	void testDeleteById() throws InvalidDataException, NotFoundException {
		var language = new Language(0, "Language to delete");
		var addLangId = srv.add(language).getLanguageId();
		var fullSize = srv.getAll().size();
		srv.deleteById(addLangId);
		assertEquals(fullSize - 1, srv.getAll().size());
	}

	@Test
	@DisplayName("Delete by id not exists")
	void testDeleteByIdNotExists() throws InvalidDataException, NotFoundException {

		var language = new Language(0, "Language to delete");
		var addedLanguageId = srv.add(language).getLanguageId();
		var originalSize = srv.getAll().size();
		srv.deleteById(addedLanguageId + 1);
		assertEquals(originalSize, srv.getAll().size());
		srv.deleteById(addedLanguageId);
	}
}