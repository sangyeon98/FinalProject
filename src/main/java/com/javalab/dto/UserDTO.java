package com.javalab.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.javalab.entity.Event;
import com.javalab.entity.Notice;
import com.javalab.entity.ScrapCourse;
import com.javalab.entity.ScrapPlace;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserDTO {
	private String userId; // 사용자 ID
	private String userPassword; // 사용자 비밀번호
	private String userName; // 사용자 이름
	private String userPhoneNumber; // 사용자 전화번호
	private String userAddress; // 사용자 주소
	private LocalDateTime userDateOfBirth; // 사용자 생년월일
	private List<ScrapPlace> scrapPlaces; // 스크랩한 장소 목록
	private List<ScrapCourse> scrapCourses; // 스크랩한 코스 목록
	private List<Notice> notices; // 공지사항 목록
	private List<Event> events; // 이벤트 목록

	public UserDTO(String userId, String userPassword, String userName, String userPhoneNumber, String userAddress,
			LocalDateTime userDateOfBirth) {
		this.userId = userId;
		this.userPassword = userPassword;
		this.userName = userName;
		this.userPhoneNumber = userPhoneNumber;
		this.userAddress = userAddress;
		this.userDateOfBirth = userDateOfBirth;
	}
}
