package com.example.domains.services;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.domains.contracts.repository.FilmRepository;
import com.example.domains.contracts.services.FilmServices;
import com.example.domains.entities.Film;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

import jakarta.transaction.Transactional;
import lombok.NonNull;

@Service
public class FilmServiceImpl implements FilmServices {

	@Autowired
	private FilmRepository dao;

	@Override
	public <T> List<T> getByProjection(Class<T> type) {
		return dao.findAllBy(type);
	}

	@Override
	public <T> Iterable<T> getByProjection(Sort sort, Class<T> type) {
		return dao.findAllBy(sort, type);
	}

	@Override
	public <T> Page<T> getByProjection(Pageable pageable, Class<T> type) {
		return dao.findAllBy(pageable, type);
	}

	@Override
	public Iterable<Film> getAll(Sort sort) {
		return dao.findAll(sort);
	}

	@Override
	public Page<Film> getAll(Pageable pageable) {
		return dao.findAll(pageable);
	}

	@Override
	public List<Film> getAll() {
		return dao.findAll();
	}

	@Override
	public Optional<Film> getOne(Integer id) {
		return dao.findById(id);
	}

	@Override
	public Film add(Film item) throws DuplicateKeyException, InvalidDataException {
		if (item == null)
			throw new InvalidDataException("Can't be null");
		if (item.isInvalid())
			throw new InvalidDataException(item.getErrorsMessage());
		if (dao.existsById(item.getFilmId()))
			throw new DuplicateKeyException(item.getErrorsMessage());
		var actores = item.getActors();
		var categorias = item.getCategories();
		item.clearActors();
		item.clearCategories();
		var newItem = dao.save(item);
		newItem.setActors(actores);
		newItem.setCategories(categorias);
		return dao.save(newItem);
	}

	@Override
	@Transactional
	public Film modify(Film item) throws NotFoundException, InvalidDataException {
		if(item == null)
			throw new InvalidDataException("Can't be null");
		if(item.isInvalid())
			throw new InvalidDataException(item.getErrorsMessage());
		var read = dao.findById(item.getFilmId());
		if(read.isEmpty())
			throw new NotFoundException();
		return dao.save(item.merge(read.get()));
	}

	@Override
	public void delete(Film item) throws InvalidDataException {
		if (item == null)
			throw new InvalidDataException("Can't be null");
		deleteById(item.getFilmId());

	}

	@Override
	public void deleteById(Integer id) {
		dao.deleteById(id);

	}

	@Override
	public List<Film> news(@NonNull Timestamp date) {
		return dao.findByLastUpdateGreaterThanEqualOrderByLastUpdate(date);
	}

}
