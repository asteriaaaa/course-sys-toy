package com.prinzasteria.coursesys.dao;

import com.prinzasteria.coursesys.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Course findCourseByCourseCode(String courseCode);

}
