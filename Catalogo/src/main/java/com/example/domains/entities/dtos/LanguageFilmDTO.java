package com.example.domains.entities.dtos;

import lombok.Value;

@Value
public class LanguageFilmDTO<K, V> {
	private K id;
	private V language;
}
