package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
//Como vamos a trabajar con la estructura que nos va a llegar
public class Persona {
	private long id;
	private String nombre, correo, ip;
}