package com.javalab.dto;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScrapPlaceDTO {
	
	private Integer scrapNumber;
	private String userId;
	private String placeTitle;
	
	private UserDTO user;
	private PlaceDTO place;
	


}
