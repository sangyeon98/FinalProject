package com.javalab.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.stream.Collectors;
import com.javalab.dto.CourseDTO;
import com.javalab.entity.Course;
import com.javalab.repository.CourseRepository;


@Service
public class CourseServiceImpl implements CourseService {
	
	@Autowired
	private CourseRepository courseRepository;

	@Override
	public List<Course> getAllCourses() {
      return courseRepository.findAll();
	}
	
	// 코스 한개 조회
	@Override
	public CourseDTO findByTitle(String courseTitle) {
	    List<Course> courses = courseRepository.findAllByCourseTitle(courseTitle);
	    if (!courses.isEmpty()) {
	        Course course = courses.get(0); // 첫 번째 코스 선택 또는 로직에 맞게 선택
	        List<String> placeTitles = courseRepository.getPlaceTitlesByCourseTitle(courseTitle);
	        CourseDTO courseDTO = entityToDto(course);
	        courseDTO.setPlaceTitle(placeTitles);
	        return courseDTO;
	    }
	    return null;
	}
	
//	// 코스타이틀별 지역타이틀
//	@Override
//	public List<String> getPlaceTitlesByCourseTitle(String courseTitle) {
//	    List<Course> courses = courseRepository.findByCourseTitle(courseTitle);
//	    List<String> placeTitles = new ArrayList<>();
//	    for (Course course : courses) {
//	        String placeTitle = course.getPlace().getPlaceTitle();
//	        placeTitles.add(placeTitle);
//	    }
//	    return placeTitles;
//	}
//	
	@Override
	public List<CourseDTO> getCourseList() {
	    List<Course> entityList = courseRepository.findAll();
	    
	    return entityList.stream()
	            .map(entity -> entityToDto(entity))
	            .collect(Collectors.toList());
	}

	// 지역별 코스
	@Override
	public List<Course> filterByRegion(String region) {
		return null;
	}

	 public Course getCourseById(Integer courseNo) {
	        return courseRepository.findById(courseNo).orElse(null);
	    }

}
