package com.javalab.service;

import org.springframework.stereotype.Service;
import com.javalab.dto.ScrapCourseDTO;
import com.javalab.entity.ScrapCourse;
import com.javalab.repository.ScrapCourseRepository;

@Service
public class ScrapCourseServiceImpl implements ScrapCourseService {

	private final ScrapCourseRepository scrapCourseRepository;
	
	public ScrapCourseServiceImpl(ScrapCourseRepository scrapCourseRepository) {
		this.scrapCourseRepository = scrapCourseRepository;
	}
	

	@Override
	public ScrapCourse register(ScrapCourseDTO scrapCourse) {
	    ScrapCourse entity = dtoToEntity(scrapCourse);
	    ScrapCourse savedScrapCourse = scrapCourseRepository.save(entity);
	    // 디버깅 메시지 추가
	    System.out.println("스크랩 데이터가 저장되었습니다. 스크랩 번호: " + savedScrapCourse.getScrapNo());
	    return savedScrapCourse;
	}
}
