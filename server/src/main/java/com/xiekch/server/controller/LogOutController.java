package com.xiekch.server.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LogOutController {
    @PostMapping("/logOut")
    public String createUser(HttpSession session) {
        System.out.println("Log Out");
        try {
            session.removeAttribute("user");
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }
}