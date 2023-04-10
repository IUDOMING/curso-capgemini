package com.example.domains.entities.dtos;

import lombok.Value;

@Value
public class CategoriesFilmDTO<K, V> {
	private K id;
	private V Category;
}
