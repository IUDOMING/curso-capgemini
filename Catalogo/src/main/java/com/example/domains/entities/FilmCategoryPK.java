package com.example.domains.entities;

import java.io.Serializable;
import jakarta.persistence.*;

<<<<<<< HEAD
@Embeddable
public class FilmCategoryPK implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name = "film_id", insertable = false, updatable = false)
	private int filmId;

	@Column(name = "category_id", insertable = false, updatable = false)
	private int categoryId;

	public FilmCategoryPK() {
	}

	public FilmCategoryPK(int filmId, int categoryId) {
		this.filmId = filmId;
		this.categoryId = categoryId;
	}

	public int getFilmId() {
		return this.filmId;
	}

	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}

	public int getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(int categoryId) {
=======
/**
 * The primary key class for the film_category database table.
 * 
 */
@Embeddable
public class FilmCategoryPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="film_id", insertable=false, updatable=false, unique=true, nullable=false)
	private int filmId;

	@Column(name="category_id", insertable=false, updatable=false, unique=true, nullable=false)
	private byte categoryId;

	public FilmCategoryPK() {
	}
	public int getFilmId() {
		return this.filmId;
	}
	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}
	public byte getCategoryId() {
		return this.categoryId;
	}
	public void setCategoryId(byte categoryId) {
>>>>>>> 862e2d75bdfc08585e5d4099527348875a982978
		this.categoryId = categoryId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FilmCategoryPK)) {
			return false;
		}
<<<<<<< HEAD
		FilmCategoryPK castOther = (FilmCategoryPK) other;
		return (this.filmId == castOther.filmId) && (this.categoryId == castOther.categoryId);
=======
		FilmCategoryPK castOther = (FilmCategoryPK)other;
		return 
			(this.filmId == castOther.filmId)
			&& (this.categoryId == castOther.categoryId);
>>>>>>> 862e2d75bdfc08585e5d4099527348875a982978
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.filmId;
		hash = hash * prime + ((int) this.categoryId);
<<<<<<< HEAD

=======
		
>>>>>>> 862e2d75bdfc08585e5d4099527348875a982978
		return hash;
	}
}