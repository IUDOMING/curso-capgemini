package com.example.application.resources;

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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.domains.contracts.services.CategoryService;
import com.example.domains.entities.Category;
import com.example.domains.entities.Film;
import com.example.domains.entities.Film.Rating;
import com.example.domains.entities.FilmCategory;
import com.example.domains.entities.Language;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CategoryResources.class)
class CategoryResourcesTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CategoryService srv;

	@Autowired
	ObjectMapper objectMapper;

	@Test
	@DisplayName("Get all categories")
	void testGetAllString() throws Exception {
		List<Category> lista = new ArrayList<>(
				Arrays.asList(new Category(1, "Terror"), new Category(2, "Acción"), new Category(3, "Infantil")));

		when(srv.getAll()).thenReturn(lista);
		mockMvc.perform(get("/api/categorias/v1").accept(MediaType.APPLICATION_JSON)).andExpectAll(status().isOk(),
				content().contentType("application/json"), jsonPath("$.size()").value(3));
	}

	@Test
	@DisplayName("Get one category")
	void testGetOne() throws Exception {
		int id = 1;
		var ele = new Category(1, "Terror");
		when(srv.getOne(id)).thenReturn(Optional.of(ele));
		mockMvc.perform(get("/api/categorias/v1/{id}", id)).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(id)).andExpect(jsonPath("$.categoria").value(ele.getName()))
				.andDo(print());
	}

	@Test
	@DisplayName("Get all category films")
	void testGetActorFilms() throws Exception{
		var id = 1; 
		List<Film> filmList = new ArrayList<>(
		        Arrays.asList(new Film(1, "Titulo", "Description of the film we don't know about",
		        		new Short("1990"), new Language(3),
						new Language(5), (byte) 10, new BigDecimal(20),
						230, new BigDecimal(20), Rating.GENERAL_AUDIENCES),
		        		new Film(2, "Random Title", "Description of the random film",
		        				new Short("1993"), new Language(3),
						new Language(5), (byte) 10, new BigDecimal(20),
						230, new BigDecimal(20), Rating.GENERAL_AUDIENCES)));
		        		
		var category = new Category(id, "Categoria");
		
		List<FilmCategory> filmCategory = new ArrayList<>(
				Arrays.asList(
						new FilmCategory(filmList.get(0), category),
						new FilmCategory(filmList.get(1), category)
						));
				
		
		category.setFilmCategories(filmCategory);
		
		when(srv.getOne(id)).thenReturn(Optional.of(category));
		
		mockMvc.perform(get("/api/categorias/v1/{id}/pelis", id))
			.andExpectAll(
					status().isOk(),
					jsonPath("$.length()").value(2),
					jsonPath("$[0].id").value(1),
					jsonPath("$[0].categoria").value("Titulo"),
					jsonPath("$[1].id").value(2),
					jsonPath("$[1].categoria").value("Random Title")
			).andDo(print());
	}

	@Test
	@DisplayName("Create category")
	void testCreate() throws Exception {
		int id = 1;
		var ele = new Category(id, "Terror");
		when(srv.add(ele)).thenReturn(ele);
		mockMvc.perform(post("/api/categorias/v1").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(ele))).andExpect(status().isCreated())
				.andExpect(header().string("Location", "http://localhost/api/categorias/v1/1")).andDo(print());
	}

	@Test
	@DisplayName("Update category")
	void testUpdate() throws Exception {
		int id = 1;
		var ele = new Category(1,"Terror");
		
		when(srv.modify(ele)).thenReturn(ele);
		
		mockMvc.perform(put("/api/categorias/v1/{id}", id)
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(ele))
			)
			.andExpect(status().isNoContent())
	        .andDo(print())
	        ;
	}
	@Test
	@DisplayName("Delete category")
	void testDelete() throws Exception {
		var id = 1;
		
		doNothing().when(srv).deleteById(id);
		
		mockMvc.perform(delete("/api/categorias/v1/{id}", id))
			.andExpect(status().isNoContent())
	        .andDo(print());
		
		verify(srv,times(1)).deleteById(id);
	}

}