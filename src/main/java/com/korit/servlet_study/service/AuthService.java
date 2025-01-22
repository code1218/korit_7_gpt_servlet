package com.korit.servlet_study.service;

import com.korit.servlet_study.dao.AuthDao;
import com.korit.servlet_study.dto.ResponseDto;
import com.korit.servlet_study.dto.SignupDto;
import com.korit.servlet_study.entity.User;

public class AuthService {
    private AuthDao authDao;

    private static AuthService instance;

    private AuthService() {
        authDao = AuthDao.getInstance();
    }

    public static AuthService getInstance() {
        if (instance == null) {
            instance = new AuthService();
        }
        return instance;
    }

    public ResponseDto<?> signup(SignupDto signupDto) {
        User insertedUser = authDao.signup(signupDto.toUser());
        if(insertedUser == null) {
            return ResponseDto.fail("사용자를 추가하지 못하였습니다.");
        }
        return ResponseDto.success(insertedUser);
    }
}
