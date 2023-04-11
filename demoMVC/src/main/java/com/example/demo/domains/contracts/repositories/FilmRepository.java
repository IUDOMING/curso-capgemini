package com.example.demo.domains.contracts.repositories;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domains.entities.Film;

public interface FilmRepository extends JpaRepository<Film, Integer> {
	List<Film> findByLastUpdateGreaterThanEqualOrderByLastUpdate(Timestamp fecha);
}