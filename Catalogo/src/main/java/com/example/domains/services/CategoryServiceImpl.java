	package com.example.domains.services;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.domains.contracts.repository.CategoryRepository;
import com.example.domains.contracts.services.CategoryService;
import com.example.domains.entities.Category;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository dao;

	@Override
	public List<Category> getAll() {
		return dao.findAll();
	}

	@Override
	public Optional<Category> getOne(Integer id) {
		return dao.findById(id);
	}

	@Override
	public Category add(Category item) throws DuplicateKeyException, InvalidDataException {
		if (item == null)
			throw new InvalidDataException("Can't be null");
		if (item.isInvalid())
			throw new InvalidDataException(item.getErrorsMessage());
		if (dao.existsById(item.getCategoryId()))
			throw new DuplicateKeyException(item.getErrorsMessage());
		return dao.save(item);
	}

	@Override
	public Category modify(Category item) throws NotFoundException, InvalidDataException {
		if (item == null)
			throw new InvalidDataException("Can't be null");
		if (item.isInvalid())
			throw new InvalidDataException(item.getErrorsMessage());
		if (getOne(item.getCategoryId()).isEmpty())
			throw new NotFoundException();
		return dao.save(item);

	}

	@Override
	public void delete(Category item) throws InvalidDataException {
		if (item == null)
			throw new InvalidDataException("Can't be null");
		deleteById(item.getCategoryId());

	}

	@Override
	public void deleteById(Integer id) {
		dao.deleteById(id);

	}

	@Override
	public List<Category> news(Timestamp date) {
		return dao.findByLastUpdateGreaterThanEqualOrderByLastUpdate(date);
	}

}
