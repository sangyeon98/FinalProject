package com.javalab.dto;

import java.sql.Date;

import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.javalab.entity.Notice;
import com.javalab.entity.User;

import groovy.transform.ToString;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Getter
@Setter
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(excludes = "user")
public class NoticeDTO {

    private Integer noticeNo;

    @NotBlank(message =  "제목을 입력하세요")
    private String noticeTitle;

    @NotBlank(message = "내용을 입력하세요")
    private String noticeContent;

    @NotNull(message = "날짜를 입력하세요")
    private LocalDateTime noticeDate = LocalDateTime.now();

    private User user;

}