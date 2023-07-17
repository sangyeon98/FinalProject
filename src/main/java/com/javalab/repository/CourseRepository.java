package com.javalab.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.javalab.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {	
	
	// 상세보기
	List<Course> findAllByCourseTitle(String courseTitle);

	// 코스 타이틀별 지역 타이틀
    @Query("SELECT c.place.placeTitle FROM Course c WHERE c.courseTitle = :courseTitle")
    List<String> getPlaceTitlesByCourseTitle(@Param("courseTitle") String courseTitle);
    
}
