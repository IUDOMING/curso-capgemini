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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.domains.contracts.services.ActorService;
import com.example.domains.entities.Actor;
import com.example.domains.entities.Film;
import com.example.domains.entities.Film.Rating;
import com.example.domains.entities.FilmActor;
import com.example.domains.entities.Language;
import com.example.domains.entities.dtos.ActorDTO;
import com.example.domains.entities.dtos.ActorShort;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Value;

@WebMvcTest(ActorResources.class)
class ActorResourcesTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ActorService srv;

	@Autowired
	ObjectMapper objectMapper;

	@Value
	static class ActorShortMock implements ActorShort {
		int actorId;
		String nombre;
	}

	@Test
	@DisplayName("Get all actors")
	void testGetAllString() throws Exception {
		List<ActorShort> lista = new ArrayList<>(Arrays.asList(
				new ActorShortMock(1, "Samuel JACSKON"),
				new ActorShortMock(2, "Mejor ACTOR"), 
				new ActorShortMock(3, "Peor ACTOR")));
		when(srv.getByProjection(ActorShort.class)).thenReturn(lista);
		mockMvc.perform(get("/api/actores/v1").accept(MediaType.APPLICATION_JSON))
		.andExpectAll(
				status().isOk(),
				content().contentType("application/json"),
				jsonPath("$.size()").value(3));
	}

	@Test
	@DisplayName("Get all actors with pages")
	void testGetAllPageable() throws Exception {
		List<ActorShort> lista = new ArrayList<>(Arrays.asList(
				new ActorShortMock(1, "Samuel JACSKON"),
				new ActorShortMock(2, "Mejor ACTOR"), 
				new ActorShortMock(3, "Peor ACTOR")));

		when(srv.getByProjection(PageRequest.of(0, 20), ActorShort.class))
		.thenReturn(new PageImpl<>(lista));
		mockMvc.perform(get("/api/actores/v1").queryParam("page", "0"))
		.andExpectAll(
				status().isOk(),
				content().contentType("application/json"),
				jsonPath("$.content.size()").value(3),
				jsonPath("$.size").value(3));
	}

	
	@Test
	@DisplayName("Get one actor")
	void testGetOne() throws Exception {
		int id = 1;
		var ele = new Actor(id, "Samuel", "JACKSON");
		when(srv.getOne(id)).thenReturn(Optional.of(ele));
		mockMvc.perform(get("/api/actores/v1/{id}", id))
			.andExpect(status().isOk())
	        .andExpect(jsonPath("$.id").value(id))
	        .andExpect(jsonPath("$.name").value(ele.getFirstName()))
	        .andExpect(jsonPath("$.surname").value(ele.getLastName()))
	        .andDo(print());
	}
	
	@Test
	@DisplayName("Get all actor's films")
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
		        		
		var actor = new Actor(id, "Samuel","JACKSON");
		
		List<FilmActor> filmActors = new ArrayList<>(
				Arrays.asList(
						new FilmActor(filmList.get(0), actor),
						new FilmActor(filmList.get(1), actor)
						));
				
		actor.setFilmActors(filmActors);
		when(srv.getOne(id)).thenReturn(Optional.of(actor));
		mockMvc.perform(get("/api/actores/v1/{id}/pelis", id))
			.andExpectAll(
					status().isOk(),
					jsonPath("$[0].id").value(1),
					jsonPath("$[0].title").value("Titulo"),
					jsonPath("$[1].id").value(2),
					jsonPath("$[1].title").value("Random Title")
			).andDo(print());
			
	}

	@Test
	@DisplayName("Create Actor")
	void testCreate() throws Exception {
		int id = 1;
		var ele = new Actor(id, "Samuel", "JACSKON");
		when(srv.add(ele)).thenReturn(ele);
		mockMvc.perform(post("/api/actores/v1").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(ActorDTO.from(ele))))
				.andExpect(status().isCreated())
				.andExpect(header().string("Location", "http://localhost/api/actores/v1/1"))
				.andDo(print());
	}

	@Test
	@DisplayName("Update Actor")
	void testUpdate() throws Exception {
		int id = 1;
		var ele = new Actor(id,"Samuel", "JACKSON");
		when(srv.modify(ele)).thenReturn(ele);
		mockMvc.perform(put("/api/actores/v1/{id}", id)
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(ActorDTO.from(ele))))
			.andExpect(status().isNoContent())
	        .andDo(print());
	}

	@Test
	@DisplayName("Delete actor")
	void testDelete() throws Exception {
		var id = 1;
		
		doNothing().when(srv).deleteById(id);
		
		mockMvc.perform(delete("/api/actores/v1/{id}", id))
			.andExpect(status().isNoContent())
	        .andDo(print());
		
		verify(srv,times(1)).deleteById(id);
	}
}