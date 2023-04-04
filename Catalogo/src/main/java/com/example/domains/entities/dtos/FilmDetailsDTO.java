package com.example.domains.entities.dtos;

import java.math.BigDecimal;
import java.util.List;

import com.example.domains.entities.Film;
import com.example.domains.entities.Film.Rating;
import com.example.domains.entities.Language;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Value;

@Value
public class FilmDetailsDTO {
	private int filmId;
	private String title;
	private String description;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy")
	private Short releaseYear;
	private Language language;
	private Language languageVO;
	private byte rentalDuration;
	private BigDecimal rentalRate;
	private BigDecimal replacementCost;
	private int length;
	private Rating rating;
	private List<String> actors;
	private List<String> categories;

	public static FilmDetailsDTO from(Film source) {
		return new FilmDetailsDTO(
				source.getFilmId(),
				source.getTitle(),
				source.getDescription(),
				source.getReleaseYear(),
				source.getLanguage() == null ? null : source.getLanguage(),
				source.getLanguageVO() == null ? null : source.getLanguageVO(),
				source.getRentalDuration(), source.getRentalRate(),
				source.getReplacementCost(),
				source.getLength(),
				source.getRating(),
				source.getActors().stream().map(item-> item.getFirstName() + " " + item.getLastName()).sorted().toList(),
				source.getCategories().stream().map(item->item.getName()).sorted().toList()
				);
	}
	public static Film from(FilmDetailsDTO source) {
		return new Film(
				source.getFilmId(),
				source.getTitle(),
				source.getDescription(),
				source.getReleaseYear(),
				source.getLanguage() == null ? null : source.getLanguage(),
				source.getLanguageVO() == null ? null : source.getLanguageVO(),
				source.getRentalDuration(), source.getRentalRate(),
				source.getLength(),
				source.getReplacementCost(),
				source.getRating());
	}
}
