package com.xiekch.server.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.xiekch.server.domain.*;
import com.xiekch.server.solidity.*;

@Controller
public class ContractsController {
    @PostMapping("/main")
    public String contract(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        return "main";
    }
}