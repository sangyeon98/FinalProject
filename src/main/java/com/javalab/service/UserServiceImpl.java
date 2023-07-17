package com.javalab.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javalab.dto.UserDTO;
import com.javalab.entity.User;
import com.javalab.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	// 회원가입
	@Override
	public void signup(UserDTO userDTO) {
		User user = new User();
		user.setUserId(userDTO.getUserId());
		user.setUserName(userDTO.getUserName());
		user.setUserPassword(userDTO.getUserPassword());
		user.setUserPhoneNumber(userDTO.getUserPhoneNumber());
		user.setUserAddress(userDTO.getUserAddress());

		userRepository.save(user);
	}

	// 로그인
	@Override
	public UserDTO login(UserDTO userDTO) {
		User user = userRepository.findById(userDTO);
		// entity -> dto
		UserDTO dto = UserDTO.builder().userId(user.getUserId()).userName(user.getUserName())
				.userPhoneNumber(user.getUserPhoneNumber()).userAddress(user.getUserAddress()).build();

		return dto;
	}

	@Override
	public UserDTO getUserByUsername(String username) {
		User user = userRepository.findByUserName(username);
		if (user == null) {
			return null;
		}
		UserDTO userDTO = new UserDTO();
		userDTO.setUserName(user.getUserName());
		// 필요한 경우 다른 사용자 속성을 설정합니다.

		return userDTO;
	}

	// 계정 삭제
	@Override
	public void deleteUserById(String userId, HttpSession session) {
		// 세션에서 현재 로그인된 사용자 정보 확인
		UserDTO loggedInUser = (UserDTO) session.getAttribute("loggedInUser");

		// 현재 로그인된 사용자가 없거나, 로그인된 사용자의 식별자와 삭제 대상 계정의 식별자가 일치하지 않으면 처리 중단
		if (loggedInUser == null || !loggedInUser.getUserId().equals(userId)) {
			return;
		}

		// 계정 삭제 로직 구현
		userRepository.deleteByUserId(userId);

		// 세션 무효화
		session.invalidate();
	}

	// 아이디 중복 확인
	@Override
	public boolean checkUserId(String userId) {
		User user = userRepository.findByUserId(userId);
		return user != null;
	}

	@Override
	public void deleteUserById(String userId) {
		userRepository.deleteByUserId(userId);
	}
}
