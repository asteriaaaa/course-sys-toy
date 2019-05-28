package com.prinzasteria.coursesys.service;

import com.prinzasteria.coursesys.model.User;

public interface PortalService {

    boolean userExsits(String username);

    boolean passwdMatches(String username, String passwd);

    void regesterUser(User user);
}
