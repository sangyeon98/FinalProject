package com.javalab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.javalab.dto.ScrapCourseDTO;
import com.javalab.entity.ScrapCourse;

@Repository
public interface MypageRepository extends JpaRepository<ScrapCourse, Integer> {
    @Query("SELECT s FROM ScrapCourse s WHERE s.user.userId = :userId")
    List<ScrapCourse> findScrapCoursesByUserId(@Param("userId") String userId);
}