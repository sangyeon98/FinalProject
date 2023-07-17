package com.javalab.service;

import javax.servlet.http.HttpSession;

import com.javalab.dto.UserDTO;

public interface UserService {
    void signup(UserDTO userDTO); // 회원가입

    public UserDTO login(UserDTO userDTO); // 로그인

    UserDTO getUserByUsername(String username);

    void deleteUserById(String userId); // 계정 삭제

    boolean checkUserId(String userId); // 아이디 중복 확인

    void deleteUserById(String userId, HttpSession session);
    
}
