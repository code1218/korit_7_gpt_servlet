package com.korit.servlet_study.service;

import com.korit.servlet_study.dto.ResponseDto;
import com.korit.servlet_study.dto.SignupDto;

public class AuthService {

    public ResponseDto<?> signup(SignupDto signupDto) {

        return ResponseDto.success();
    }
}
