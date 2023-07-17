package com.javalab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.javalab.dto.UserDTO;
import com.javalab.entity.User;
import com.javalab.repository.UserRepository;
import com.javalab.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/user")
public class UserController {
	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping("/login")
	public String showLoginPage() {
		return "login/login";
	}

	/*
	 * @GetMapping("/login") public String showLoginForm() { return "login"; }
	 */

	@PostMapping("/login")
	@ResponseBody
	public ResponseEntity<String> login(@RequestBody UserDTO dto, HttpSession session) {
		log.info("login 메소드입니다................................................");
		// String userId = request.get("userId");
		// String userPassword = request.get("userPassword");

		log.info("로그인 요청 - dto.getUserId}", dto.getUserId());

		// 데이터베이스에서 계정 확인
		UserDTO userDTO = userService.login(dto);

		if (userDTO != null) {
			// 로그인 성공 시 사용자 정보를 세션에 저장
			// UserDTO userDTO = userService.getUserByUsername(userId);
			session.setAttribute("loggedInUser", userDTO);

			UserDTO loginUser = (UserDTO) session.getAttribute("loggedInUser");
			log.info("세션 loginUser : " + loginUser.toString());

			return ResponseEntity.ok("login ok");
		} else {
			String errorMessage = "로그인에 실패했습니다. 아이디와 비밀번호를 확인하세요";
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMessage);
		}
	}

	// 회원가입
	@GetMapping("/signup")
	public String showSignupForm() {
		return "login/signup";
	}

	@PostMapping("/signup")
	public String signup(@Valid @ModelAttribute("userDTO") UserDTO userDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "signup";
		}

		log.info("userDTO : " + userDTO);

		userService.signup(userDTO);

		return "redirect:/user/login";
	}

	// 아이디 중복 확인
	@GetMapping("/checkUserId/{userId}")
	public ResponseEntity<Boolean> checkUserId(@PathVariable String userId) {
		return ResponseEntity.ok(userService.checkUserId(userId));
	}

	// 계정 탈퇴
	@PostMapping("/delete/{userId}")
	public String deleteUserById(@PathVariable("userId") String userId, HttpSession session) {
	    userService.deleteUserById(userId, session);
	    session.invalidate(); // 세션 무효화

	    return "redirect:/user/login"; // 메인 화면으로 리다이렉트
	}

	// 로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/user/login";
	}

}
