package com.example.application.resources;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.domains.contracts.services.LanguageService;
import com.example.domains.entities.Category;
import com.example.domains.entities.Film;
import com.example.domains.entities.FilmCategory;
import com.example.domains.entities.Language;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;
import com.example.domains.entities.Film.Rating;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(LanguageResources.class)
class LanguageResourcesTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private LanguageService srv;

	@Autowired
	ObjectMapper objectMapper;

	@Test
	@DisplayName("Get all language")
	void testGetAllString() throws Exception {
		List<Language> lista = new ArrayList<>(
				Arrays.asList(new Language(1, "Alemán"), new Language(2, "Chino"), new Language(3, "Castellano")));

		when(srv.getAll()).thenReturn(lista);
		mockMvc.perform(get("/api/lenguages/v1").accept(MediaType.APPLICATION_JSON)).andExpectAll(status().isOk(),
				content().contentType("application/json"), jsonPath("$.size()").value(3));
	}

	@Test
	@DisplayName("Get one language")
	void testGetOne() throws Exception {
		int id = 1;
		var ele = new Language(1, "Chino");
		when(srv.getOne(id)).thenReturn(Optional.of(ele));
		mockMvc.perform(get("/api/lenguages/v1/{id}", id)).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(id)).andExpect(jsonPath("$.idioma").value(ele.getName()))
				.andDo(print());
	}

	@Test
	@DisplayName("Create language")
	void testCreate() throws Exception {
		int id = 1;
		var ele = new Language(id, "Alemán");
		when(srv.add(ele)).thenReturn(ele);
		mockMvc.perform(post("/api/lenguages/v1").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(ele))).andExpect(status().isCreated())
				.andExpect(header().string("Location", "http://localhost/api/lenguages/v1/1")).andDo(print());
	}

	@Test
	@DisplayName("Update language")
	void testUpdate() throws Exception {
		int id = 1;
		var ele = new Language(1, "Alemán");

		when(srv.modify(ele)).thenReturn(ele);

		mockMvc.perform(put("/api/lenguages/v1/{id}", id).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(ele))).andExpect(status().isNoContent()).andDo(print());
	}

	@Test
	@DisplayName("Delete language")
	void testDelete() throws Exception {
		var id = 1;

		doNothing().when(srv).deleteById(id);

		mockMvc.perform(delete("/api/lenguages/v1/{id}", id)).andExpect(status().isNoContent()).andDo(print());

		verify(srv, times(1)).deleteById(id);
	}

}