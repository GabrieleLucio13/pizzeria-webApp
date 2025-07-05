package com.pizzeria.service;

import com.pizzeria.repository.Proxy.DayProxy;
import com.pizzeria.model.Day;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Service
public class CalendarService {

    @Autowired
    private DayProxy proxy;

    @PostConstruct
    public void init()  {
        LocalDate today = LocalDate.now();
        for (int i = 0; i < 90; i++) {
            LocalDate data = today.plusDays(i);

            Day day = new Day();
            day.setData(data);

            DateTimeFormatter giornoFormatter = DateTimeFormatter.ofPattern("EEEE", Locale.ITALIAN);
            DateTimeFormatter meseFormatter = DateTimeFormatter.ofPattern("MMMM", Locale.ITALIAN);

            day.setGiorno(data.format(giornoFormatter));
            day.setMese(data.format(meseFormatter));

            if (data.getDayOfWeek() == DayOfWeek.MONDAY) {
                day.setChiuso(true);
            } else {
                day.setChiuso(false);
                day.setApertura(LocalTime.of(18, 0));
                day.setChiusura(LocalTime.of(23, 0));
            }

            if (!proxy.existsByDate(data)) {
                proxy.save(day);
            }
        }
    }

    public List<Day> getCalendar() {
        return proxy.getAll();
    }

    public void update(Day day) {
        proxy.update(day);
    }

    public Day findByDate(LocalDate data) {
        return proxy.findByDate(data);
    }
}
