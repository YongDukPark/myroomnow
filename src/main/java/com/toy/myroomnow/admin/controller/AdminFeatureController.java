package com.toy.myroomnow.admin.controller;

import com.toy.myroomnow.admin.domain.Qrroominfo;
import com.toy.myroomnow.admin.service.QrroominfoService;
import com.toy.myroomnow.users.security.CustomUserDetails;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminFeatureController {

    private final QrroominfoService qrroominfoService;

    @GetMapping("/admin/index")
    public String adminIndex(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                             //HttpServletResponse response,
                             Model model){
        // 전역 처리를 진행했기에 제거
//        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
//        response.setHeader("Pragma", "no-cache"); // HTTP 1.0
//        response.setDateHeader("Expires", 0); // Proxies

        model.addAttribute("loginuser", customUserDetails.getUsername());

        return "admin/index";
    }

    //QR 생성폼
    @GetMapping("/admin/qrs/new")
    public String qrNewForm(){
        return "admin/qr/create";
    }

    //QR 리스트폼
    @GetMapping("/admin/qrs")
    public String qrlist(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                         Model model){

        //qrroominfoService.findByid()

        //qrroominfoService.findAllByIdAadCreateid(customUserDetails.getUser().getId());
        List<Qrroominfo> roomlist = qrroominfoService.findAllByCreateid(customUserDetails.getId());

        model.addAttribute("roomlist", roomlist);

        return "admin/qr/roomlist";
    }

    //QR 디테일
//    @PostMapping("/admin/qrs")
//    public String qrlist(){
//
//        /qrroominfoService.findAllByIdAadCreateid();
//
//        return "admin/qr/list";
//    }
}
