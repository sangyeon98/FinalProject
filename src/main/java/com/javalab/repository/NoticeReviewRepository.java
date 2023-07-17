package com.javalab.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.javalab.entity.NoticeReview;

public interface NoticeReviewRepository extends JpaRepository<NoticeReview, Integer> {
	@Query("SELECT nr FROM NoticeReview nr WHERE nr.notice.noticeNo = :noticeNo")
	List<NoticeReview> findByNoticeNo(@Param("noticeNo")Integer noticeNo);
	
}