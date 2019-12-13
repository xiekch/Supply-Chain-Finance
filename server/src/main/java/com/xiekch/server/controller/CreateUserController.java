package com.xiekch.server.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.xiekch.server.domain.*;
import com.xiekch.server.service.UserService;

@Controller
public class CreateUserController {
    private final static int interval = 60 * 60 * 4;

    @PostMapping("/")
    public String createUser(User user, HttpSession session, Model model) {
        try {
            if (UserService.getInstance().userSignUp(user)) {
                session.setAttribute("user", user);
                session.setMaxInactiveInterval(interval);
                System.out.println("user signup");
                return "redirect:/main";
            }
        } catch (RuntimeException e) {
            try {
                if (UserService.getInstance().userSignIn(user)) {
                    session.setAttribute("user", user);
                    session.setMaxInactiveInterval(interval);
                    System.out.println("user signin");
                    return "redirect:/main";
                }
            } catch (RuntimeException err) {
                err.printStackTrace();
                model.addAttribute("error", err.toString().split(":")[1]);
                return "index"; // "{\"error\": \"" + err.toString().split(":")[1] + "\"}";
            }
        }
        return "redirect:/";
    }
}