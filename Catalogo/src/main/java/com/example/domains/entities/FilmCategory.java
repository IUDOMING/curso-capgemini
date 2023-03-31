package com.example.domains.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "film_category")
@NamedQuery(name = "FilmCategory.findAll", query = "SELECT f FROM FilmCategory f")
public class FilmCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FilmCategoryPK id;

	@Column(name = "last_update", insertable = false, updatable = false)
	private Timestamp lastUpdate;

	// bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name = "category_id", insertable = false, updatable = false)
	@NotNull
	private Category category;

	// bi-directional many-to-one association to Film
	@ManyToOne
	@JoinColumn(name = "film_id", insertable = false, updatable = false)
	@NotNull
	private Film film;

	public FilmCategory() {
	}

	public FilmCategory(Film film, Category category) {
		this.film = film;
		this.category = category;
		setId(new FilmCategoryPK(film.getFilmId(), category.getCategoryId()));
	}

	public FilmCategoryPK getId() {
		return this.id;
	}

	public void setId(FilmCategoryPK id) {
		this.id = id;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Film getFilm() {
		return this.film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

}