package com.example.domains.core.contracts.services;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DuplicateKeyException;

import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

<<<<<<< HEAD
=======

>>>>>>> 862e2d75bdfc08585e5d4099527348875a982978
public interface DomainService<E, K> {
	List<E> getAll();

	Optional<E> getOne(K id);

	E add(E item) throws DuplicateKeyException, InvalidDataException;
<<<<<<< HEAD

=======
 
>>>>>>> 862e2d75bdfc08585e5d4099527348875a982978
	E modify(E item) throws NotFoundException, InvalidDataException;

	void delete(E item) throws InvalidDataException;

	void deleteById(K id);

}
