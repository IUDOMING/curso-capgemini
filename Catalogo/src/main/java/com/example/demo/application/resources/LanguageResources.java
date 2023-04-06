package com.example.demo.application.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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

import com.example.domains.contracts.services.LanguageService;
import com.example.domains.entities.Language;
import com.example.exceptions.BadRequestException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = { "/api/lenguages/v1", "/api/lenguages" })
public class LanguageResources {
	@Autowired
	private LanguageService srv;

	@GetMapping
	public List<Language> getAll(@RequestParam(required = false) String sort) {
		if (sort != null)
			return (List<Language>) srv.getAll();
		return srv.getAll();
	}

	@GetMapping(path = "/{id}")
	public Language getOne(@PathVariable int id) throws NotFoundException {
		var item = srv.getOne(id);
		if (item.isEmpty())
			throw new NotFoundException();
		return item.get();
	}

	@PostMapping
	public ResponseEntity<Object> create(@Valid @RequestBody Language item)
			throws BadRequestException, DuplicateKeyException, InvalidDataException {
		var newItem = srv.add(new Language(item.getLanguageId(), item.getName()));
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newItem.getLanguageId()).toUri();
		return ResponseEntity.created(location).build();

	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable int id, @Valid @RequestBody Language item)
			throws BadRequestException, NotFoundException, InvalidDataException {
		if (id != item.getLanguageId())
			throw new BadRequestException("No coinciden los identificadores");
		srv.modify(new Language(item.getLanguageId(),item.getName()));
	}	

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) {
		srv.deleteById(id);
	}

}
