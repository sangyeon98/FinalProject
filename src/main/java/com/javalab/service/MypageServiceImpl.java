package com.javalab.service;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javalab.dto.PlaceDTO;
import com.javalab.dto.ScrapCourseDTO;
import com.javalab.dto.ScrapPlaceDTO;
import com.javalab.dto.UserDTO;
import com.javalab.entity.Course;
import com.javalab.entity.ScrapCourse;
import com.javalab.entity.ScrapPlace;
import com.javalab.entity.User;
import com.javalab.repository.CourseRepository;
import com.javalab.repository.MypageRepository;
import com.javalab.repository.ScrapCourseRepository;
import com.javalab.repository.ScrapPlaceRepository;

@Service
public class MypageServiceImpl implements MypageService{
	private final ScrapPlaceRepository scrapPlaceRepository;
	private final ScrapCourseRepository scrapCourseRepository;
	
	@Autowired
	private MypageRepository mypageRepository;
	private CourseRepository courseRepository;
	
	@Autowired
	public MypageServiceImpl(ScrapPlaceRepository scrapPlaceRepository, ScrapCourseRepository scrapCourseRepository, MypageRepository mypageRepository, CourseRepository courseRepository) {
	    this.scrapPlaceRepository = scrapPlaceRepository;
	    this.scrapCourseRepository = scrapCourseRepository;
	    this.mypageRepository = mypageRepository;
	    this.courseRepository = courseRepository;
	}
	
	// 여행지 스크랩
	@Override
	public List<ScrapPlaceDTO> getScrapListByUser(UserDTO user) {
	    List<ScrapPlace> scrapPlaces = scrapPlaceRepository.findByUser_UserId(user.getUserId());
	    List<ScrapPlaceDTO> scrapPlaceDTOs = new ArrayList<>();

	    for (ScrapPlace scrapPlace : scrapPlaces) {
	        ScrapPlaceDTO scrapPlaceDTO = ScrapPlaceDTO.builder()
	                .scrapNumber(scrapPlace.getScrapNo())
	                .user(user)
	                .place(PlaceDTO.builder()
	                        .placeTitle(scrapPlace.getPlace().getPlaceTitle())
	                        .placeComment(scrapPlace.getPlace().getPlaceComment())
	                        .placeContent(scrapPlace.getPlace().getPlaceContent())
	                        .placeTime(scrapPlace.getPlace().getPlaceTime())
	                        .placePrice(scrapPlace.getPlace().getPlacePrice())
	                        .placeTel(scrapPlace.getPlace().getPlaceTel())
	                        .build())
	                .build();
	        scrapPlaceDTOs.add(scrapPlaceDTO);
	    }

	    return scrapPlaceDTOs;
	}

	// 코스 스크랩
	 @Override
	    public List<ScrapCourseDTO> getScrapCourseListByUser(UserDTO user) {
	        List<ScrapCourse> scrapCourses = mypageRepository.findScrapCoursesByUserId(user.getUserId());
	        List<ScrapCourseDTO> scrapCourseDTOs = new ArrayList<>();

	        for (ScrapCourse scrapCourse : scrapCourses) {
	            ScrapCourseDTO scrapCourseDTO = ScrapCourseDTO.builder()
	                    .scrapNo(scrapCourse.getScrapNo())
	                    .user(user)
	                    .courseNo(scrapCourse.getCourse().getCourseNo())
	                    .courseTitle(scrapCourse.getCourse().getCourseTitle())
	                    .build();
	            scrapCourseDTOs.add(scrapCourseDTO);
	        }

	        return scrapCourseDTOs;
	    }

	
	
	
	
	
	
	
	
	
	
	
	

	

}
