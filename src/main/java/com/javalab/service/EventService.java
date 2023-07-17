package com.javalab.service;

import java.util.List;

import com.javalab.dto.EventDTO;

import java.util.List;
import com.javalab.entity.Event;


public interface EventService {
	
	// 공지사항 목록 조회
	List<EventDTO> eventList();
	EventDTO read(Integer eventNo);
	/*
	 * void modify(NoticeDTO noticedto); Notice register(NoticeDTO notice); boolean
	 * remove(Integer noticeNo);
	 */

    // DTO -> Entity 전환
    default Event dtoToEntity(EventDTO dto) {
        Event entity = new Event();
        entity.setEventNo(dto.getEventNo());
        entity.setEventTitle(dto.getEventTitle());
        entity.setEventContent(dto.getEventContent());
        return entity;
    }
	
	
    // Entity -> DTO 전환
    default EventDTO entityToDto(Event entity) {
    	EventDTO dto = new EventDTO();
        dto.setEventNo(entity.getEventNo());
        dto.setEventTitle(entity.getEventTitle());
        dto.setEventContent(entity.getEventContent());
        return dto;
    }
}
