package com.prinzasteria.coursesys.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"username", "school_name"})})
@NoArgsConstructor
@RequiredArgsConstructor
public class User {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NonNull
    @Column(name="username", nullable = false)
    private String userName;

    private String nickName;

    @NonNull
    @Column(name="password", nullable = false)
    private String passwd;

    @Setter(AccessLevel.PUBLIC)
    @Getter
    @Column(name="privilege", updatable = false, columnDefinition = "char(1)")
    private Character privilege;

    @Setter(AccessLevel.PUBLIC)
    @Column(name = "phone")
    private String phone;

    @CreationTimestamp
    @Setter(AccessLevel.NONE)
    @Column(name = "creation_time", updatable = false)
    private Date createDate;

    @Setter
    @Getter
    @Column(name = "school_name")
    private String schoolName;

    @ManyToMany(mappedBy = "courseStudents", targetEntity = Course.class, cascade = {CascadeType.REMOVE})
    @Setter(AccessLevel.PUBLIC)
    private Set<Course> courseList = new HashSet<>();

    @ManyToMany(mappedBy = "courseGrades", targetEntity = Course.class, cascade = {CascadeType.REMOVE})
    private List<StudentGrade> grades = new ArrayList<>();

}
