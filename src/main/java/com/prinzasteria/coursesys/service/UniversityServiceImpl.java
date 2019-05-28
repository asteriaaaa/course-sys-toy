package com.prinzasteria.coursesys.service;

import com.prinzasteria.coursesys.dao.UniversityRepository;
import com.prinzasteria.coursesys.model.University;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UniversityServiceImpl implements UniversityService{
    private final UniversityRepository universityRepository;

    @Autowired
    public UniversityServiceImpl(UniversityRepository universityRepository){
        this.universityRepository = universityRepository;
    }

    public List<String> getSchools(){
        List<String> li = new ArrayList<>();
        List<University> u = universityRepository.findAll();
        for (University uni : u){
            li.add(uni.getName());
        }
        return li;
    }

}
