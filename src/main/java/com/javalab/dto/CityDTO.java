package com.javalab.dto;

import java.util.List;

import com.javalab.entity.Place;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityDTO {
	
	private String cityName;
	private List<Place> places;

}
