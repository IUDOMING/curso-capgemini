package com.example.demo.domains.entities.dtos;

import lombok.Value;

@Value
public class ElementoDTO<K, V> {
	private K keyK;
	private V value;
}
