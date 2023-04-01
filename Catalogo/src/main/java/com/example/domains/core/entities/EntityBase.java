package com.example.domains.core.entities;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Transient;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

public abstract class EntityBase<E> {

	//Identifies it as non persistent
	@Transient
	////Will ignore it as JSON
	@JsonIgnore
	@SuppressWarnings("unchecked")
	//Checks for any possible error and retruns it as a Collection
	public Set<ConstraintViolation<E>> getErrors() {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		return validator.validate((E) this);
	}

	
	@JsonIgnore
	@Transient
	//Using get Errors, gets the field that suufered and error and it's message.
	public String getErrorsMessage() {
		Set<ConstraintViolation<E>> lst = getErrors();
		if (lst.isEmpty())
			return "";
		StringBuilder sb = new StringBuilder("ERRORES:");
		getErrorsFields().forEach((field, error) -> sb.append(" " + field + ": " + error + "."));
		return sb.toString();
	}

	@JsonIgnore
	@Transient
	public Map<String, String> getErrorsFields() {
		Set<ConstraintViolation<E>> lst = getErrors();
		if (lst.isEmpty())
			return null;
		Map<String, String> errors = new HashMap<>();
		lst.stream().sorted((a, b) -> a.getPropertyPath().toString().compareTo(b.getPropertyPath().toString()))
				.forEach(item -> errors.put(item.getPropertyPath().toString(),
				(errors.containsKey(item.getPropertyPath().toString())
				? errors.get(item.getPropertyPath().toString()) + ", ": "")
				+ item.getMessage()));
		return errors;
	}

	
	//Checks if something is valid.
	//When getErrors doesn't Set is 0, it means is valid
	@Transient
	@JsonIgnore
	public boolean isValid() {
		return getErrors().size() == 0;
	}

	//Checks if isValid is false. In that case, item is invalid.
	@Transient
	@JsonIgnore
	public boolean isInvalid() {
		return !isValid();
	}

}
