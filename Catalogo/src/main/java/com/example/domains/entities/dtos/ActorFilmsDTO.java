package com.example.domains.entities.dtos;

import lombok.Value;

@Value
public class ActorFilmsDTO<K, V> {
	private K id;
	private V name;
}
