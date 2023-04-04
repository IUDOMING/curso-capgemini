package com.example.demo.domains.core.validations;

import java.util.Arrays;
import java.util.regex.Pattern;

public class Validation {

	private static final Pattern REGEXP = Pattern.compile("[0-9]{8}[A-Z]");
	private static final String DIGITO_CONTROL = "TRWAGMYFPDXBNJZSQVHLCKE";
	private static final String[] INVALIDOS = new String[] { "00000000T", "00000001R", "99999999R" };

	public Validation() {
		// TODO Auto-generated constructor stub
	}

	public static boolean validarDNI(String dni) {

		if (dni == null)
			return true;
		dni = dni.toUpperCase();
		return Arrays.binarySearch(INVALIDOS, dni) < 0 // (1)
				&& REGEXP.matcher(dni).matches() // (2)
				&& (dni.charAt(8) == DIGITO_CONTROL.charAt(Integer.parseInt(dni.substring(0, 8)) % 23)); // (3)

	}

}
