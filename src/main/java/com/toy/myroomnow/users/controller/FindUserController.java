package com.toy.myroomnow.users.controller;

import jakarta.servlet.http.HttpSession;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FindUserController {

    final DefaultMessageService messageService;

    @Value("${smsApiKey}")
    private String smsApiKey;

    @Value("${smsSecretKey}")
    private String smsSecretKey;

    public FindUserController() {
        this.messageService = NurigoApp.INSTANCE.initialize(smsApiKey, smsSecretKey, "https://api.coolsms.co.kr\"");
    }

    @PostMapping("/send-one")
    public SingleMessageSentResponse sendOne(@RequestParam("userPhoneNumber") String userPhoneNumber, HttpSession session) {

        Message message = new Message();
        // 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.
        message.setFrom("01046665190");
        message.setTo(userPhoneNumber);
        message.setText("한글 45자, 영자 90자 이하 입력되면 자동으로 SMS타입의 메시지가 추가됩니다.");

        SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));
        System.out.println(response);

        return response;
    }
}
