package com.javalab.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.javalab.dto.EventDTO;
import com.javalab.dto.NoticeDTO;
import com.javalab.dto.PageRequestDTO;
import com.javalab.dto.PageResultDTO;
import com.javalab.entity.Event;
import com.javalab.entity.Notice;
import com.javalab.repository.EventRepository;
import com.javalab.repository.NoticeRepository;

@Service
public class EventServiceImpl implements EventService {

	@Autowired
	private EventRepository eventRepository;

	public EventServiceImpl(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}


	@Override
	public List<EventDTO> eventList() {
		List<Event> entityList = eventRepository.findAll();

		List<EventDTO> eventList = entityList.stream().
				map(entity -> entityToDto(entity))
				.collect(Collectors.toList());

		return eventList;
	}

	@Override
	public EventDTO read(Integer eventNo) {
		Optional<Event> event = eventRepository.findById(eventNo);
		return event.map(eve -> entityToDto(eve)).orElse(null);
	}


}