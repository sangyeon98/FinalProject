package com.javalab.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javalab.dto.UserDTO;
import com.javalab.dto.ScrapCourseDTO;
import com.javalab.dto.ScrapPlaceDTO;
import com.javalab.service.MypageService;
import com.javalab.service.UserService;

@Controller
@RequestMapping("/mypage")
public class MypageController {
	private final MypageService mypageService;

	@Autowired
	public MypageController(MypageService mypageService) {
		this.mypageService = mypageService;
	}

	// 마이페이지 조회
	@GetMapping("/mypage")
	public String showMypage(HttpSession session, Model model) {
		// 세션에서 loggedInUser 정보 가져오기
		UserDTO loggedInUser = (UserDTO) session.getAttribute("loggedInUser");

		// 로그인되지 않은 경우 로그인 페이지로 이동
		if (loggedInUser == null) {
			return "redirect:/user/login";
		}

		  // 사용자의 스크랩 목록을 가져와 모델에 추가
	    List<ScrapPlaceDTO> scrapList = mypageService.getScrapListByUser(loggedInUser);
	    model.addAttribute("scrapList", scrapList);

	    // 스크랩한 코스를 가져옵니다.
	    List<ScrapCourseDTO> courseList = mypageService.getScrapCourseListByUser(loggedInUser);
	    model.addAttribute("courseList", courseList);
		
		model.addAttribute("loggedInUser", loggedInUser);
		// 로그인된 사용자의 마이페이지로 이동
		return "/mypage/mypage";
	}
	
	
}
