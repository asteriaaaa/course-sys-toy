package com.prinzasteria.coursesys.service;

import lombok.Getter;
import lombok.Setter;

public class UserLoginForm {

    @Getter
    @Setter
    private String username;

    @Setter
    @Getter
    private String passwd;

    @Getter
    private String schoolName;
}
