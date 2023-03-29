package com.example.demo.domains.entities.dtos;

import com.example.demo.domains.entities.Actor;

import lombok.Value;

//@Data @AllArgsConstructor @NoArgsConstructor

@Value
public class ActorDTO {

	private int actorId;

	private String firstName;

	private String lastName;
	
	
	// De ActorDTO se pasa a Actor
	public static ActorDTO from(Actor target) {
		return new ActorDTO(target.getActorId(), target.getFirstName(), target.getLastName());
	}

	//De Actor se pasa a ActorDTO
	public static Actor from(ActorDTO target) {
		return new Actor(target.getActorId(), target.getFirstName(), target.getLastName());
	}
}
