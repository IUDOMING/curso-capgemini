package com.example.demo.ioc;

//Mediante esta interfaz, solucionamos la flexibilidad de los genéricos del Repositorio.
//Se da la información de que tipo usará la interfaz de la que extiende.
public interface StringRepository extends Repository<String>{

}
