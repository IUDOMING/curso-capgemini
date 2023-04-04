package com.example.domains.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;

import com.example.domains.contracts.repository.CategoryRepository;
import com.example.domains.contracts.services.CategoryService;
import com.example.domains.entities.Category;
import com.example.domains.entities.Language;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

@DataJpaTest
@ComponentScan(basePackages = "com.example")
class CategoryServiceImplMockTest {
	
	@MockBean
	CategoryRepository dao;

	@Autowired
	CategoryService srv;

	@Test
	void testGetAll_isNotEmpty() {
		List<Category> list = new ArrayList<>(
				Arrays.asList(new Category(1, "Acción"),
				new Category(2, "Terror"),
				new Category(3, "Otros")));
		
		when(dao.findAll()).thenReturn(list);
		var rslt = srv.getAll();
		assertThat(rslt.size()).isEqualTo(3);
	}

	@Test
	void testGetOne_valid() {
		List<Category> list = new ArrayList<>(
				Arrays.asList(new Category(1, "Acción"),
				new Category(2, "Terror"),
				new Category(3, "Otros")));
		
		when(dao.findById(3)).thenReturn(Optional.of(new Category(3, "Otros")));
		var rslt = srv.getOne(3);
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
		when(dao.save(any(Category.class))).thenReturn(null, null);
		assertThrows(InvalidDataException.class, () -> srv.add(null));
		verify(dao, times(0)).save(null);
	}

	@Test
	void testModify() throws NotFoundException, InvalidDataException {
		when(dao.save(any(Category.class))).thenReturn(null, null);
		assertThrows(InvalidDataException.class, () -> srv.modify(null));
		verify(dao, times(0)).save(null);
	}

	@Test
	void testDelete() throws InvalidDataException {
		List<Category> list = new ArrayList<>(
				Arrays.asList(new Category(1, "Acción"),
				new Category(2, "Terror"),
				new Category(3, "Otros")));

		when(dao.findAll()).thenReturn(list);
		dao.delete(new Category(2, "Terror"));
		var rslt = srv.getOne(2);

		assertThat(rslt.isPresent()).isFalse();

	}

	@Test
	void testDeleteById() {
		List<Category> list = new ArrayList<>(
				Arrays.asList(new Category(1, "Acción"),
				new Category(2, "Terror"),
				new Category(3, "Otros")));
		
		when(dao.findAll()).thenReturn(list);
		dao.deleteById(3);
		var rslt = srv.getOne(3);
		assertThat(rslt.isPresent()).isFalse();
		
		
	}

}