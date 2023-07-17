package com.javalab.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javalab.entity.City;

public interface CityRepository extends JpaRepository<City, String> {

}
