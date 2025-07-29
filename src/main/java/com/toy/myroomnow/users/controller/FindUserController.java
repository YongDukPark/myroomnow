package com.toy.myroomnow.users.controller;

import com.toy.myroomnow.users.service.FindUserService;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class FindUserController {

    private FindUserService findUserService;

    @PostMapping("/send-auth-code")
    public ResponseEntity<?> sendAuthCode(@RequestParam("userPhoneNumber") String userPhoneNumber, HttpSession session) {
        String code = findUserService.sendAuthCode(userPhoneNumber);
        session.setAttribute("authCode", code);
        session.setAttribute("userPhoneNumber", userPhoneNumber);

        Map<String, String> body = new HashMap<>();
        body.put("message", "인증번호가 전송되었습니다.");

        return ResponseEntity.ok(body);
    }

    @PostMapping("/verify-auth-code")
    public ResponseEntity<?> verifyCode(@RequestParam("code") String code, HttpSession session) {
        String sessionCode = (String)session.getAttribute("authCode");
        String phoneNumber = (String)session.getAttribute("userPhoneNumber");

        //아이디
        if (sessionCode != null && sessionCode.equals(code)) {
            String userId =

            Map<String, String> body = new HashMap<>();
            body.put("userId", userId);
            return ResponseEntity.ok(body); // 200 OK
        } else {
            model.addAttribute("error", "인증번호가 일치하지 않습니다.");
            return "verify-code";
        }
    }
}
