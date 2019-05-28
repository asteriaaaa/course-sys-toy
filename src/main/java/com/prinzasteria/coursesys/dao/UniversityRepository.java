package com.prinzasteria.coursesys.dao;

import com.prinzasteria.coursesys.model.University;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UniversityRepository extends JpaRepository<University, Long> {
    University findUniversityByName(String name);
    List<University> findAll();
}
