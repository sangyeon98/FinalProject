package com.javalab.dto;

import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceDTO {
    private String placeTitle;
    private String placeComment;
    private Date placeDate;
    private String placeContent;
    private String placeParking;
    private String placeOff;
    private String placeTime;
    private String placePrice;
    private String placeUrl;
    private String placeTel;
    private CityDTO city;
    
    
    private List<CityDTO> dtoCityList;
    
    private List<CourseDTO> courses;
    private List<ScrapPlaceDTO> scrapPlaces;
	
}