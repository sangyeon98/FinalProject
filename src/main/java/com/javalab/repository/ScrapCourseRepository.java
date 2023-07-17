package com.javalab.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.javalab.entity.ScrapCourse;


public interface ScrapCourseRepository extends JpaRepository<ScrapCourse, Integer> {
	List<ScrapCourse> findByUser_UserId(String userId);
	
}