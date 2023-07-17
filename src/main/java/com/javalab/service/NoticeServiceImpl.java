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

import com.javalab.dto.NoticeDTO;
import com.javalab.dto.PageRequestDTO;
import com.javalab.dto.PageResultDTO;
import com.javalab.entity.Notice;
import com.javalab.repository.NoticeRepository;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeRepository noticeRepository;
	public NoticeServiceImpl(NoticeRepository noticeRepository) {
		this.noticeRepository = noticeRepository;
	}
	
    // 카테고리 목록 조회[페이징]
    @Override
    public PageResultDTO<NoticeDTO, Notice> noticeList(PageRequestDTO requestDTO) {

        Pageable pageable = requestDTO.getPageable(Sort.by("noticeNo").descending());
        
        Page<Notice> result = noticeRepository.findAll(pageable);
        
        Function<Notice, NoticeDTO> fn = (entity -> entityToDto(entity)); // java.util.Function
        return new PageResultDTO<>(result, fn );
    } 

	/*
	 * @Override 
	 * public List<NoticeDTO> noticeList() { 
	 * List<Notice> entityList = noticeRepository.findAll();
	 * 
	 * List<NoticeDTO> noticeList = entityList.stream() .map(entity ->
	 * entityToDto(entity)) .collect(Collectors.toList());
	 * 
	 * return noticeList; }
	 */
	
	 @Override
	 public NoticeDTO read(Integer noticeNo) {
	 Optional<Notice> notice = noticeRepository.findById(noticeNo);
	 return notice.map(ntc -> entityToDto(ntc)).orElse(null);
    }
	    
	
	 @Override
	 public void modify(NoticeDTO noticedto) {
	 Optional<Notice> notice = noticeRepository.findById(noticedto.getNoticeNo());
	 if(notice.isPresent()) {
		 Notice existingNotice = notice.get();
		 existingNotice.setNoticeTitle(noticedto.getNoticeTitle());
		 existingNotice.setNoticeContent(noticedto.getNoticeContent());
		 /*existingNotice.setNoticeNumber(noticedto.getNoticeNumber());*/
		 
		 noticeRepository.save(existingNotice);
	 	}
	 }
	 
	 
	 @Override
	    public Notice register(NoticeDTO dto) {
		 Notice entity = dtoToEntity(dto);
	      return noticeRepository.save(entity);
	    }
	 
	 
	  @Override
	    public boolean remove(Integer noticeNo) {
	    	// 1. 삭제할 notice 엔티티 조회
	        Optional<Notice> nct = noticeRepository.findById(noticeNo);
	        // 2. 존재할 경우 삭제 처리
	        if (nct.isPresent()) {
	            noticeRepository.deleteById(noticeNo);
	            return true;
	        } else {
	            return false;
	        }
	    }
	 
		 	

	
}