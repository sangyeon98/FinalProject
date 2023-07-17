package com.javalab.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javalab.dto.EventDTO;
import com.javalab.service.EventService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/event")
@Controller
public class EventController {

	private final EventService eventService;

	public EventController(EventService eventService) {
		this.eventService = eventService;
	}

	// 목록 조회
	@GetMapping("/list")
	public String eventList(Model model) {
		
		List<EventDTO> result = eventService.eventList();
    	model.addAttribute("result",result);
    	return "event/list";
	}

	// 상세보기
	@GetMapping("/read")
	public String getEventDetails(@RequestParam Integer eventNo, Model model) {
		log.info("getEventDetails");
		EventDTO dto = eventService.read(eventNo);
		model.addAttribute("event", dto);
		return "event/read";

	}

}