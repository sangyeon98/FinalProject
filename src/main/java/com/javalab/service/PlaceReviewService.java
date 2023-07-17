package com.javalab.service;

import java.util.List;

import com.javalab.dto.PlaceDTO;
import com.javalab.dto.PlaceReviewDTO;
import com.javalab.entity.Place;
import com.javalab.entity.PlaceReview;
import com.javalab.entity.User;


public interface PlaceReviewService {

	PlaceReview createComment(PlaceReviewDTO comment);
	
	List<PlaceReviewDTO> getCommentsByPlaceTitle(String placeTitle);
	

	
	default PlaceReview dtoToEntity(PlaceReviewDTO placeReviewDTO) {
		return PlaceReview.builder()
				.reviewContent(placeReviewDTO.getReviewContent())
				.place(new Place(placeReviewDTO.getPlaceTitle()))
				.user(new User(placeReviewDTO.getUserId()))
				.build();		

	}
	
	default PlaceReviewDTO entityToDTO(PlaceReview placeReview) {
	    PlaceReviewDTO placeReviewDTO = new PlaceReviewDTO();
	    placeReviewDTO.setReviewNo(placeReview.getReviewNo());
	    placeReviewDTO.setReviewContent(placeReview.getReviewContent());

	    // Place 엔티티에서 placeTitle 가져오기
	    String placeTitle = placeReview.getPlace().getPlaceTitle();
	    placeReviewDTO.setPlaceTitle(placeTitle);

	    // User 엔티티에서 userId 가져오기
	    String userId = placeReview.getUser().getUserId();
	    placeReviewDTO.setUserId(userId);

	    return placeReviewDTO;
	}

	

}
