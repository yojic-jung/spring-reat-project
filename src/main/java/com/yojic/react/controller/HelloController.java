package com.yojic.react.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.yojic.react.dto.MembersDTO;
import com.yojic.react.security.SecurityService;

@Controller
public class HelloController {
	
    @Autowired
    private SecurityService securityService;

    @GetMapping("/")
    public String homeView(HttpServletRequest requset) {
    	System.out.println(requset.getUserPrincipal());
        return "pages/home";
    }

    @GetMapping("/login")
    public String loginView() {
        return "pages/login";
    }

    @GetMapping("/signup")
    public String signupView() {
        return "pages/signup";
    }

    @PostMapping("/signup")
    public String signup(MembersDTO memberTO) {
    	securityService.save(memberTO);
        return "redirect:/login";
    }

    @GetMapping("/member/info")
    public String userInfoView(HttpServletRequest requset) {
    	System.out.println(requset.getUserPrincipal());
        return "pages/user_info";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String adminView() {
        return "pages/admin";
    }

    @GetMapping("/denied")
    public String deniedView() {
        return "pages/denied";
    }
}
