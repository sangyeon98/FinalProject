package com.javalab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javalab.entity.ScrapPlace;

public interface ScrapPlaceRepository extends JpaRepository<ScrapPlace, Integer> {
	List<ScrapPlace> findByUser_UserId(String userId);
}
