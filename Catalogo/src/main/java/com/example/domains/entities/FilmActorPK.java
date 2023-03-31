package com.example.domains.entities;

import java.io.Serializable;
import jakarta.persistence.*;

<<<<<<< HEAD
=======
/**
 * The primary key class for the film_actor database table.
 * 
 */
>>>>>>> 862e2d75bdfc08585e5d4099527348875a982978
@Embeddable
public class FilmActorPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

<<<<<<< HEAD
	@Column(name="actor_id", insertable=false, updatable=false)
	private int actorId;

	@Column(name="film_id", insertable=false, updatable=false)
=======
	@Column(name="actor_id", insertable=false, updatable=false, unique=true, nullable=false)
	private int actorId;

	@Column(name="film_id", insertable=false, updatable=false, unique=true, nullable=false)
>>>>>>> 862e2d75bdfc08585e5d4099527348875a982978
	private int filmId;

	public FilmActorPK() {
	}
<<<<<<< HEAD
	public FilmActorPK(int filmId, int actorId) {
		this.actorId = actorId;
		this.filmId = filmId;
	}
=======
>>>>>>> 862e2d75bdfc08585e5d4099527348875a982978
	public int getActorId() {
		return this.actorId;
	}
	public void setActorId(int actorId) {
		this.actorId = actorId;
	}
	public int getFilmId() {
		return this.filmId;
	}
	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FilmActorPK)) {
			return false;
		}
		FilmActorPK castOther = (FilmActorPK)other;
		return 
			(this.actorId == castOther.actorId)
			&& (this.filmId == castOther.filmId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.actorId;
		hash = hash * prime + this.filmId;
		
		return hash;
	}
}