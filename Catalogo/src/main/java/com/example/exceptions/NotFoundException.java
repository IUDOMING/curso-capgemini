package com.example.exceptions;

public class NotFoundException extends Exception {
<<<<<<< HEAD
	private static final long serialVersionUID = 1L;
	private final static String MESSAGE_STRING = "Not found";
	
	public NotFoundException() {
		this(MESSAGE_STRING);
=======

	public NotFoundException() {
		// TODO Auto-generated constructor stub
>>>>>>> 862e2d75bdfc08585e5d4099527348875a982978
	}

	public NotFoundException(String message) {
		super(message);
<<<<<<< HEAD
	}

	public NotFoundException(Throwable cause) {
		this(MESSAGE_STRING, cause);
=======
		// TODO Auto-generated constructor stub
	}

	public NotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
>>>>>>> 862e2d75bdfc08585e5d4099527348875a982978
	}

	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
<<<<<<< HEAD
	}

	public NotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
=======
		// TODO Auto-generated constructor stub
	}

	public NotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
>>>>>>> 862e2d75bdfc08585e5d4099527348875a982978
