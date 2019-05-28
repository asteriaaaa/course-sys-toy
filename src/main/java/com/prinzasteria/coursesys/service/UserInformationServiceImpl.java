package com.prinzasteria.coursesys.service;

import com.prinzasteria.coursesys.dao.CourseRepository;
import com.prinzasteria.coursesys.dao.StudentGradeRepository;
import com.prinzasteria.coursesys.dao.UserRepository;
import com.prinzasteria.coursesys.model.Course;
import com.prinzasteria.coursesys.model.StudentGrade;
import com.prinzasteria.coursesys.model.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* Most service utilities are completed here */
@Service
public class UserInformationServiceImpl implements UserInformationService {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final StudentGradeRepository studentGradeRepository;

    @Autowired
    public UserInformationServiceImpl(UserRepository userRepository,
                                      CourseRepository courseRepository, StudentGradeRepository studentGradeRepository){
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
        this.studentGradeRepository = studentGradeRepository;
    }

    public void updateNickname(String username, String nickName, String schoolName){
        User user = userRepository.findUserByUserNameAndSchoolName(username, schoolName);
        user.setNickName(nickName);
        userRepository.save(user);
    }

    public boolean verifyUser(String username, String passwd, String schoolName){
        if (userRepository.findUserByUserNameAndSchoolName(username, schoolName) == null)
            return false;
        if (userRepository.findUserByUserNameAndSchoolName(username,schoolName).getPasswd().equals(passwd))
            return true;
        return false;
    }

    /* The user's school name should be the same as the admin */
    public int addUser(String username, Character pri, String schooName){
        User usr = userRepository.findUserByUserNameAndSchoolName(username, schooName);
        if ( usr != null && usr.getSchoolName() != null && usr.getSchoolName().equals(schooName))
            return 1;
        if (username == null || pri == null)
            return -1;
        User user = new User();
        user.setUserName(username);
        user.setPrivilege(pri);
        user.setSchoolName(schooName);
        String passWd = DigestUtils.sha1Hex(username);
        user.setPasswd("123456");
        System.out.println(passWd);
        userRepository.save(user);
        return 0;
    }


    public int deleteUser(String username, String schoolName)
    {
        User user = userRepository.findUserByUserNameAndSchoolName(username, schoolName);
        if (user == null)
            return -1;
        List<StudentGrade> grades = studentGradeRepository.findStudentGradesByUser(user);
        studentGradeRepository.deleteInBatch(grades);
        userRepository.delete(user);
        return 0;
    }

    /* Due to unknown reasons directly user the save() method can't get the information updated
     * so delete is used */
    @Override
    public int updatePrivilege(String username, Character P, String schoolName) {
        User user = userRepository.findUserByUserNameAndSchoolName(username,schoolName);
        userRepository.delete(user);
        if (user == null)
            return -1;
        user.setPrivilege(P);
        userRepository.save(user);
        return 0;
    }

    @Override
    public int updatePassword(String username, String newPasswd, String schoolName) {
        if (newPasswd == null || newPasswd.length() <= 1)
            return 1;
        User user = userRepository.findUserByUserNameAndSchoolName(username,schoolName);
        user.setPasswd(newPasswd);
        userRepository.save(user);
        return 0;
    }

    private boolean legalPhone(String phone){
        for (int i = 0; i < phone.length(); i++){
            if (phone.charAt(i) > 57 || phone.charAt(i) < 48)
                return false;
        }
        return true;
    }

    @Override
    public int updatePhone(String username, String newPhone, String schoolName) {
        if (newPhone == null || (newPhone.length() <= 6 || newPhone.length() >= 14) || !legalPhone(newPhone))
            return 1;
        User user = userRepository.findUserByUserNameAndSchoolName(username, schoolName);
        user.setPhone(newPhone);
        userRepository.save(user);
        return 0;
    }


    @Override
    public double getGrade (String username, String courseCode, String schoolName){
        User user = userRepository.findUserByUserNameAndSchoolName(username, schoolName);
        Course course = courseRepository.findCourseByCourseCode(courseCode);
        return studentGradeRepository.
                findStudentGradeByUserAndCourse(user, course).getGrade();
    }

    @Override
    public List<User> getUsers(String name) {
        return userRepository.findUsersBySchoolName(name);
    }

    @Override
    public Map<String, Double> getGrades(String username, String schoolName){
        User user = userRepository.findUserByUserNameAndSchoolName(username, schoolName);
        List<StudentGrade> studentGrades = studentGradeRepository.findStudentGradesByUser(user);
        Map<String, Double> map = new HashMap<>();
        for (StudentGrade grade : studentGrades){
            map.put(grade.getCourse().getCourseCode(), grade.getGrade());
        }
        return map;
    }

    @Override
    public User getUser(String username, String schoolName){
        return userRepository.findUserByUserNameAndSchoolName(username, schoolName);
    }
}
