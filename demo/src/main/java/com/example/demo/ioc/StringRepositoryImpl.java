package com.example.demo.ioc;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

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
	public void save(String item) {
		System.out.println("Guardo el item: " + item);

	}

}
