package com.javalab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.javalab.entity.CourseReview;

public interface CourseReviewRepository extends JpaRepository<CourseReview, Integer>{
	@Query("SELECT cr FROM CourseReview cr WHERE cr.course.courseNo = :courseNo")
	List<CourseReview> findByCourseNo(@Param("courseNo") Integer courseNo);

	
}
