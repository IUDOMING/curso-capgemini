package com.example.ejemplos;

import java.util.Optional;

import com.example.demo.exceptions.InvalidDataException;

import lombok.Builder;

@Builder
public class Persona {
	private int id;
	private String nombre;
	private String apellidos;

	public Persona(int id, String nombre, String apellidos) {
		this.id = id;
		setNombre(nombre);
		setApellidos(apellidos);
	}

	public Persona(int id, String nombre) {
		this.id = id;
		setNombre(nombre);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if (nombre == null || "".equals(nombre) || nombre.length() < 2)
			throw new IllegalArgumentException();
		this.nombre = nombre;
	}

	public Optional<String> getApellidos() {
		return Optional.ofNullable(apellidos);
	}

	public void setApellidos(String apellidos) {
		if (apellidos == null)
			throw new IllegalArgumentException();
		this.apellidos = apellidos;
	}

	public void removeApellidos(String apellidos) {
		this.apellidos = null;
	}

}