package com.example.domains.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.example.domains.core.entities.EntityBase;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

//Creation Actor Object related to Actor Table

@Entity
//Name of the table in BBDD
@Table(name = "actor")
@NamedQuery(name = "Actor.findAll", query = "SELECT a FROM Actor a")
public class Actor extends EntityBase<Actor> implements Serializable {
	private static final long serialVersionUID = 1L;

	// Ceration of Object attributes

	// Identifies PK (Primary Key)
	@Id
	// Sets generations strategies for PKs
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// Identifies the name of the field related to the Table plus info of the
	// configurations
	@Column(name = "actor_id", unique = true, nullable = false)
	private int actorId;

	@Column(name = "first_name", nullable = false, length = 45)
	// Can't be empty, null and lenght > 0
	@NotBlank
	// Sets max and min size of the element
	@Size(max = 45, min = 2)
	private String firstName;

	@Column(name = "last_name", nullable = false, length = 45)
	@Size(max = 45, min = 2)
	// Sets a pattern for that element
	@Pattern(regexp = "[A-Z]+", message = "Must be uppercase")
	private String lastName;

	@Column(name = "last_update", insertable = false, updatable = false, nullable = false)
	// Checks if time is Past or Present.
	@PastOrPresent
	private Timestamp lastUpdate;

	// bi-directional many-to-one association to FilmActor
	// Sets a relation
	@OneToMany(mappedBy = "actor", fetch = FetchType.LAZY)
	private List<FilmActor> filmActors = new ArrayList<>();

	// Creation of constructors

	public Actor() {
	}

	public Actor(int actorId) {
		super();
		this.actorId = actorId;
	}

	public Actor(int actorId, String firstName, String lastName) {
		super();
		this.actorId = actorId;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public Actor(int actorId, String firstName, String lastName, Timestamp timestamp ) {
		super();
		this.actorId = actorId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.lastUpdate = timestamp;
	}

	// Basic Getters and Setters

	public int getActorId() {
		return this.actorId;
	}

	public void setActorId(int actorId) {
		this.actorId = actorId;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public List<FilmActor> getFilmActors() {
		return this.filmActors;
	}

	public void setFilmActors(List<FilmActor> filmActors) {
		this.filmActors = filmActors;
	}

	
	
	public FilmActor addFilmActor(FilmActor filmActor) {
		getFilmActors().add(filmActor);
		filmActor.setActor(this);

		return filmActor;
	}

	public FilmActor removeFilmActor(FilmActor filmActor) {
		getFilmActors().remove(filmActor);
		filmActor.setActor(null);

		return filmActor;
	}

	@Override
	public int hashCode() {
		return Objects.hash(actorId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Actor other = (Actor) obj;
		return actorId == other.actorId;
	}

	// Override of the method String, getting only the attributes we need.
	@Override
	public String toString() {
		return "Actor [actorId=" + actorId + ", firstName=" + firstName + ", lastName=" + lastName + ", lastUpdate="
				+ lastUpdate + "]";
	}

}