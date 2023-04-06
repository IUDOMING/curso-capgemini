package com.example.domains.entities.dtos;

import lombok.Value;

@Value
public class FilmsActorsDTO<K, V> {
	private K id;
	private V title;
}
