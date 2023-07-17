package com.javalab.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NoticeReviewDTO {
	
	private Integer reviewNo;
	private String reviewContent;
	
	private Integer noticeNo;
	private String userId;
	
	

}