package com.toy.myroomnow.users.controller;

import com.toy.myroomnow.users.dto.UserLoginRequest;
import com.toy.myroomnow.users.dto.UserSignUpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")  // REST API 공통 경로
public class UserApiController {

    // 회원가입 처리 (POST /api/users)
    @PostMapping("/users")
    public ResponseEntity<?> signUp(@RequestBody UserSignUpRequest request) {
        // TODO: 실제 회원가입 로직 처리
        // userService.register(request);

        // 가입 완료 메시지 혹은 가입한 유저 정보 반환
        return ResponseEntity.ok(Map.of("message", "회원가입 성공"));
    }

    // 로그인 처리 (POST /api/login)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequest request) {
        // TODO: 실제 로그인 인증 로직 처리
        // 인증 성공 시 토큰 발급 등 처리

        return ResponseEntity.ok(Map.of("message", "로그인 성공"));
    }
}
