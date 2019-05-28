package com.prinzasteria.coursesys.web;

import com.prinzasteria.coursesys.model.User;
import com.prinzasteria.coursesys.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserUtil {

    private final UserInformationService userInformationService;
    private final CourseInformationService courseInformationService;

    @Autowired
    public UserUtil(UserInformationService userInformationService, CourseInformationService courseInformationService){
        this.userInformationService = userInformationService;
        this.courseInformationService = courseInformationService;
    }

    /* Home page */
    @GetMapping("/")
    public String welcome(Model model, HttpServletRequest request){
        User user = userInformationService.getUser((String)request.getSession()
                .getAttribute("userInfo"), (String)request.getSession()
                .getAttribute("userSchoolInfo"));
        model.addAttribute("user", user);
        System.out.println(user.getPrivilege()=='S');
        return "index";
    }

    /* Information Edit page */
    @GetMapping("/edit_info")
    public String editInfo(Model model, HttpServletRequest request){
        User user = userInformationService.getUser((String) request.getSession().
                getAttribute("userInfo"), (String)request.getSession()
                .getAttribute("userSchoolInfo"));
        model.addAttribute("user", user);
        return "edit_info";
    }

    /* Admin management page */
    @GetMapping("/admin_page")
    public String showAdminPage(Model model, HttpServletRequest request){
        String username = (String) request.getSession().
                getAttribute("userInfo");
        String userschool =(String)request.getSession()
                .getAttribute("userSchoolInfo");
        User user = userInformationService.getUser(username, userschool);
        if ( user == null || user.getPrivilege() != 'A')
            return "404";
        List<User> allUsers = userInformationService.getUsers(user.getSchoolName());
        model.addAttribute("userList", allUsers);
        return "admin_page";
    }

    /* See the student user's grades */
    @GetMapping("/my_grades")
    public String showGrades(Model model, HttpServletRequest request){
        String username = (String) request.getSession().
                getAttribute("userInfo");
        String userschool =(String)request.getSession()
                .getAttribute("userSchoolInfo");
        User user = userInformationService.getUser(username, userschool);
        if ( user == null || user.getPrivilege() != 'S')
            return "404";
        Map<String, Double> grades = userInformationService.getGrades(username, userschool);
        model.addAttribute("grades", grades);
        Map<String, String> courses = new HashMap<>();
        for (String courseCode : grades.keySet()){
            courses.put(courseCode, courseInformationService.getCourseNameByCode(courseCode));
        }
        model.addAttribute("coursenames", courses);
        return "my_grades";
    }

    /* Handling admin user information update request*/
    @PostMapping("/admin_update")
    @ResponseBody
    public String adminUpdate (@RequestBody AdminUserUpdate update, HttpServletRequest request){
        String username = (String) request.getSession().
                getAttribute("userInfo");
        String userschool =(String)request.getSession()
                .getAttribute("userSchoolInfo");
        User user = userInformationService.getUser(username, userschool);
        if ( user == null || user.getPrivilege() != 'A')
            return "404";
        int flag = update.getFlag();
        if (flag == 0){
            userInformationService.deleteUser(update.getUsername(), userschool);
        }
        else{
            if (userInformationService.getUser(update.getUsername(), userschool) != null &&
                    userInformationService.getUser(update.getUsername(), userschool).getPrivilege() != update.getPrivilege())
                userInformationService.updatePrivilege(update.getUsername(), update.getPrivilege(), userschool);

            else
                userInformationService.addUser(update.getUsername(), update.getPrivilege(), userschool);
        }
        return "admin_page";
    }

    /*Handling ordinary user information update request*/
    @PostMapping("/update_info")
    @ResponseBody
    public String updateInfo(@RequestBody UserInfoUpdate update, HttpServletRequest request){
        User user = userInformationService.getUser((String) request.getSession().
                getAttribute("userInfo"), (String)request.getSession()
                .getAttribute("userSchoolInfo"));
        String newPhone = update.getPhone();
        String newPasswd = update.getNewPasswd();
        String newUsername = update.getUserName();
        if (! userInformationService.verifyUser(user.getUserName(), update.getOldPasswd(), user.getSchoolName()))
            return "edit_info";
        int pwRet = userInformationService.updatePassword(user.getUserName(), newPasswd,user.getSchoolName());
        userInformationService.updatePhone(user.getUserName(), newPhone, user.getSchoolName());
        userInformationService.updateNickname(user.getUserName(), newUsername, user.getSchoolName());
        return "/";
    }
}
