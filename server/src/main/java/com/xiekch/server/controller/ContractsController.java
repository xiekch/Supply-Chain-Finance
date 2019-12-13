package com.xiekch.server.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.xiekch.server.domain.*;
import com.xiekch.server.service.ContractsService;

@Controller
public class ContractsController {
    @PostMapping("/contract/new")
    public String newCompany(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        String message = "OK";
        model.addAttribute("message", message);
        return "result";
    }

    @PostMapping("/contract/deal")
    public String deal(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        String message = "OK";
        model.addAttribute("message", message);
        return "result";
    }

    @PostMapping("/contract/transfer")
    public String transfer(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        String message = "OK";
        model.addAttribute("message", message);
        return "result";
    }

    @PostMapping("/contract/finance")
    public String finance(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        String message = "OK";
        model.addAttribute("message", message);
        return "result";
    }
}