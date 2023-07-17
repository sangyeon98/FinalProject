package com.javalab.service;




import java.util.List;

import com.javalab.dto.CityDTO;
import com.javalab.dto.PageRequestDTO;
import com.javalab.dto.PageResultDTO;
import com.javalab.dto.PlaceDTO;
import com.javalab.entity.Place;


public interface PlaceService {
	
	//descending
	PageResultDTO<PlaceDTO, Place> getList(PageRequestDTO requestDTO);
	//ascending
	PageResultDTO<PlaceDTO, Place> getList2(PageRequestDTO requestDTO);
	
	PlaceDTO read(String placeTitle);
	
	 //List<Place> getPlacesByCityName(String cityName);


	//decending
	public PageResultDTO<PlaceDTO, Place> findByPlaceByCityName(PageRequestDTO requestDTO);
	//ascending
	public PageResultDTO<PlaceDTO, Place> findByPlaceByCityName2(PageRequestDTO requestDTO);
	
	
	
	
	
	
	default Place dtoToEntity(PlaceDTO placeDTO) {
		return Place.builder()
				.placeTitle(placeDTO.getPlaceTitle())
				.placeComment(placeDTO.getPlaceComment())
				.placeParking(placeDTO.getPlaceParking())
				.placeOff(placeDTO.getPlaceOff())
				.placeContent(placeDTO.getPlaceContent())
				.placeTime(placeDTO.getPlaceTime())
				.placePrice(placeDTO.getPlacePrice())
				.placeURL(placeDTO.getPlaceUrl())
				.placeTel(placeDTO.getPlaceTel())
				.build();
	}
	
	default PlaceDTO entityToDto(Place place) {
		
		CityDTO cityDTO = new CityDTO();
		cityDTO.setCityName(place.getCity().getCityName());
		
		return PlaceDTO.builder()
				.placeTitle(place.getPlaceTitle())
				.placeComment(place.getPlaceComment())
				.placeContent(place.getPlaceContent())
				.placeParking(place.getPlaceParking())
				.placeOff(place.getPlaceOff())
				.placeTime(place.getPlaceTime())
				.placePrice(place.getPlacePrice())
				.placeUrl(place.getPlaceURL())
				.placeTel(place.getPlaceTel())
				.city(cityDTO)
				.build();
				
	}

	
	
}
