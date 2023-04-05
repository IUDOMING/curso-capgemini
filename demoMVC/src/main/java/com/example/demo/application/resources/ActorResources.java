package com.example.demo.application.resources;

import java.net.URI;
import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.domains.contracts.services.ActorService;
import com.example.demo.domains.entities.dtos.ActorDTO;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.exceptions.NotFoundException;

import jakarta.validation.Valid;
@RestController
@RequestMapping(path = { "/api/actores/v1", "/api/actors" })
public class ActorResources {
	private ActorService srv;

	@GetMapping
	public List<ActorDTO> getAll() {
		return srv.getByProjection(ActorDTO.class);
	}

	@GetMapping(path = "/{id}")
	//No existe la manera de conseguir un ActorDTO direcamtente de un id
	public ActorDTO getOne(@PathVariable int id) throws NotFoundException {
		// Conseguimos el Actor mediante la id
		var item = srv.getOne(id);
		//Chequeo que exista
		if(item.isEmpty())
			throw new NotFoundException();
		//Si existe, lo transformamos a ActorDTO mediante la clase ActorDTO
		return ActorDTO.from(item.get());
	}
	@PostMapping
	public ResponseEntity<Object> create(@Valid @RequestBody ActorDTO item) throws BadRequestException,
											DuplicateKeyException, InvalidDataException {
		var newItem = srv.add(ActorDTO.from(item));
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
			.buildAndExpand(newItem.getActorId()).toUri();
		return ResponseEntity.created(location).build();

	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable int id, @Valid @RequestBody ActorDTO item) throws BadRequestException, NotFoundException, InvalidDataException {
		if(id != item.getActorId())
			throw new BadRequestException("No coinciden los identificadores");
		srv.modify(ActorDTO.from(item));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) throws InvalidDataException {
		srv.deleteById(id);
	}

}
