package com.example.domains.services;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.domains.contracts.repository.LanguageRepository;
import com.example.domains.contracts.services.LanguageService;
import com.example.domains.entities.Language;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

@Service
public class LanguageServiceImpl implements LanguageService {

	@Autowired
	private LanguageRepository dao;

	@Override
	public List<Language> getAll() {
		return dao.findAll(Sort.by("name"));
	}

	@Override
	public Optional<Language> getOne(Integer id) {
		return dao.findById(id);
	}

	@Override
	public Language add(Language item) throws DuplicateKeyException, InvalidDataException {
		if (item == null)
			throw new InvalidDataException("Can't be null");
		if (item.isInvalid())
			throw new InvalidDataException(item.getErrorsMessage());
		if (dao.existsById(item.getLanguageId()))
			throw new DuplicateKeyException(item.getErrorsMessage());
		return dao.save(item);
	}

	@Override
	public Language modify(Language item) throws NotFoundException, InvalidDataException {
		if (item == null)
			throw new InvalidDataException("Can't be null");
		if (item.isInvalid())
			throw new InvalidDataException(item.getErrorsMessage());
		if (getOne(item.getLanguageId()).isEmpty())
			throw new NotFoundException();
		return dao.save(item);

	}

	@Override
	public void delete(Language item) throws InvalidDataException {
		if (item == null)
			throw new InvalidDataException("Can't be null");
		deleteById(item.getLanguageId());

	}

	@Override
	public void deleteById(Integer id) {
		dao.deleteById(id);

	}

	@Override
	public List<Language> news(Timestamp date) {
		return dao.findByLastUpdateGreaterThanEqualOrderByLastUpdate(date);
	}

}
