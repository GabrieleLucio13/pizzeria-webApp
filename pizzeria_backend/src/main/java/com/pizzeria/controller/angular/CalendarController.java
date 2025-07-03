package com.pizzeria.controller.angular;

import com.pizzeria.model.Day;
import com.pizzeria.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/calendar")
@CrossOrigin("*")
public class CalendarController {

    @Autowired
    private CalendarService service;

    @GetMapping
    public List<Day> getCalendar() {
        return service.getCalendar();
    }
}
