package com.javalab.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javalab.dto.CityDTO;
import com.javalab.service.CityService;



@Controller
@RequestMapping("/koMap")
public class KoMapController {
	
	
	// 지도 화면 호출
	@GetMapping("/koMap")
    public String showKoMapPage(Model model) {
        return "/koMap/koMap";
    }


}
