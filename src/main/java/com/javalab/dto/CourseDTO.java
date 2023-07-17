package com.javalab.dto;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {
    private Integer courseNo;
    private String courseTitle;
    private String courseContent;
    private String courseKilometer;
    private String courseDay;
    private PlaceDTO place; 
    private List<String> placeTitle;
    private String city;
    private String cityName;
    private List<String> images;
    
    public CourseDTO(Integer courseNo) {
        this.courseNo = courseNo;
    }

  
    public Integer getCourseNo() {
        return courseNo;
    }

    public void setCourseNo(Integer courseNo) {
        this.courseNo = courseNo;
    }
    
    public List<String> getPlaceTitle() {
        return placeTitle;
    }

    public void setPlaceTitle(List<String> placeTitle) {
        this.placeTitle = placeTitle;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    
    // 이미지 파일 경로를 설정하는 메서드
    public void setImages(List<String> images) {
        this.images = images;
    }

    // 이미지 파일 경로를 가져오는 메서드
    public List<String> getImages() {
        return images;
    }
    
    private List<CourseDTO> course;
}