package com.javalab.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import com.javalab.dto.CityDTO;
import com.javalab.dto.CourseDTO;
import com.javalab.entity.Course;


public interface CourseService {
	
	// 코스 목록 조회
	List<CourseDTO> getCourseList();
	// 코스 상세 조회
	CourseDTO findByTitle(String courseTitle);
	
	// DTO -> Entity 전환
    default Course dtoToEntity(CourseDTO dto) {
    	return Course.builder()
            .courseNo(dto.getCourseNo())
            .courseTitle(dto.getCourseTitle())
            .courseContent(dto.getCourseContent())
            .courseKilometer(dto.getCourseKilometer())
            .courseDay(dto.getCourseDay())
            .build();
    }
    
    // Entity -> DTO 전환
    default CourseDTO entityToDto(Course course) {
    	CityDTO cityDTO = new CityDTO();
        cityDTO.setCityName(course.getPlace().getCity().getCityName());
        String placeTitle = course.getPlace().getPlaceTitle();
        List<String> placeTitles = new ArrayList<>(Collections.singletonList(placeTitle));
        
        List<String> images = new ArrayList<>();
        
        // 이미지 파일 경로 추가 로직
        String imagePath = "/images/ima/" + cityDTO.getCityName() + "/" + placeTitle + " (1).jpg";
        images.add(imagePath);

        return CourseDTO.builder()
            .courseNo(course.getCourseNo())
            .courseTitle(course.getCourseTitle())
            .courseContent(course.getCourseContent())
            .courseKilometer(course.getCourseKilometer())
            .courseDay(course.getCourseDay())
            .city(cityDTO.getCityName())
            .placeTitle(Arrays.asList(placeTitle))
            .images(placeTitles)
            .build();
    }

	List<Course> getAllCourses();
	List<Course> filterByRegion(String region);
	static Course getCourseById(Integer courseNo) {
		// TODO Auto-generated method stub
		return null;
	}

	
}