package com.example.domains.core.entities;

<<<<<<< HEAD
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

=======
import java.util.Set;

>>>>>>> 862e2d75bdfc08585e5d4099527348875a982978
import jakarta.persistence.Transient;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

<<<<<<< HEAD
public abstract class EntityBase<E> {

=======
import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class EntityBase<E> {
	
	
>>>>>>> 862e2d75bdfc08585e5d4099527348875a982978
	@Transient
	@JsonIgnore
	@SuppressWarnings("unchecked")
	public Set<ConstraintViolation<E>> getErrors() {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
<<<<<<< HEAD
		return validator.validate((E) this);
	}

	@JsonIgnore
	@Transient
	public String getErrorsMessage() {
		Set<ConstraintViolation<E>> lst = getErrors();
		if (lst.isEmpty())
			return "";
		StringBuilder sb = new StringBuilder("ERRORES:");
		getErrorsFields().forEach((campo, error) -> sb.append(" " + campo + ": " + error + "."));
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
								? errors.get(item.getPropertyPath().toString()) + ", "
								: "") + item.getMessage()));
		return errors;
	}

=======
		return validator.validate((E)this);
	}
	
	@Transient
	@JsonIgnore
	public String getErrorsMessage() {
		if(isValid()) return "";
		StringBuilder sb = new StringBuilder("ERRORES: ");
		//getErrors().forEach(item -> sb.append(item.getPropertyPath() + ": " + item.getMessage() + ". "));
		getErrors().stream().map(item -> item.getPropertyPath() + ": " + item.getMessage() + ". ")
		.sorted().forEach(sb::append);
		return sb.toString().trim();
	}
	
>>>>>>> 862e2d75bdfc08585e5d4099527348875a982978
	@Transient
	@JsonIgnore
	public boolean isValid() {
		return getErrors().size() == 0;
	}

	@Transient
	@JsonIgnore
	public boolean isInvalid() {
		return !isValid();
	}

}
