package com.toy.myroomnow.users.controller;

import com.toy.myroomnow.users.dto.UserLoginRequest;
import com.toy.myroomnow.users.dto.UserSignUpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class UserController {

    //로그인 페이지로 화면 이동
    @GetMapping("/login")
    public ModelAndView loginForm(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("loginform");
        return mv;
    }

    //회원가입 페이지로 화면 이동
    @GetMapping("/signup")
    public ModelAndView signUpForm(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("signupform");
        return mv;
    }

    //회원가입 진행
    @PostMapping("/users")
    public String singUp(){
        ModelAndView mv = new ModelAndView();

        return "redirect:/login";
    }

}
