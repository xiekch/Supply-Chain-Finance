package com.xiekch.server.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.xiekch.server.domain.*;
import com.xiekch.server.service.UserService;

@Controller
public class MainController {

    @GetMapping("/main")
    public String mainPage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("ownedCompanies", UserService.getInstance().getOwnedCompanies(user));
        model.addAttribute("restCompanies", UserService.getInstance().getRestCompanies(user));
        
        model.addAttribute("user", user);
        return "main";
    }
}