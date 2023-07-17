package com.javalab.service;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.javalab.dto.CourseReviewDTO;
import com.javalab.entity.CourseReview;
import com.javalab.repository.CourseReviewRepository;

@Service
public class CourseReviewServiceImpl implements CourseReviewService{
	
	private final CourseReviewRepository courseReviewRepository;
	
	public CourseReviewServiceImpl(CourseReviewRepository courseReviewRepository) {
		this.courseReviewRepository = courseReviewRepository;
	}

	@Override
	public CourseReview createComment(CourseReviewDTO courseReview) {
	    CourseReview entity = dtoToEntity(courseReview);
	    return courseReviewRepository.save(entity);
	}
	
    @Override
    public List<CourseReviewDTO> getCommentsByCourseNo(Integer courseNo) {
        List<CourseReview> courseReviews = courseReviewRepository.findByCourseNo(courseNo);

        List<CourseReviewDTO> courseReviewDTOs = new ArrayList<>();
        for (CourseReview courseReview : courseReviews) {
            CourseReviewDTO courseReviewDTO = entityToDTO(courseReview);
            courseReviewDTOs.add(courseReviewDTO);
        }

        return courseReviewDTOs;
    }


}


