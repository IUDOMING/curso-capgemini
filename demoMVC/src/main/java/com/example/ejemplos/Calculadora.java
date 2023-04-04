package com.example.ejemplos;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculadora {
	public double suma(double a, double b) {
		BigDecimal rslt = BigDecimal.valueOf(a + b);
		//Como patina el último byte, se saca el último digito 
		//(ya que no se puede sacar solo un byte)
		return rslt.setScale(15, RoundingMode.HALF_DOWN).doubleValue();	}

	public double divide(double a, double b) {
		if (b==0) throw new ArithmeticException("Divided by 0 / Not paossible.");
		return a / b;
	}

	public double divide(int a, int b) {
		if (b==0) throw new ArithmeticException("Divided by 0 / Not paossible.");
		return a / b;
	}
	
}