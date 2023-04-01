package com.example.domains.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

import com.example.domains.core.entities.EntityBase;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

//Creation Actor Object related to Actor Table


@Entity
//Name of the table in BBDD
@Table(name = "category")
@NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c")
public class Category extends EntityBase<Category> implements Serializable {
	private static final long serialVersionUID = 1L;

	// Ceration of Object attributes

	// Identifies PK (Primary Key)
	@Id
	// Sets generations strategies for PKs
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// Identifies the name of the field related to the Table plus info of the
	// configurations
	@Column(name = "category_id")
	@JsonProperty("id")
	private int categoryId;

	@Column(name = "last_update", insertable = false, updatable = false)
	// Checks if time is Past or Present.
	@PastOrPresent
	@JsonIgnore
	private Timestamp lastUpdate;

	// Can't be empty, null and lenght > 0
	@NotBlank
	// Sets max and min size of the element
	@Size(max = 25)
	@JsonProperty("categoria")
	private String name;

	// bi-directional many-to-one association to FilmCategory
	// Sets a relation
	@OneToMany(mappedBy = "category")
	@JsonIgnore
	private List<FilmCategory> filmCategories;

	// Creation of constructors
	
	public Category() {
	}

	public Category(int categoryId) {
		super();
		this.categoryId = categoryId;
	}
	
	// Basic getters and setters

	public int getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<FilmCategory> getFilmCategories() {
		return this.filmCategories;
	}

	public void setFilmCategories(List<FilmCategory> filmCategories) {
		this.filmCategories = filmCategories;
	}

	public FilmCategory addFilmCategory(FilmCategory filmCategory) {
		getFilmCategories().add(filmCategory);
		filmCategory.setCategory(this);

		return filmCategory;
	}

	public FilmCategory removeFilmCategory(FilmCategory filmCategory) {
		getFilmCategories().remove(filmCategory);
		filmCategory.setCategory(null);

		return filmCategory;
	}

	@Override
	public int hashCode() {
		return Objects.hash(categoryId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		return categoryId == other.categoryId;
	}

	// Override of the method String, getting only the attributes we need.
	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", name=" + name + ", lastUpdate=" + lastUpdate + "]";
	}

}