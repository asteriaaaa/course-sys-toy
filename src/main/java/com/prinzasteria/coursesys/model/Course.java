package com.prinzasteria.coursesys.model;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Course {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "course_code", nullable = false)
    private String courseCode;

    @Column(nullable = false)
    private String courseName;

    private String schoolName;

    @ManyToMany(cascade = {CascadeType.REMOVE})
    @Setter(AccessLevel.NONE)
    private Set<User> courseStudents = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.REMOVE})
    private List<StudentGrade> courseGrades = new ArrayList<>();

}
