package com.javalab.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.javalab.dto.PageRequestDTO;
import com.javalab.dto.PageResultDTO;
import com.javalab.dto.PlaceDTO;
import com.javalab.entity.Place;
import com.javalab.repository.PlaceRepository;

@Service
public class PlaceServiceImpl implements PlaceService {

	private final PlaceRepository placeRepository;

	public PlaceServiceImpl(PlaceRepository placeRepository) {
		this.placeRepository = placeRepository;
	}

	// List보여주는거, descending
	@Override
	public PageResultDTO<PlaceDTO, Place> getList(PageRequestDTO requestDTO) {
						
		Pageable pageable = requestDTO.getPageable(Sort.by("placeTitle").descending());
		Page<Place> result = placeRepository.findAll(pageable);
		Function<Place, PlaceDTO> fn = member -> entityToDto(member);
		return new PageResultDTO<>(result, fn);
	}
	//List ascending
	@Override
	public PageResultDTO<PlaceDTO, Place> getList2(PageRequestDTO requestDTO) {
		
		Pageable pageable = requestDTO.getPageable(Sort.by("placeTitle").ascending());
		Page<Place> result = placeRepository.findAll(pageable);
		Function<Place, PlaceDTO> fn = member -> entityToDto(member);
		return new PageResultDTO<>(result, fn);
	}
	
	
	//descending, 눌렀을때 cityName로 구별
	@Override
	public PageResultDTO<PlaceDTO, Place> findByPlaceByCityName(PageRequestDTO requestDTO) {
		Pageable pageable =  requestDTO.getPageable(Sort.by("placeTitle").descending());
				  
		Page<Place> result = placeRepository.findByPlaceByCityName(requestDTO.getCityName(), pageable);
				  
		Function<Place, PlaceDTO> fn =(entity -> entityToDto(entity));
		
		return new PageResultDTO<>(result, fn); 
	}
	
	//descending, 눌렀을때 cityName로 구별
	@Override
	public PageResultDTO<PlaceDTO, Place> findByPlaceByCityName2(PageRequestDTO requestDTO) {
		Pageable pageable =  requestDTO.getPageable(Sort.by("placeTitle").ascending());
		
		Page<Place> result = placeRepository.findByPlaceByCityName(requestDTO.getCityName(), pageable);
		
		Function<Place, PlaceDTO> fn =(entity -> entityToDto(entity));
		
		return new PageResultDTO<>(result, fn); 
	}
	
	

	

	
	
	
	// 각 게시물 번호 가져와서 하나씩 읽기
	@Override
	public PlaceDTO read(String placeTitle) {
		Optional<Place> place = placeRepository.findById(placeTitle);
		return place.map(pla -> entityToDto(pla)).orElse(null);
	}

	

	




}
