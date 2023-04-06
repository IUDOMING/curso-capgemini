package com.example.demo.domains.entities.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Value;

@Value
public class ElementoDTO<K, V> {
	@JsonProperty("id")
	private K keyK;
	@JsonProperty("titulo")
	private V value;
}
