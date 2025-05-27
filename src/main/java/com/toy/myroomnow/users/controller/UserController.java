package com.toy.myroomnow.users.controller;

import com.toy.myroomnow.users.dto.UserSignupDto;
import com.toy.myroomnow.users.mapper.UserMapper;
import com.toy.myroomnow.users.domain.User;
import com.toy.myroomnow.users.service.SignupService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final SignupService signupService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    //로그인 페이지로 화면 이동
    @GetMapping("/login")
    public ModelAndView loginForm(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("loginform");
        return mv;
    }

    //회원가입 페이지로 화면 이동
    @GetMapping("/users/signup")
    public String signUpForm(){
        return "user/signupform";
    }

    //회원가입 진행
    @PostMapping("/users")
    public String singUp(UserSignupDto userSignupDto){
        User user = userMapper.toEntity(userSignupDto, passwordEncoder);



        signupService.signup(user);


        return "redirect:/login";
    }



}
