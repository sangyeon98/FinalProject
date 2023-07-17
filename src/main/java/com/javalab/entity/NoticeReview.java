package com.javalab.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "TBL_NOTICE_REVIEW")
//@ToString(exclude = "notice")
public class NoticeReview {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Notice_Review_No")
	private Integer reviewNo;
	
	@Column(name = "Notice_Review_Content")
	private String reviewContent;
	
	@ManyToOne
	@JoinColumn(name = "NOTICE_NO")
	private Notice notice;
	
	@ManyToOne
	@JoinColumn(name = "User_ID")
	private User user;

}
