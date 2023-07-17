package com.javalab.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javalab.dto.CityDTO;
import com.javalab.dto.PageRequestDTO;
import com.javalab.dto.PageResultDTO;
import com.javalab.dto.PlaceDTO;

import com.javalab.entity.Place;
import com.javalab.service.CityService;
import com.javalab.service.PlaceService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/place")
@Slf4j
public class PlaceController {

	private final PlaceService placeService;
	private final CityService cityService;
	
	

	public PlaceController(PlaceService placeService, CityService cityService) {
		this.placeService = placeService;
		this.cityService = cityService;
		
	}

//	// List호출용 이건 없어도 되는거
//	@GetMapping("/list")
//	public void getList(PageRequestDTO pageRequestDTO, Model model) {
//		PageResultDTO<PlaceDTO, Place> result = placeService.getList(pageRequestDTO);
//		model.addAttribute("result", result);
//		log.info("이거는 리설트값 확인 :  " + result);
//	}

	// 게시물 하나만 확인용 이건 완성본
//	@GetMapping("/read")
//	public void getPlaceByTitle(@RequestParam String placeTitle, Model model) {
//		PlaceDTO dto = placeService.read(placeTitle);
//		model.addAttribute("place", dto);
//	}
	
	
	
	@GetMapping("/read")
	public void getPlaceByTitle(@RequestParam String placeTitle, Model model) {
	    PlaceDTO dto = placeService.read(placeTitle);
	    
	    List<CityDTO> cityList = cityService.getList();
	    dto.setDtoCityList(cityList);
	    
		//dto.setDtoCityList(cityList);
		
		model.addAttribute("placeList", dto);
		model.addAttribute("cityList", cityList);
		
		
		
	    log.info("read 눌렀을때 담겨있는값부터 보자 : " +dto);
	    log.info("이건 시티리스트 디버깅: " + cityList);

	    //log.info("이건 이미지 확인용 디버깅 문자열 :" +images);
	}
	
	
	
	
	@GetMapping("/list2")
	public String getList(PageRequestDTO pageRequestDTO, Model model) {
		
		PageResultDTO<PlaceDTO, Place> result = placeService.getList(pageRequestDTO);
		//PageResultDTO<PlaceDTO, Place> result2 = placeService.getList2(pageRequestDTO);
		List<CityDTO> cityList = cityService.getList();
		log.info("cityList확인" +cityList.size());
		
		result.setDtoCityList(cityList);
		//result2.setDtoCityList(cityList);
		
		
		model.addAttribute("result", result);
		//model.addAttribute("result2", result2);
		
		
		log.info("뭐가담긴지 봐볼까 : " + result);
		//log.info("뭐가담긴지 봐볼까 : " + result2);
		
		return "place/list";
		
		
	}
	

	
	
	//이거 시티네임으로만 가져오는거
	@GetMapping("/list3")
	public String findByPlaceByCityName(PageRequestDTO pageRequestDTO, Model model) {
	   
	    
		log.info("cityName : " + pageRequestDTO.getCityName());
		log.info("cityName : " + pageRequestDTO.toString());
		
	    PageResultDTO<PlaceDTO, Place> result = placeService.findByPlaceByCityName(pageRequestDTO);
	    //PageResultDTO<PlaceDTO, Place> result2 = placeService.findByPlaceByCityName2(pageRequestDTO);
	    
	    List<CityDTO> cityList = cityService.getList();
	    
	    
		log.info("cityList확인" +cityList.size());
		
		result.setDtoCityList(cityList);
		//result2.setDtoCityList(cityList);
		
	    model.addAttribute("result", result);
	    //model.addAttribute("result2", result2);
	    
	    log.info("이건 클릭했을때 나오는곳" + result);
	    
	    return "place/list2";
	}
	
	
}
