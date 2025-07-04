package com.pizzeria.controller.thymeleaf;

import com.pizzeria.model.Food;
import com.pizzeria.service.FoodService;

import java.util.List;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/menu_editor")
public class Thymeleaf_MenuController {

    @Autowired
    private FoodService foodService;
    @GetMapping
    public String mostraMenu(Model model, HttpSession session) {
        Boolean isLogged = (Boolean) session.getAttribute("loggedIn");
        if (isLogged == null || !isLogged) {
            return "redirect:" + "http://localhost:4200/login";

        }
        List<Food> foods = foodService.getFoods();
        model.addAttribute("foods", foods);
        return "admin/menu";
    }
    @PostMapping("/add")
    public String aggiungiPiatto(@ModelAttribute Food food) {
        foodService.addFood(food);
        return "redirect:/dashboard";
    }

    @PostMapping("/delete")
    public String rimuoviPiatto(@RequestParam("id") Long id) {
        foodService.deleteFood(id);
        return "redirect:/dashboard";
    }
}
