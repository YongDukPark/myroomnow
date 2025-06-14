package com.toy.myroomnow.admin.controller;

import com.toy.myroomnow.users.security.CustomUserDetails;
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
                             Model model){
        model.addAttribute("loginuser", customUserDetails.getUsername());

        return "admin/index";
    }
}
