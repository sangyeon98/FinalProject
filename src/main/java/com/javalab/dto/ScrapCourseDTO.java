package com.javalab.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ScrapCourseDTO {

	private Integer scrapNo;
	private UserDTO user;
	private Integer courseNo;
	private String courseTitle;

	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}

	public Integer getScrapNo() {
		return scrapNo;
	}

	public void setScrapNo(Integer scrapNo) {
		this.scrapNo = scrapNo;
	}

	public Integer getCourseNo() {
		return courseNo;
	}

	public void setCourseNo(Integer courseNo) {
		this.courseNo = courseNo;

	}

}
