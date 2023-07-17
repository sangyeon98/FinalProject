package com.javalab.service;

import com.javalab.dto.ScrapCourseDTO;
import com.javalab.entity.Course;
import com.javalab.entity.ScrapCourse;
import com.javalab.entity.User;

public interface ScrapCourseService {
	
	ScrapCourse register(ScrapCourseDTO scrapCourse);
	
	default ScrapCourse dtoToEntity(ScrapCourseDTO dto) {
		
		
		
		User userId = new User();
		userId.setUserId(dto.getUser().getUserId());
		
		Course course = new Course();
        course.setCourseNo(dto.getCourseNo());
		
//		Course courseNo = null;
//        if (dto.getCourse() != null) {
//            if (dto.getCourse().getCourseNo() != null) {
//                courseNo = new Course();
//                courseNo.setCourseNo(dto.getCourse().getCourseNo());
//            }
//        }
		
		ScrapCourse entity= ScrapCourse.builder()
							.scrapNo(dto.getScrapNo())
							.user(userId)
							.course(course)
							.build();
		return entity;	
	}


}
