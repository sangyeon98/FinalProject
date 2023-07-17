package com.javalab.dto;

import com.javalab.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QnADTO {
    private String chatQ;
    private String chatA;

    // Getters and setters
}