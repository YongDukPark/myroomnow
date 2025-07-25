package com.toy.myroomnow.admin.controller;

import com.toy.myroomnow.admin.domain.Qrroominfo;
import com.toy.myroomnow.admin.dto.QrroominfoDto;
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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminFeatureController {

    private final QrroominfoService qrroominfoService;

    @GetMapping("/admin/index")
    public String adminIndex(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                             Model model){

        model.addAttribute("loginuser", customUserDetails.getUsername());

        return "admin/index";
    }

    //QR 생성폼
    @GetMapping("/admin/qrs/new")
    public String qrUpdateForm(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                            Model model){

        model.addAttribute();

        return "admin/qr/qrupdateform";
    }

    //QR Update폼
    @PostMapping("/admin/qrs/update")
    public String qrUpdateForm(@RequestParam("id") Long id,
                               Model model){

        model.addAttribute();

        return "admin/qr/qrupdateform";
    }

    //QR 객실정보 저장 및 수정
    @PostMapping("/admin/qrs/save")
    public String qrUpdateForm(QrroominfoDto qrroominfoDto){

        return "null";
    }

    //QR 리스트폼
    @GetMapping("/admin/qrs")
    public String qrlist(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                         Model model){

        List<QrroominfoDto> roomlist = qrroominfoService.findAllByCreateid(customUserDetails.getId());
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
