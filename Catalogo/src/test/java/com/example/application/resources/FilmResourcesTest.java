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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.application.resources.ActorResourcesTest.ActorShortMock;
import com.example.domains.contracts.services.FilmServices;
import com.example.domains.entities.Actor;
import com.example.domains.entities.Category;
import com.example.domains.entities.Film;
import com.example.domains.entities.Film.Rating;
import com.example.domains.entities.Language;
import com.example.domains.entities.dtos.ActorShort;
import com.example.domains.entities.dtos.FilmDetailsDTO;
import com.example.domains.entities.dtos.FilmShortDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(FilmResources.class)
class FilmResourcesTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FilmServices srv;

	@Autowired
	ObjectMapper objectMapper;
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	@DisplayName("Get all FIlms")
	void testGetAllString() throws Exception {
		List<FilmShortDTO> lista = new ArrayList<>(Arrays.asList(
				new FilmShortDTO(1, "Random1"),
				new FilmShortDTO(2, "Random2"), 
				new FilmShortDTO(3, "Random3")));
		when(srv.getByProjection(FilmShortDTO.class)).thenReturn(lista);
		mockMvc.perform(get("/api/peliculas/v1").accept(MediaType.APPLICATION_JSON))
		.andExpectAll(
				status().isOk(),
				content().contentType("application/json"),
				jsonPath("$.size()").value(3));
	}
	
	@Test
	@DisplayName("Get all films in a page")
	void testGetAllPageable() throws Exception {
		List<FilmShortDTO> lista = new ArrayList<>(
		        Arrays.asList(
		        		new FilmShortDTO(1, "Random1"),
						new FilmShortDTO(2, "Random2"), 
						new FilmShortDTO(3, "Random3")));

		when(srv.getByProjection(PageRequest.of(0, 20), FilmShortDTO.class))
		.thenReturn(new PageImpl<>(lista));
		mockMvc.perform(get("/api/peliculas/v1").queryParam("page", "0"))
		.andExpectAll(
				status().isOk(),
				content().contentType("application/json"),
				jsonPath("$.content.size()").value(3),
				jsonPath("$.size").value(3));
		}
	
	@Test
	@DisplayName("Get one film")
	void testGetOne() throws Exception {
		int id = 1;		
		var ele = new Film(id,"Titulo", "Description of the film we don't know about",
        		new Short("1990"), new Language(3),
				new Language(5), (byte) 10, new BigDecimal(20),230,
				new BigDecimal(20), Rating.GENERAL_AUDIENCES);
		when(srv.getOne(id)).thenReturn(Optional.of(ele));
		mockMvc.perform(get("/api/peliculas/v1/{id}", id))
			.andExpect(status().isOk())
	        .andExpect(jsonPath("$.filmId").value(id))
	        .andExpect(jsonPath("$.title").value(ele.getTitle()))
	        .andDo(print());
	}
	
	@Test
	@DisplayName("Create film")
	void testCreate() throws Exception {
		int id = 1;
		var ele = new Film(1, "Titulo", "Description of the film we don't know about",
        		new Short("1990"), new Language(3),
				new Language(5), (byte) 10, new BigDecimal(20),
				230, new BigDecimal(20), Rating.GENERAL_AUDIENCES);
		when(srv.add(ele)).thenReturn(ele);
		mockMvc.perform(post("/api/peliculas/v1")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(ele))
			)
			.andExpect(status().isCreated())
	        .andExpect(header().string("Location", "http://localhost/api/peliculas/v1/1"))
	        .andDo(print())
	        ;
	}
	@Test
	@DisplayName("Update film")
	void testUpdate() throws Exception {
		int id = 1;
		var ele = new Film(1, "Titulo", "Description of the film we don't know about",
        		new Short("1990"), new Language(3),
				new Language(5), (byte) 10, new BigDecimal(20),
				230, new BigDecimal(20), Rating.GENERAL_AUDIENCES);
		when(srv.modify(ele)).thenReturn(ele);
		mockMvc.perform(put("/api/peliculas/v1/{id}", id)
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(ele)))
			.andExpect(status().isNoContent())
	        .andDo(print())
	        ;
	}
	
	@Test
	@DisplayName("Delete film")
	void testDelete() throws Exception {
		var id = 1;
		
		doNothing().when(srv).deleteById(id);
		
		mockMvc.perform(delete("/api/peliculas/v1/{id}", id))
			.andExpect(status().isNoContent())
	        .andDo(print());
		
		verify(srv,times(1)).deleteById(id);
	}

}
