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
@Table (name = "TBL_PLACE_REVIEW")
@ToString(exclude = "place")
public class PlaceReview {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Place_Review_No")
	private Integer reviewNo;
	
	@Column(name = "Place_Review_Content")
	private String reviewContent;
	
	@ManyToOne
	@JoinColumn(name = "Place_TITLE")
	private Place place;
	
	@ManyToOne
	@JoinColumn(name = "User_ID")
	private User user;

}
