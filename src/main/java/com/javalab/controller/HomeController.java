package com.javalab.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.javalab.dto.EventDTO;
import com.javalab.service.EventService;

@Controller
public class HomeController {
	
	@Autowired
	private EventService eventService;
    
    // 어플리케이션 처음 구동시 호출되는 메소드("/")
    @GetMapping("/")
    public String Home(Model model) {
    	
    	// 이벤트 리스트 조회
    	List<EventDTO> eventList = eventService.eventList();
    	model.addAttribute("eventList",eventList);
    	
        return "main/main";
    }
    
    @GetMapping("/search")
    public String Search(Model model) {
    	
    	// 이벤트 리스트 조회
    	List<EventDTO> eventList = eventService.eventList();
    	model.addAttribute("eventList",eventList);
    	
        return "main/search";
    }

}
