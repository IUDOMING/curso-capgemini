package com.example.demo.ioc;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.InvalidDataException;

//Añadimos el comentario para que detecte que es un componente, @Component o @Service
@Service
//Se clasifica la clase con un nombre y se usará para identificar al que llamamos
@Qualifier("Local")
public class StringServiceImpl implements StringService {

	// Data acces object// Persistencia de datos
	// Creamos una variable del objeto String Repository para usarlo.
	private StringRepository dao;

	public StringServiceImpl(StringRepository dao) {

		this.dao = dao;
	}

	@Override
	public String get(Integer id) {
		// TODO Auto-generated method stub
		return dao.load()+ " en local";
	}

	@Override
	public void add(String item)throws InvalidDataException {
		// TODO Auto-generated method stub
		dao.save(item);
	}

	@Override
	public void modify(String item) throws InvalidDataException {
		// TODO Auto-generated method stub
		dao.save(item);
	}

	@Override
	public void remove(Integer id) throws InvalidDataException {
		// TODO Auto-generated method stub
		dao.save(id.toString());
	}

}
