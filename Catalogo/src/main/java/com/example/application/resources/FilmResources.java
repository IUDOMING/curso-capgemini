package com.example.application.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.domains.contracts.services.FilmServices;
import com.example.domains.entities.Film;
import com.example.domains.entities.Language;
import com.example.domains.entities.dtos.ActorFilmsDTO;
import com.example.domains.entities.dtos.CategoriesFilmDTO;
import com.example.domains.entities.dtos.FilmDetailsDTO;
import com.example.domains.entities.dtos.FilmShortDTO;
import com.example.exceptions.BadRequestException;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = { "/api/peliculas/v1", "/api/peliculas" })
public class FilmResources {
	@Autowired
	private FilmServices srv;

	@GetMapping
	public List<FilmShortDTO> getAll(@RequestParam(required = false) String sort) {
		if (sort != null)
			return (List<FilmShortDTO>) srv.getByProjection(Sort.by(sort), FilmShortDTO.class);
		return srv.getByProjection(FilmShortDTO.class);
	}
	
	@GetMapping(params="page")
	public Page<FilmShortDTO> getAll(Pageable pageable) {
		return srv.getByProjection(pageable, FilmShortDTO.class);
	}

	@GetMapping(path = "/{id}")
	public FilmDetailsDTO getOne(@PathVariable int id) throws NotFoundException {
		var item = srv.getOne(id);
		if (item.isEmpty())
			throw new NotFoundException();
		return FilmDetailsDTO.from(item.get());
	}
	
	@GetMapping(path = "/{id}/actores")
	@Transactional
	public List<ActorFilmsDTO<Integer,String>> getActores(@PathVariable int id) throws NotFoundException {
		var item = srv.getOne(id);
		if (item.isEmpty())
			throw new NotFoundException();
		return item.get().getActors().stream()
				.map(o -> new ActorFilmsDTO<>(o.getActorId(),o.getFirstName()+" " + o.getLastName())).toList();
	}
	@GetMapping(path = "/{id}/lenguaje")
	@Transactional
	public Language getLanguage(@PathVariable int id) throws NotFoundException {
		var item = srv.getOne(id);
		if (item.isEmpty())
			throw new NotFoundException();
		return item.get().getLanguage();
	}
	
	@GetMapping(path = "/{id}/categorias")
	@Transactional
	public List<CategoriesFilmDTO<Integer,String>> getCategories(@PathVariable int id) throws NotFoundException {
		var item = srv.getOne(id);
		if (item.isEmpty())
			throw new NotFoundException();
		return item.get().getCategories().stream()
				.map(o -> new CategoriesFilmDTO<>(o.getCategoryId(),o.getName())).toList();
	}

	@PostMapping
	public ResponseEntity<Object> create(@Valid @RequestBody Film item)
			throws BadRequestException, DuplicateKeyException, InvalidDataException {
		var newItem = srv.add(item);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newItem.getFilmId()).toUri();
		return ResponseEntity.created(location).build();

	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable int id, @Valid @RequestBody Film item)
			throws BadRequestException, NotFoundException, InvalidDataException {
		if (id != item.getFilmId())
			throw new BadRequestException("No coinciden los identificadores");
		srv.modify(item);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) {
		srv.deleteById(id);
	}

}
