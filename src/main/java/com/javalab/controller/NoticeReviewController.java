package com.javalab.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javalab.dto.NoticeReviewDTO;
import com.javalab.service.NoticeReviewService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/review")
@Slf4j
public class NoticeReviewController {
	
	@Autowired
	private NoticeReviewService noticeReviewService;
	
	
	// 댓글 작성시 DB저장
	@PostMapping("/comments")
	public ResponseEntity<String> createComment(NoticeReviewDTO comment,
										HttpSession session) {
		
		log.info("여기는 뭐 들어오냐 : " + comment.toString());

		noticeReviewService.createComment(comment);
		
		return ResponseEntity.ok("댓글 작성 성공");
	}
	
	// 작성된 DB가져오기
	@GetMapping("/comments")
	public ResponseEntity<List<NoticeReviewDTO>> 
		getCommentsByNoticeNo(@RequestParam("noticeNo")Integer noticeNo){
		List<NoticeReviewDTO> comments = noticeReviewService.getCommentsByNoticeNo(noticeNo);
		
		return ResponseEntity.ok(comments);
	}
	
}
