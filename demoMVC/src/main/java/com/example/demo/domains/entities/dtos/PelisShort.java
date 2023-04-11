package com.example.demo.domains.entities.dtos;

import org.springframework.data.rest.core.config.Projection;

import com.example.demo.domains.entities.Film;

@Projection(types = Film.class, name = "titulo")
public interface PelisShort {
	int getFilmId();
	String getTitle();
}