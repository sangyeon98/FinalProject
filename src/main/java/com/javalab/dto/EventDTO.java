package com.javalab.dto;

import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.javalab.entity.User;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Getter
@Setter
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO {

	private Integer eventNo;

    @NotBlank(message =  "제목을 입력하세요")
    private String eventTitle;

    @NotBlank(message = "내용을 입력하세요")
    private String eventContent;


}