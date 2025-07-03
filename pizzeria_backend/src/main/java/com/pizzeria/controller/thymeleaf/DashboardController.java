package com.pizzeria.controller.thymeleaf;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session) {
        Boolean isLogged = (Boolean) session.getAttribute("loggedIn");
        if (isLogged == null || !isLogged) {
            return "redirect:" + "http://localhost:4200/login";

        }
        return "admin/dashboard";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:" + "http://localhost:4200/";
    }
}


