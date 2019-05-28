package com.prinzasteria.coursesys.service;

import com.prinzasteria.coursesys.dao.CourseRepository;
import com.prinzasteria.coursesys.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseInformationServiceImpl implements CourseInformationService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseInformationServiceImpl(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    public void addCourse(Course course){
        courseRepository.save(course);
    }

    public void removeCourse(Course course){
        courseRepository.delete(course);
    }

    public void updateCourse(Course course){
        courseRepository.save(course);
    }

    public String getCourseNameByCode (String code){
        Course course = courseRepository.findCourseByCourseCode(code);
        return course.getCourseName();
    }
}
