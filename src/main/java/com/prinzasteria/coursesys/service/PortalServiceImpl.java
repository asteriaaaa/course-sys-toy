package com.prinzasteria.coursesys.service;

import com.prinzasteria.coursesys.dao.UserRepository;
import com.prinzasteria.coursesys.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PortalServiceImpl {

    private final UserRepository userRepository;

    @Autowired
    public PortalServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public boolean userExists(String username){
        return userRepository.findUserByUserName(username) == null;
    }

    public boolean passwdMatches(String username, String passwd){
        User user = userRepository.findUserByUserName(username);
        return passwd.equals(user.getPasswd());
    }

    public void regesterUser(User user){

    }
}
