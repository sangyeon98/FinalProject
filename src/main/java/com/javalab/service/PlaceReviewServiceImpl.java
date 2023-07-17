package com.javalab.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.javalab.dto.PlaceReviewDTO;
import com.javalab.entity.PlaceReview;
import com.javalab.repository.PlaceReviewRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PlaceReviewServiceImpl implements PlaceReviewService{
	
	private final PlaceReviewRepository placeReviewRepository;
	
	public PlaceReviewServiceImpl(PlaceReviewRepository placeReviewRepository) {
		this.placeReviewRepository = placeReviewRepository;
	}

	@Override
	public PlaceReview createComment(PlaceReviewDTO comment) {
		PlaceReview entity = dtoToEntity(comment);
		return placeReviewRepository.save(entity);
		
	}

	@Override
	public List<PlaceReviewDTO> getCommentsByPlaceTitle(String placeTitle) {
	    List<PlaceReview> placeReviews = placeReviewRepository.findByPlaceTitle(placeTitle);
	    
	    log.info("여기 플레이스 리뷰 담겼나 확인 : "+ placeReviews.size());
	    
		/* placeReviews.stream().forEach(p ->log.info(p.toString())); */
	    
	    List<PlaceReviewDTO> placeReviewDTOs = new ArrayList<>();

	    for (PlaceReview placeReview : placeReviews) {
	        PlaceReviewDTO placeReviewDTO = entityToDTO(placeReview);
	        placeReviewDTOs.add(placeReviewDTO);
	    }
	    
	    log.info("여기는 넘어가는 값 확인 : " + placeReviewDTOs.toString());

	    return placeReviewDTOs;
	}

}
