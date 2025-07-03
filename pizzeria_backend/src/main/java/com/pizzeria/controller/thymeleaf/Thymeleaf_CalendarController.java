package com.pizzeria.controller.thymeleaf;

import com.pizzeria.model.Day;
import com.pizzeria.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/calendar_editor")
public class Thymeleaf_CalendarController {
    @Autowired
    private CalendarService service;
    @GetMapping
    public String mostraCalendario(Model model) {
        List<Day> days = service.getCalendar();
        model.addAttribute("calendar", days);
        return "admin/events";
    }
    @GetMapping("/edit")
    public String vaiAModifica(@RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data, Model model) {
        List<Day> days = service.getCalendar();
        for (Day day : days) {
            if (day.getData().equals(data)) {
                model.addAttribute("giorno", day);
                return "admin/editDay";
            }
        }
        return "redirect:/calendar_editor";
    }
    @PostMapping
    public String modificaGiorno(@RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data,
                                 @RequestParam(value = "apertura", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime apertura,
                                 @RequestParam(value = "chiusura", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime chiusura,
                                 @RequestParam(value = "chiuso", required = false, defaultValue = "false") boolean chiuso) {

        Day day = new Day();

        day.setData(data);
        day.setChiuso(chiuso);

        DateTimeFormatter giornoFormatter = DateTimeFormatter.ofPattern("EEEE", Locale.ITALIAN);
        DateTimeFormatter meseFormatter = DateTimeFormatter.ofPattern("MMMM", Locale.ITALIAN);

        day.setGiorno(data.format(giornoFormatter));
        day.setMese(data.format(meseFormatter));


        if(day.isChiuso()){
            day.setApertura(LocalTime.MIDNIGHT);
            day.setChiusura(LocalTime.MIDNIGHT);
        }
        else{
            day.setApertura(apertura);
            day.setChiusura(chiusura);
        }
        service.update(day);
        return "redirect:/calendar_editor";
    }
}