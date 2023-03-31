package com.example.exceptions;

<<<<<<< HEAD
import java.util.Map;

public class InvalidDataException extends Exception {
	private static final long serialVersionUID = 1L;
	private final static String MESSAGE_STRING = "Invalid data";
	private Map<String, String> errors = null;
	
	public InvalidDataException() {
		this(MESSAGE_STRING);
=======
public class InvalidDataException extends Exception {

	public InvalidDataException() {
>>>>>>> 862e2d75bdfc08585e5d4099527348875a982978
	}

	public InvalidDataException(String message) {
		super(message);
	}

<<<<<<< HEAD
	public InvalidDataException(Map<String, String> errors) {
		this(MESSAGE_STRING, errors);
	}

	public InvalidDataException(String message, Map<String, String> errors) {
		super(message);
		this.errors = errors;
	}

	public InvalidDataException(Throwable cause) {
		this(MESSAGE_STRING, cause);
=======
	public InvalidDataException(Throwable cause) {
		super(cause);
>>>>>>> 862e2d75bdfc08585e5d4099527348875a982978
	}

	public InvalidDataException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidDataException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

<<<<<<< HEAD
	public InvalidDataException(Throwable cause, Map<String, String> errors) {
		this(MESSAGE_STRING, cause, errors);
	}

	public InvalidDataException(String message, Throwable cause, Map<String, String> errors) {
		super(message, cause);
		this.errors = errors;
	}

	public InvalidDataException(String message, Throwable cause, Map<String, String> errors, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.errors = errors;
	}

	public boolean hasErrors() { return errors != null; }

	public Map<String, String> getErrors() {
		return errors;
	}

}
=======
}
>>>>>>> 862e2d75bdfc08585e5d4099527348875a982978
