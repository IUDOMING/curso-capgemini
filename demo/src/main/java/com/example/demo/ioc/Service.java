package com.example.demo.ioc;

//CRUD basico
public interface Service<K, V> {
	// Método para conseguir item
	V get(K id);

	// Método para añadir item
	void add(V item);

	// Método para modificar item
	void modify(V item);

	// Método para eliminar item
	void remove(K id);

}
