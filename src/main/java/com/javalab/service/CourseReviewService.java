package com.javalab.service;

import java.util.List;

import com.javalab.dto.CourseReviewDTO;
import com.javalab.entity.Course;
import com.javalab.entity.CourseReview;
import com.javalab.entity.User;

public interface CourseReviewService {

	
	//리뷰
	CourseReview createComment(CourseReviewDTO courseReview);
	
	List<CourseReviewDTO> getCommentsByCourseNo(Integer courseNo);
	
	
	default CourseReview dtoToEntity(CourseReviewDTO dto) {
	    return CourseReview.builder()
	    		.reviewContent(dto.getReviewContent())
	    		.course(new Course(dto.getCourseNo()))
	    		.user(new User(dto.getUserId()))
	    		.build();
	}

	default CourseReviewDTO entityToDTO(CourseReview courseReview) {
	    CourseReviewDTO dto = new CourseReviewDTO();
	    	dto.setCourseNo(courseReview.getReviewNo());
	    	dto.setReviewContent(courseReview.getReviewContent());

	    Integer courseNo = courseReview.getCourse().getCourseNo();
	    dto.setCourseNo(courseNo);
	    
	    String userId = courseReview.getUser().getUserId();
	    dto.setUserId(userId);
	    
	    return dto;
	}

}
