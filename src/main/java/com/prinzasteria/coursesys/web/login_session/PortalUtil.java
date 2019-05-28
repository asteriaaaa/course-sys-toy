package com.prinzasteria.coursesys.web.login_session;

import com.prinzasteria.coursesys.service.UniversityService;
import com.prinzasteria.coursesys.service.UserInformationService;
import com.prinzasteria.coursesys.service.UserLoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class PortalUtil {
    private final UserInformationService userService;
    private final UniversityService universityService;

    @Autowired
    public PortalUtil(UserInformationService userService, UniversityService universityService){
        this.universityService =universityService;
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage(Model model){
        List<String> universities = universityService.getSchools();
        model.addAttribute("schools", universities);
        return "login";
    }

    /* If the login is successful, lead to the homepage, otherwise do nothing */
    @PostMapping("/verify")
    @ResponseBody
    public String verify(@RequestBody UserLoginForm user, HttpServletRequest request){
        if (userService.verifyUser(user.getUsername(), user.getPasswd(), user.getSchoolName())) {
            request.getSession().setAttribute("userInfo", user.getUsername());
            request.getSession().setAttribute("userSchoolInfo", user.getSchoolName());
            return "/";
        }
        else {
            return "/login";
        }
    }

}
