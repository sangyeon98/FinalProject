package com.javalab.controller;

import java.util.List;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javalab.dto.UserDTO;
import com.javalab.dto.ScrapCourseDTO;
import com.javalab.dto.CourseReviewDTO;
import com.javalab.service.CourseReviewService;
import com.javalab.service.ScrapCourseService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping
@Slf4j
public class ScrapCourseController {
    
    @Autowired
    private ScrapCourseService scrapCourseService;
    
    @Autowired
    private CourseReviewService courseReviewService;
    
    // 스크랩
    @PostMapping("/course/scrap/{courseNo}")
    public ResponseEntity<String> scrapCourse(@PathVariable Integer courseNo,
                                              HttpSession httpSession) {
        UserDTO userDTO = (UserDTO) httpSession.getAttribute("loggedInUser");
        if (userDTO == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
        }

        ScrapCourseDTO scrapCourseDTO = new ScrapCourseDTO();
        scrapCourseDTO.setCourseNo(courseNo);
        scrapCourseDTO.setUser(userDTO);

        log.info("courseNo: " + courseNo);
        scrapCourseService.register(scrapCourseDTO);
        return ResponseEntity.ok("스크랩 성공");
    }
    
    // 댓글
    @PostMapping("/course/comment")
    public ResponseEntity<String> createComment(CourseReviewDTO courseReview, HttpSession session) {
    		// 댓글 생성
            courseReviewService.createComment(courseReview);
            return ResponseEntity.ok("댓글 작성 성공");
    }

    @GetMapping("/course/comments")
    public ResponseEntity<List<CourseReviewDTO>> getCommentsByCourseNo(@RequestParam("courseNo") Integer courseNo) {
        List<CourseReviewDTO> comments = courseReviewService.getCommentsByCourseNo(courseNo);
        return ResponseEntity.ok(comments);
    }

 
}
