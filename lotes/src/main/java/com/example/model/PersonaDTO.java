package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//Como llega la estructura desde fuera (Objeto de Transferencia de Datos)
//Se corresponde en como está clasificada la información en los ".csv"
public class PersonaDTO {
	private long id;
	private String nombre, apellidos, correo, sexo, ip;
}
