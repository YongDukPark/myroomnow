package com.toy.myroomnow.users.service;

import com.toy.myroomnow.users.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class FindUserService {
    private final UserRepository userRepository;
    private DefaultMessageService messageService;

    @Value("${smsApiKey}")
    private String smsApiKey;

    @Value("${smsSecretKey}")
    private String smsSecretKey;

    @PostConstruct
    public void init() {
        this.messageService = NurigoApp.INSTANCE.initialize(smsApiKey, smsSecretKey, "https://api.coolsms.co.kr\"");
    }

    //public SingleMessageSentResponse getAuthCode(String userPhoneNumber, HttpSession session) {
    public String sendAuthCode(String userPhoneNumber) {
        Message message = new Message();
        String authCode = createAuthCode();
        // 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.
        message.setFrom("01046665190");
        message.setTo(userPhoneNumber);
        message.setText("인증번호 ["+ authCode +"] 를 입력하세요.");

        SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));
        System.out.println(response);

        return authCode;
    }

    public String createAuthCode(){
        StringBuilder authcode = new StringBuilder();
        Random random = new Random();
        for (int i = 0 ; i < 6 ; i++) {
            authcode.append(Integer.toString(random.nextInt(10)));
        }
        return authcode.toString();
    }


    public String findUserId(){

        return "test";
    }

    public String findUserPassword(){

        return "test";
    }

}
