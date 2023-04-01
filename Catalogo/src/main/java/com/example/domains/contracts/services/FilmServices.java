package com.example.domains.contracts.services;

import java.sql.Timestamp;
import java.util.List;

import com.example.domains.core.contracts.services.ProjectionDomainService;
import com.example.domains.entities.Film;

public interface FilmServices extends ProjectionDomainService<Film, Integer> {

	List<Film> novedades(Timestamp fecha);

}
