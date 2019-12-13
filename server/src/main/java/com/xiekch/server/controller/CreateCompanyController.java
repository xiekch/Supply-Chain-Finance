package com.xiekch.server.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.xiekch.server.domain.*;
import com.xiekch.server.service.ContractsService;

@Controller
public class CreateCompanyController {

    @PostMapping("/createCompany")
    public String createCompany(@RequestParam("companyName") String companyName, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");    
        
        return "redirect:/";
    }
}