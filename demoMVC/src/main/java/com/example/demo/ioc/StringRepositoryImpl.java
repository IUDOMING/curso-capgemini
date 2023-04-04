package com.example.demo.ioc;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;


import com.example.demo.exceptions.InvalidDataException;

//Para que Spring detecte esta clase se ha de usar @Component o @Repository debido a su origen
//(Diferencia solo semantica) (Revisar por si acaso) Básico, hecho en el curso
@Repository

//Se indica que es el Default en caso de que haya más de uno con nombres parecidos
@Primary

public class StringRepositoryImpl implements StringRepository {

	@Override
	public String load() {
		// TODO Auto-generated method stub
		return "Soy el StringRepositoryImpl";
	}

	@Override
	public void save(String item) throws InvalidDataException {

		// Al ser una excepción de Runtime, no ha de ser tratada
		// if (item=="") throw new ArgumentoInvalidoException();

		// Como se trata de una excepción general, ha de ser tratada
		// Las excepciones han de ser parte de las Interfaces si una clase implementa
		// una interfaz
		if (item == "")
			throw new InvalidDataException("La cadena no puede estar vacía");
		System.out.println("Guardo el item: " + item);

	}

}
