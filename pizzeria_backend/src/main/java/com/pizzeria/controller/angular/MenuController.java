package com.pizzeria.controller.angular;

import com.pizzeria.model.Food;
import com.pizzeria.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/foods")
@CrossOrigin("*")
public class MenuController {

    @Autowired
    private FoodService foodService;

    @GetMapping
    public List<Food> getAllFoods() {
        return foodService.getFoods();
    }
}
