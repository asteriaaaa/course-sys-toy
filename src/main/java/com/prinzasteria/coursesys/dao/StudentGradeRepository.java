package com.prinzasteria.coursesys.dao;

import com.prinzasteria.coursesys.model.Course;
import com.prinzasteria.coursesys.model.StudentGrade;
import com.prinzasteria.coursesys.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentGradeRepository extends JpaRepository<StudentGrade, Long> {

    StudentGrade findStudentGradeByUserAndCourse(User user, Course course);
    List<StudentGrade> findStudentGradesByUser(User user);
}
