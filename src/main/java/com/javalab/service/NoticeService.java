package com.javalab.service;

import java.util.List;

import com.javalab.dto.NoticeDTO;
import com.javalab.dto.PageRequestDTO;
import com.javalab.dto.PageResultDTO;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import com.javalab.dto.NoticeDTO;
import com.javalab.entity.Notice;


public interface NoticeService {
	
	// 공지사항 목록 조회
	PageResultDTO<NoticeDTO, Notice> noticeList(PageRequestDTO requestDTO);
	NoticeDTO read(Integer noticeNo);
	void modify(NoticeDTO noticedto);
	Notice register(NoticeDTO notice);
	boolean remove(Integer noticeNo);  

    // DTO -> Entity 전환
    default Notice dtoToEntity(NoticeDTO dto) {
        Notice entity = new Notice();
        entity.setNoticeNo(dto.getNoticeNo());
        entity.setNoticeTitle(dto.getNoticeTitle());
        entity.setNoticeContent(dto.getNoticeContent());
        LocalDateTime localDateTime = dto.getNoticeDate();
        Date sqlDate = Date.valueOf(localDateTime.toLocalDate());
        entity.setNoticeDate(sqlDate);
        entity.setUser(dto.getUser());
        return entity;
    }
	
	
    // Entity -> DTO 전환
    default NoticeDTO entityToDto(Notice entity) {
        NoticeDTO dto = new NoticeDTO();
        dto.setNoticeNo(entity.getNoticeNo());
        dto.setNoticeTitle(entity.getNoticeTitle());
        dto.setNoticeContent(entity.getNoticeContent());
        dto.setNoticeDate(entity.getNoticeDate().toLocalDate().atStartOfDay());
        dto.setUser(entity.getUser());
        return dto;
    }
}
