package com.javalab.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseReviewDTO {
	
	private Integer reviewNo;
	private String reviewContent;
	private Integer courseNo;
	private String userId;
	
	public void setUserId(String userId) {
	    this.userId = userId;
	}
	
	public void setCourseNo(Integer courseNo) {
	    this.courseNo = courseNo;
	}
}
