package com.example.application.services;

import java.sql.Timestamp;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.domains.contracts.services.ActorService;
import com.example.domains.contracts.services.CategoryService;
import com.example.domains.contracts.services.FilmServices;
import com.example.domains.contracts.services.LanguageService;
import com.example.domains.entities.dtos.ActorDTO;
import com.example.domains.entities.dtos.FilmEditDTO;
import com.example.domains.entities.dtos.NovedadesDTO;

public class CatalogoServiceImpl implements CatalogoService {
	@Autowired
	private FilmServices filmSrv;
	@Autowired
	private ActorService artorSrv;
	@Autowired
	private CategoryService categorySrv;
	@Autowired
	private LanguageService languageSrv;

	@Override
	public NovedadesDTO news(Timestamp fecha) {
		if (fecha == null)
			fecha = Timestamp.from(Instant.now().minusSeconds(36000));
		return new NovedadesDTO(filmSrv.news(fecha).stream().map(item -> FilmEditDTO.from(item)).toList(),
				artorSrv.news(fecha).stream().map(item -> ActorDTO.from(item)).toList(), categorySrv.news(fecha),
				languageSrv.news(fecha));
	}

}
