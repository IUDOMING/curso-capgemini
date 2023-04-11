package com.example.application.resources;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.domains.contracts.services.CategoryService;
import com.example.domains.entities.Actor;
import com.example.domains.entities.Category;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CategoryResources.class)
class CategoryResourcesTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CategoryService srv;

	@Autowired
	ObjectMapper objectMapper;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	@Disabled
	void test() {
		fail("Not yet implemented");
	}


	@Test
	void testGetAllString() throws Exception {
		List<Category> lista = new ArrayList<>(Arrays.asList(
				new Category(1, "Terror"),
				new Category(2, "Acción"), 
				new Category(3, "Infantil")));
		
		when(srv.getAll()).thenReturn(lista);
		mockMvc.perform(get("/api/categorias/v1").accept(MediaType.APPLICATION_JSON))
		.andExpectAll(
				status().isOk(),
				content().contentType("application/json"),
				jsonPath("$.size()").value(3));
	}
	
	@Test
	void testGetOne() throws Exception {
		int id = 1;
		var ele = new Category(1, "Terror");
		when(srv.getOne(id)).thenReturn(Optional.of(ele));
		mockMvc.perform(get("/api/categorias/v1/{id}", id))
			.andExpect(status().isOk())
	        .andExpect(jsonPath("$.id").value(id))
	        .andExpect(jsonPath("$.categoria").value(ele.getName()))
	        .andDo(print());
	}


	@Test
	void testGetOne404() throws Exception {
		int id = 1;
		var ele = new Category(id, "Terror");
		when(srv.getOne(id)).thenReturn(Optional.empty());
		mockMvc.perform(get("/api/categorias/v1/{id}", id)).andExpect(status().isNotFound())
				.andExpect(jsonPath("$.title").value("Not Found")).andDo(print());
	}

	@Test
	void testCreate() throws Exception {
		int id = 1;
		var ele = new Category(id, "Terror");
		when(srv.add(ele)).thenReturn(ele);
		mockMvc.perform(post("/api/categorias/v1").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(ele))).andExpect(status().isCreated())
				.andExpect(header().string("Location", "http://localhost/api/categorias/v1/1")).andDo(print());
	}

	@Test
	void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	void testDelete() {
		fail("Not yet implemented");
	}

}