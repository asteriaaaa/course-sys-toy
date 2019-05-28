package com.prinzasteria.coursesys.service;

import com.prinzasteria.coursesys.model.Course;
import com.prinzasteria.coursesys.model.User;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface UserInformationService {

    int deleteUser(String username, String schoolName);

    int addUser (String username, Character pri, String schoolName);

    int updatePrivilege(String username, Character P, String schoolName);

    int updatePassword(String username, String newPasswd , String schoolName);

    int updatePhone(String username, String newPhone, String schoolName);


    boolean verifyUser(String username, String passwd, String schoolName);

    void updateNickname(String username, String nickName , String schoolName);

    double getGrade(String username, String courseCode, String schoolName);

    List<User> getUsers(String name);

    Map<String, Double> getGrades(String username, String schoolName);

    User getUser(String username, String schoolName);



}
