package com.javalab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.javalab.entity.PlaceReview;

public interface PlaceReviewRepository extends JpaRepository<PlaceReview, Integer>{

	@Query("SELECT pr FROM PlaceReview pr WHERE pr.place.placeTitle = :placeTitle")
	List<PlaceReview> findByPlaceTitle(@Param("placeTitle")String placeTitle);

}
