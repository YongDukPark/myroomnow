package com.toy.myroomnow.admin.controller;

import com.toy.myroomnow.users.security.CustomUserDetails;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
@RequiredArgsConstructor
public class AdminFeatureController {

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
}
