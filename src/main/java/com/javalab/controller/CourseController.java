package com.javalab.controller;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javalab.dto.CourseDTO;
import com.javalab.entity.Course;
import com.javalab.entity.ScrapCourse;
import com.javalab.entity.User;
import com.javalab.service.CourseService;

@Controller
@RequestMapping("/course")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // 코스 리스트 조회
    @GetMapping("/list")
    public String getAllCourses(Model model) {
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courseList", courses);

        return "course/CourseList";
    }
    
    @GetMapping("/findByTitle")
    public String getCourseById(@RequestParam String courseTitle, Model model) {
        CourseDTO dto = courseService.findByTitle(courseTitle);
        
        // 이미지 파일 이름 배열 생성
        List<String> imageNames = getImageNamesForCourse(dto);
        
        // 이미지 파일 이름을 CourseDTO에 설정
        dto.setImages(imageNames);
        
        model.addAttribute("course", dto);
        return "course/CourseDetail";
    }
    
    // 이미지 파일 이름 배열 생성 메서드
    private List<String> getImageNamesForCourse(CourseDTO dto) {
        List<String> imageNames = new ArrayList<>();

        String city = dto.getCity(); // 코스의 도시 정보
        List<String> placeTitles = dto.getPlaceTitle(); // 코스의 지역 타이틀들

        for (String placeTitle : placeTitles) {
            String imageName = "/images/ima/" + city + "/" + placeTitle + "%20(1).jpg";
            imageNames.add(imageName);
        }

        return imageNames;
    }

	@GetMapping("/course/filterByRegion")
    public List<Course> filterByRegion(@RequestParam("region") String region) {
      // 해당하는 코스 데이터 필터링 및 반환
      List<Course> filteredCourses = courseService.filterByRegion(region);
      return filteredCourses;
    }
 
}
