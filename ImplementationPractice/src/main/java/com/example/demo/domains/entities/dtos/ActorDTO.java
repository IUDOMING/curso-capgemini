package com.example.demo.domains.entities.dtos;

import com.example.demo.domains.entities.Actor;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Value;

@Value
public class ActorDTO {

	@JsonProperty("id")
	private int actorId;
	@JsonProperty("nombre")
	private String firstName;
	@JsonProperty("apellido")
	private String lastName;

	public static ActorDTO from(Actor target) {
		return new ActorDTO(target.getActorId(), target.getFirstName(), target.getLastName());
	}

	public static Actor from(ActorDTO target) {
		return new Actor(target.getActorId(), target.getFirstName(), target.getLastName());
	}
}
