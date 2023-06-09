package com.example.application.services;

import java.sql.Timestamp;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domains.contracts.services.ActorService;
import com.example.domains.contracts.services.CategoryService;
import com.example.domains.contracts.services.FilmServices;
import com.example.domains.contracts.services.LanguageService;
import com.example.domains.entities.dtos.ActorDTO;
import com.example.domains.entities.dtos.FilmEditDTO;
import com.example.domains.entities.dtos.NovedadesDTO;

@Service
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
	public NovedadesDTO news(Timestamp date) {
		if (date == null)
			date = Timestamp.from(Instant.now().minusSeconds(36000));
		return new NovedadesDTO(
				filmSrv.news(date).stream().map(item -> FilmEditDTO.from(item)).toList(),
				artorSrv.news(date).stream().map(item -> ActorDTO.from(item)).toList(),
				categorySrv.news(date),
				languageSrv.news(date));
	}

}
