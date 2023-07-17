package com.javalab.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.javalab.dto.NoticeReviewDTO;
import com.javalab.dto.PlaceReviewDTO;
import com.javalab.entity.Notice;
import com.javalab.entity.NoticeReview;
import com.javalab.repository.NoticeReviewRepository;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class NoticeReviewServiceImpl implements NoticeReviewService {
	
	private final NoticeReviewRepository noticeReviewRepository;
	
	public NoticeReviewServiceImpl(NoticeReviewRepository noticeReviewRepository) {
		this.noticeReviewRepository = noticeReviewRepository;
	}
	
	
	@Override
	public NoticeReview createComment(NoticeReviewDTO comment) {
		log.info("여기까지 오는지 확인 1");
		NoticeReview entity = dtoToEntity(comment);
		log.info("여기는 엔티티 값 담겨있는지 확인 :" + entity);
	    return noticeReviewRepository.save(entity);
	}
	
	@Override
	public List<NoticeReviewDTO> getCommentsByNoticeNo (Integer noticeNo){
		List<NoticeReview> noticeReviews = noticeReviewRepository.findByNoticeNo(noticeNo);
		  
		List<NoticeReviewDTO> noticeReviewDTOs = new ArrayList<>();
		
		  for(NoticeReview noticeReview : noticeReviews) { 
			NoticeReviewDTO noticeReviewDTO = entityToDTO(noticeReview);
			noticeReviewDTOs.add(noticeReviewDTO); }
		 
		log.info("여기는 넘어가는 값 확인 : " + noticeReviewDTOs.toString());
		
		return noticeReviewDTOs;
		
}

}