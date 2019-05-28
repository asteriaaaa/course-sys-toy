package com.prinzasteria.coursesys.service;

import com.prinzasteria.coursesys.model.Course;


public interface CourseInformationService {

    void addCourse(Course course);

    void removeCourse(Course course);

    String getCourseNameByCode(String code);

}
