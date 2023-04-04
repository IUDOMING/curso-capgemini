package com.example.demo.ioc;

import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.exceptions.NotFoundException;

//CRUD basico
public interface Service<K, V> {
	// Método para conseguir item
	V get(K id);

	// Método para añadir item
	void add(V item) throws InvalidDataException, NotFoundException;

	// Método para modificar item
	void modify(V item) throws InvalidDataException;

	// Método para eliminar item
	void remove(K id) throws InvalidDataException;

}
