package com.example.demo.domains.core.services.contracts;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DuplicateKeyException;

import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.exceptions.NotFoundException;

public interface DomainService<E, K> {
	List<E> getAll();

	Optional<E> getOne(K id);

	E add(E item) throws DuplicateKeyException, InvalidDataException;

	E modify(E item) throws NotFoundException, InvalidDataException;

	void delete(E item) throws InvalidDataException;

	void deleteById(K id);

}
