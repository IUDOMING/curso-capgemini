package com.example.demo.domains.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.example.demo.domains.core.entities.EntityBase;
import com.example.demo.domains.core.validations.NIF;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * The persistent class for the actor database table.
 * 
 */
@Entity
@Table(name = "actor")
@NamedQuery(name = "Actor.findAll", query = "SELECT a FROM Actor a")

//Hereda de EntityBase, que es nuestro validador de errores
public class Actor extends EntityBase<Actor> implements Serializable {
	private static final long serialVersionUID = 1L;

	// Con el @Id está implicito el not Null
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "actor_id", unique = true, nullable = false)
	private int actorId;

	// Los datos de @Column, solo se aplican si se genera la BBDD desde nuestras
	// entidades
	// No afectan a las validaciones.
	@Column(name = "first_name", nullable = false, length = 45)
	// Como no puede ser nulo ponemos directamente el @NotBlank cubriendo el String
	// vacio y espacios en blanco además del Null
	@NotBlank
	// Indicamos el tamaño máximo que se aplicará en las validaciones.
	// Usarlo siempre que haya un lenght
	@Size(max = 45, min = 2)
	//Esto era para probar el DNI
//	@NIF
	private String firstName;

	@Column(name = "last_name", nullable = false, length = 45)
	@Size(max = 45, min = 2)
	// Se indica que solo hay mayusculas. No hace falta indicar el tamaño del
	// pattern ya que
	// el @Size lo controla.
	@Pattern(regexp = "[A-Z]+", message = "Tiene que estar en mayúsculas.")
	private String lastName;

	@Column(name = "last_update", insertable = false, updatable = false, nullable = false)
	@PastOrPresent
	private Timestamp lastUpdate;

	// bi-directional many-to-one association to FilmActor
	@OneToMany(mappedBy = "actor", fetch = FetchType.EAGER)
	private List<FilmActor> filmActors = new ArrayList<>();

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

	@Override
	public String toString() {
		return "Actor [actorId=" + actorId + ", firstName=" + firstName + ", lastName=" + lastName + ", lastUpdate="
				+ lastUpdate + "]";
	}

	public void jubilate() {

	}

	public void recibePremio(String premio) {

	}
}