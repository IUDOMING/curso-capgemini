package com.example.domains.core.validations;

public class CadenasValidator {
<<<<<<< HEAD
=======

>>>>>>> 862e2d75bdfc08585e5d4099527348875a982978
	public static boolean isNIF(String value) {
		if (value == null)
			return true;
		value = value.toUpperCase();
		if (!value.matches("^\\d{1,8}[A-Z]$") || Integer.parseInt(value.substring(0, value.length() - 1)) == 0)
			return false;
		return "TRWAGMYFPDXBNJZSQVHLCKE".charAt(Integer.parseInt(value.substring(0, value.length() - 1)) % 23) == value
				.charAt(value.length() - 1);
	}
	public static boolean isNotNIF(String value) {
		return !isNIF(value);
	}
}