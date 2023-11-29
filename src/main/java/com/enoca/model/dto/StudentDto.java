package com.enoca.model.dto;

import lombok.Data;

@Data
public class StudentDto {

    private Long id;
    private String studentName;
    private int grade;
    private Long teacherId;
}
