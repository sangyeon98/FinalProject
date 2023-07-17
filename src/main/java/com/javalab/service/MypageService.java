package com.javalab.service;

import java.util.List;

import com.javalab.dto.ScrapCourseDTO;
import com.javalab.dto.ScrapPlaceDTO;
import com.javalab.dto.UserDTO;

public interface MypageService {
	List<ScrapPlaceDTO> getScrapListByUser(UserDTO user);

	List<ScrapCourseDTO> getScrapCourseListByUser(UserDTO user);
}
