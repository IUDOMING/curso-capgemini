package com.example.demo.domains.contracts.repositories;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domains.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	List<Category> findByLastUpdateGreaterThanEqualOrderByLastUpdate(Timestamp fecha);
}