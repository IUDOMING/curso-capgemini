package com.example.demo.domains.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.domains.contracts.repositories.ActorRepository;
import com.example.demo.domains.contracts.services.ActorService;
<<<<<<< HEAD
import com.example.demo.domains.entities.Actor;
=======
import com.example.demo.domains.core.repositories.contracts.RepositoryWithProjections;
>>>>>>> 862e2d75bdfc08585e5d4099527348875a982978
import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.exceptions.NotFoundException;
import com.example.domains.entities.Actor;

@Service
public class ActorServiceImpl implements ActorService {

	@Autowired
	ActorRepository dao;

	@Override
	public <T> List<T> getByProjection(Class<T> type) {
		// TODO Auto-generated method stub
		return dao.findAllBy(type);
	}

	@Override
	public <T> Iterable<T> getByProjection(Sort sort, Class<T> type) {
		// TODO Auto-generated method stub
		return dao.findAllBy(sort, type);
	}

	@Override
	public <T> Page<T> getByProjection(Pageable pageable, Class<T> type) {
		// TODO Auto-generated method stub
		return dao.findAllBy(pageable, type);
	}

	@Override
	public Iterable<Actor> getAll(Sort sort) {
		// TODO Auto-generated method stub
		return dao.findAll(sort);
	}

	@Override
	public Page<Actor> getAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return dao.findAll(pageable);
	}

	@Override
	public List<Actor> getAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Optional<Actor> getOne(Integer id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public Actor add(Actor item) throws DuplicateKeyException, InvalidDataException {
		// TODO Auto-generated method stub
		if (item == null)
			throw new InvalidDataException("No puede ser nuelo");

		if (item.isInvalid())
			throw new InvalidDataException(item.getErrorsMessage());
		if (dao.existsById(item.getActorId()))
			throw new DuplicateKeyException(item.getErrorsMessage());
		return dao.save(item);
	}

	@Override
	public Actor modify(Actor item) throws NotFoundException, InvalidDataException {
		if (item == null)
			throw new InvalidDataException("No puede ser nuelo");

		if (item.isInvalid())
			throw new InvalidDataException(item.getErrorsMessage());
		if (dao.existsById(item.getActorId()))
			throw new NotFoundException();
		return dao.save(item);

	}

	@Override
	public void delete(Actor item) throws InvalidDataException {
		// TODO Auto-generated method stub
		if (item == null)
			throw new InvalidDataException("No puede ser nuelo");
		deleteById(item.getActorId());

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		dao.deleteById(id);

	}

}
