package com.example.domains.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import com.example.domains.contracts.repository.CategoryRepository;
import com.example.domains.entities.Category;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

@SpringBootTest
@ComponentScan(basePackages = "com.example")
class CategoryServiceImplTest {

	@Autowired
	CategoryRepository dao;

	@Autowired
	CategoryServiceImpl srv;

	@Test
	@DisplayName("getAll")
	void testGetAll() {
		assertThat(srv.getAll().size()).isGreaterThanOrEqualTo(16);
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
		var item = srv.getOne(36);
		assertFalse(item.isPresent());
	}

	@Test
	@DisplayName("Add category")
	void testAdd() throws DuplicateKeyException, InvalidDataException {
		var fullSize = srv.getAll().size();
		var category = new Category(0, "Category");
		var result = srv.add(category);
		assertEquals(fullSize + 1, srv.getAll().size());
		srv.deleteById(result.getCategoryId());
	}

	@Test
	@DisplayName("Add null category")
	void testNullAdd() throws DuplicateKeyException, InvalidDataException {
		assertThrows(InvalidDataException.class, () -> srv.add(null));
	}

	@Test
	@DisplayName("Add invalid category")
	void testInvalidAdd() throws DuplicateKeyException, InvalidDataException {
		assertThrows(InvalidDataException.class, () -> srv.add(new Category(0, " ")));
	}

	@Test
	@DisplayName("Modify category")
	void testModify() throws NotFoundException, InvalidDataException {
		var category = new Category(0, "Category 1");
		srv.add(category);
		category.setName("Category 2");
		var result = srv.modify(category);
		assertEquals("Category 2", result.getName());
		assertEquals(category.getCategoryId(), result.getCategoryId());
		srv.deleteById(result.getCategoryId());
	}

	@Test
	@DisplayName("Null Modify Category")
	void testNullModify() throws NotFoundException, InvalidDataException {
		assertThrows(InvalidDataException.class, () -> srv.modify(null));
	}

	@Test
	@DisplayName("Invalid Modify Category")
	void testInvalidModify() throws NotFoundException, InvalidDataException {
		assertThrows(InvalidDataException.class, () -> srv.modify(new Category(0, " ")));
	}

	@Test
	@DisplayName("Not Found Modify Category")
	void testNotFOundModify() throws NotFoundException, InvalidDataException {
		assertThrows(NotFoundException.class, () -> srv.modify(new Category(0, "Not Found")));
	}

	@Test
	@DisplayName("Delete by id")
	void testDeleteById() throws InvalidDataException, NotFoundException {

		var category = new Category(0, "Category Random");
		var addCatID = srv.add(category).getCategoryId();
		var fullSize = srv.getAll().size();
		srv.deleteById(addCatID);
		assertEquals(fullSize - 1, srv.getAll().size());
	}

	@Test
	@DisplayName("Delete Null")
	void testNullDelete() {
		assertThrows(InvalidDataException.class, () -> srv.delete(null));
	}

	@Test
	@DisplayName("Delete by non existent ID")
	void testNonExistDelete() throws InvalidDataException, NotFoundException {

		var category = new Category(0, "Something");
		var addCatID = srv.add(category).getCategoryId();
		var fullSize = srv.getAll().size();
		srv.deleteById(addCatID + 1);
		assertEquals(fullSize, srv.getAll().size());
		srv.deleteById(addCatID);
	}

}