package com.prinzasteria.coursesys.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@RequiredArgsConstructor
public class StudentGrade{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = {CascadeType.REMOVE})
    @Cascade({org.hibernate.annotations.CascadeType.REMOVE})
    @Getter
    private User user;

    @OneToOne(cascade = {CascadeType.REMOVE})
    @Cascade({org.hibernate.annotations.CascadeType.REMOVE})
    @Getter
    private Course course;

    @Column(nullable = false)
    @Getter
    @Setter
    private Double grade;
}
