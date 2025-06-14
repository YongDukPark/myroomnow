package com.toy.myroomnow.users.controller;

import com.toy.myroomnow.users.domain.User;
import com.toy.myroomnow.users.service.LoginServiceOld;
import com.toy.myroomnow.users.service.SignupService;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final SignupService signupService;
    private final LoginServiceOld loginService;
//    private final UserMapper userMapper;
//    private final PasswordEncoder passwordEncoder;

    //로그인 페이지로 화면 이동
    @GetMapping("/login")
    public String loginForm(@RequestParam(value = "error", required = false) String error, Model model){
        System.out.println("?????????");
        if (error != null) {
            model.addAttribute("loginError", "등록된 회원이 아니거나 비밀번호가 틀렸습니다.");
        }

        return "loginform";
    }

    //회원가입 페이지로 화면 이동
    @GetMapping("/users/signup")
    public String signUpForm(){
        return "user/signupform";
    }

    //회원가입 진행
    @PostMapping("/users")
    public String singUp(User user){

        signupService.signup(user);

        return "redirect:/login";
    }

    //중복확인
    @PostMapping("/users/check-id")
    @ResponseBody
    public Map<String, Boolean> checkUserId(@RequestParam("userid") String userid){
        boolean exists = signupService.existsByUserid(userid);
        //boolean exists = userRepository.existsByUserid(userid);

        return Map.of("available", !exists);
    }
}
