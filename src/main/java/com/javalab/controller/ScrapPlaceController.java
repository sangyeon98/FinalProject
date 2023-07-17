package com.javalab.controller;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javalab.dto.PlaceReviewDTO;
import com.javalab.dto.UserDTO;
import com.javalab.entity.Place;
import com.javalab.entity.ScrapPlace;
import com.javalab.entity.User;
import com.javalab.repository.ScrapPlaceRepository;
import com.javalab.repository.UserRepository;
import com.javalab.service.PlaceReviewService;
import com.javalab.service.ScrapPlaceService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j

/* 여기는 스크랩이랑 답글 컨트롤러 */
public class ScrapPlaceController {
    
    @Autowired
    private ScrapPlaceRepository scrapPlaceRepository;    
    @Autowired
    private ScrapPlaceService scrapPlaceService;  
    @Autowired
    private PlaceReviewService placeReviewService;  
    @Autowired
    private UserRepository userRepository;
    
    @PostMapping("/scrap")
    public ResponseEntity<String> scrapPlace(@RequestParam("placeTitle") Place placeTitle, HttpSession httpSession
                                            ) {
        // tbl_scrap_place에 데이터 저장하는 로직 구현
        // placeTitle과 userId를 활용하여 저장
    	
    	UserDTO userDTO = (UserDTO) httpSession.getAttribute("loggedInUser");
       
    	// dtoToEntity
    	User user = User.builder()
    			.userId(userDTO.getUserId())
    			.userPassword(userDTO.getUserPassword())
    			.userName(userDTO.getUserName())
    			.userPhoneNumber(userDTO.getUserPhoneNumber())
    			.userAddress(userDTO.getUserAddress())
    			.build();
    	
    	
        ScrapPlace scrapPlace = new ScrapPlace();
        scrapPlace.setPlace(placeTitle);
        scrapPlace.setUser(user);      
        

        scrapPlaceRepository.save(scrapPlace);

        log.info("스크랩플레이스 확인: " + scrapPlace.getPlace().getPlaceTitle());
        log.info("스크랩플레이스 확인: " + scrapPlace.getUser().getUserId());

        return ResponseEntity.ok("찜하기 성공");
    }
    
    
    //여기는 댓글 작성시 DB저장하는곳
    @PostMapping("/comments")
    public ResponseEntity<String> createComment(PlaceReviewDTO placeReviewDTO,
                                                HttpSession session) {
       // String userId = (String) session.getAttribute("userId"); // 세션에서 userId 가져오기

//        PlaceReviewDTO comment = new PlaceReviewDTO();
//        comment.setReviewContent(content);
       // placeReviewDTO.setUserId(userId);    
        log.info("값 확인용: " + placeReviewDTO.toString());
        
//        comment.setPlace(placeTitle);

//        UserDTO user = new UserDTO();
//        user.setUserId(userId);
//        comment.setUser(user);

        placeReviewService.createComment(placeReviewDTO);
        
        return ResponseEntity.ok("댓글 작성 성공");
    }
    
    //여기는 작성된 DB가져오는곳
    @GetMapping("/comments")
    public ResponseEntity<List<PlaceReviewDTO>> getCommentsByPlaceTitle(@RequestParam("placeTitle") String placeTitle) {
        List<PlaceReviewDTO> comments = placeReviewService.getCommentsByPlaceTitle(placeTitle);
        log.info("여긴 플레이스리뷰dto담긴곳 : "+comments.toString());
        return ResponseEntity.ok(comments);
    }
    
    
    

}