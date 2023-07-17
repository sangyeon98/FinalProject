package com.javalab.service;

import com.javalab.dto.ScrapPlaceDTO;
import com.javalab.entity.Place;
import com.javalab.entity.ScrapPlace;
import com.javalab.entity.User;

public interface ScrapPlaceService {
	
	ScrapPlace register(ScrapPlaceDTO scrapPlace);
	
	
	
	default ScrapPlace dtoToEntity(ScrapPlaceDTO dto) {
		
		User userId = new User();
		userId.setUserId(dto.getUser().getUserId());
		
		Place placeTitle = new Place();
		placeTitle.setPlaceTitle(dto.getPlace().getPlaceTitle());
		
		ScrapPlace entity= ScrapPlace.builder()
							.scrapNo(dto.getScrapNumber())
							.user(userId)
							.place(placeTitle)
							.build();
		return entity;	
	}


	


	

}
