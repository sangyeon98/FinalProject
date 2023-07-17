package com.javalab.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.javalab.entity.City;
import com.javalab.entity.Place;


public interface PlaceRepository extends JpaRepository<Place, String> {

	
	@Query("SELECT p FROM Place p LEFT JOIN p.city c WHERE c.cityName LIKE %:cityName%")
	Page<Place> findByPlaceByCityName(@Param("cityName") String cityName, Pageable pageable);
	
	@Query("SELECT p FROM Place p LEFT JOIN p.city c WHERE c.cityName LIKE %:cityName%")
	Page<Place> findByPlaceByCityName2(@Param("cityName") String cityName, Pageable pageable);
	

	
	



	
	
	
	
}