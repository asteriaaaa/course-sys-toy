package com.prinzasteria.coursesys.dao;

import com.prinzasteria.coursesys.model.Course;
import com.prinzasteria.coursesys.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {
    Set<User> findUsersByPrivilege(char p);
    User findUserById(long id);
    List<User> findAll();
    List<User> findUsersBySchoolName(String schoolName);
    User findUserByUserName(String username);
    User findUserByUserNameAndSchoolName(String username, String schoolname);
}
