package com.javalab.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.javalab.dto.CityDTO;
import com.javalab.entity.City;
import com.javalab.repository.CityRepository;

@Service
public class CityServiceImpl implements CityService {
	
	private final CityRepository cityRepository;
	public CityServiceImpl(CityRepository cityRepository) {
		this.cityRepository= cityRepository;
	}
	
	
	
	@Override
	public List<CityDTO> getList() {
		List<City> entityList = cityRepository.findAll();
		
		List<CityDTO> cityList = entityList.stream()
				.map(entity -> CityDTO.builder()
						.cityName(entity.getCityName())
						.build())
				.collect(Collectors.toList());
		return cityList;
		
	}

}
