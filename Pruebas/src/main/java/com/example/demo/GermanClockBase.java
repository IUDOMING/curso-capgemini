package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class GermanClockBase {

	private String lowRow;
	private String upRow;

	public String convert(String str) {

		// Recive the String of the time and split it ":"
		// The ".split" returns an array
		String[] splitTime = str.split(":");

		// Convert the hours to int and send it to covert
		// We get their value according to their position in the Array
		// Will return a String following the method instructions
		String horas = horasConvert(Integer.parseInt(splitTime[0]));

		// Convert the minutes to int and send it to covert
		// We get their value according to their position in the Array
		// Will return a String following the method instructions
		String min = minConvert(Integer.parseInt(splitTime[1]));

		// Convert the seconds to int and send it to covert
		// We get their value according to their position in the Array
		// Will return a String following the method instructions
		String sec = secConvert(Integer.parseInt(splitTime[2]));

		return sec + "\n" + horas + "\n" + min;
	}

	// Method that recives, transforms and returns the hours
	// Every hour will light the lower row, adding a Y to the O row to a max of 4.
	// Every 5 hours, it will light one of the upper rows. And lower row will
	// reset with 0.
	// Upper row indicates multiples of 5.
	public String horasConvert(int h) {
		lowRow = "OOOO";
		upRow = "OOOO";

		// If hours equals 0, will return the default value
		if (h == 0) {
			return upRow + "\n" + lowRow;
		}

		// If hours are not 00, will enter this condition
		else {

			// Loop "FOR" for replacing the values in the string

			for (int i = 1; i <= h; i++) {
				// Each loop, will replace the first "O" value for an "Y" until it reaches a
				// multiple of 5
				lowRow = lowRow.replaceFirst("O", "R");

				// If the hour is multiple of 5 will enter this condition
				if (i % 5 == 0) {

					// Will replace the upper row first "O" with a "Y"
					upRow = upRow.replaceFirst("O", "R");

					// Low Row will be set to default
					lowRow = "OOOO";
				}
			}

		}
		return upRow + "\n" + lowRow;
	}

	// Method that recives, transforms and returns the minutes
	// Every minut will light the lower row, adding a Y to the O row to a max of 4.
	// Every 5 minutes, it will light one of the upper rows. And lower row will
	// reset with 0.
	// Upper row indicates multiples of 5.
	// Every 15 minutes, the uper row will light with an R.
	public String minConvert(int min) {

		// Lower Row default 00 state
		lowRow = "OOOO";

		// Upper Row default 00 state
		upRow = "OOOOOOOOOOO";

		// If min equals 00 will return the default values
		if (min == 0)
			return upRow + "\n" + lowRow;

		// If minutes are not 00 will enter the condition
		else {

			// Loop "FOR" for replacing the values in the string
			for (int i = 1; i <= min; i++) {

				// Each loop, will replace the first "O" value for an "Y" until it reaches a
				// multiple of 5
				lowRow = lowRow.replaceFirst("O", "Y");

				// When it reaches a multiple of 5 but not of 15 will enter this condition
				if (i % 5 == 0 && i % 15 != 0) {

					// Will replace the upper Row first "O" value for a "Y" value
					upRow = upRow.replaceFirst("O", "Y");

					// Lower Row will reset to default status
					lowRow = "OOOO";

					// When it reaches a multiple of 15 will enter this condition
				} else if (i % 15 == 0) {

					// Will replace Upper Row first "O" value for a "R" value
					upRow = upRow.replaceFirst("O", "R");

					// Lower Row will reset to default
					lowRow = "OOOO";
				}

			}
		}
		return upRow + "\n" + lowRow;
	}

	// Method that recives, transforms and returns the seconds
	// If the value of the seconds is divisible by 2, it will retunr "Y", otherwise
	// "O"
	public String secConvert(int sec) {
		if (sec % 2 == 0)
			return "Y";
		else
			return "O";

	}
}