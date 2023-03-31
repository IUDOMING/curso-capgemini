package com.example.domains.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "film_actor")
@NamedQuery(name = "FilmActor.findAll", query = "SELECT f FROM FilmActor f")
public class FilmActor implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FilmActorPK id;

	@Column(name = "last_update", insertable = false, updatable = false)
	private Timestamp lastUpdate;

	// bi-directional many-to-one association to Actor
	@ManyToOne
	@JoinColumn(name = "actor_id", insertable = false, updatable = false)
	private Actor actor;

	// bi-directional many-to-one association to Film
	@ManyToOne
	@JoinColumn(name = "film_id", insertable = false, updatable = false)
	private Film film;

	public FilmActor() {
	}

	public FilmActor(Film film, Actor actor) {
		super();
		this.film = film;
		this.actor = actor;
		setId(new FilmActorPK(film.getFilmId(), actor.getActorId()));
	}

	public FilmActorPK getId() {
		return this.id;
	}

	public void setId(FilmActorPK id) {
		this.id = id;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Actor getActor() {
		return this.actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

	public Film getFilm() {
		return this.film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

}