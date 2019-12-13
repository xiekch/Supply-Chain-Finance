package com.xiekch.server.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index(HttpSession session) {
        System.out.println("index");
        if (session.getAttribute("user") == null) {
            System.out.println("to sign in");
            return "index";
        } else {
            System.out.println("already signed in");
            return "redirect:/main";
        }
    }
}
