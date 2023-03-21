package com.example.demo.ioc;


//Ejemplo de doble herencia Interfaz+Clase
//<T> Lo convierte en genérico
//Repositorio es quien se relaciona con la BBDD
public interface Repository <T>{
	
	T load();
	void save (T item);

}


