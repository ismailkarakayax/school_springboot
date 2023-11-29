package com.enoca.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentName;
    private int grade;

    @ManyToOne
    @JoinColumn(name="teacher_id")
    @JsonManagedReference
    private Teacher teacher;

}
