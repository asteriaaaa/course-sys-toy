package com.prinzasteria.coursesys.service;

import lombok.Getter;


public class UserInfoUpdate {
    @Getter
    private String userName;

    @Getter
    private String oldPasswd;

    @Getter
    private String phone;

    @Getter
    private String newPasswd;

}
