package com.javalab.service;

import org.springframework.stereotype.Service;

import com.javalab.dto.ScrapPlaceDTO;
import com.javalab.entity.ScrapPlace;
import com.javalab.repository.ScrapPlaceRepository;

@Service
public class ScrapPlaceServiceImpl implements ScrapPlaceService{

	private final ScrapPlaceRepository scrapPlaceRepository;
	
	public ScrapPlaceServiceImpl(ScrapPlaceRepository scrapPlaceRepository) {
		this.scrapPlaceRepository = scrapPlaceRepository;
	}
	
	@Override
	public ScrapPlace register(ScrapPlaceDTO scrapPlace) {
		ScrapPlace entity = dtoToEntity(scrapPlace);
		return scrapPlaceRepository.save(entity);
	}



}
