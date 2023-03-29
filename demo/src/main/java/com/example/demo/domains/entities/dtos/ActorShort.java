package com.example.demo.domains.entities.dtos;

import org.springframework.beans.factory.annotation.Value;

public interface ActorShort {

	//Proyección
	int getActorId();
	//Le estás dando una información diferente a la de la entiad
	//En este caso, el DTO hace que el nombre sea el Nombre y el Apellido
	@Value("#{target.firstName + ' ' + target.lastName}")
	
	String getNombre();

}
