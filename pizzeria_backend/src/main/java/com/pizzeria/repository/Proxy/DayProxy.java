package com.pizzeria.repository.Proxy;

import com.pizzeria.repository.DAO.DayDao;
import com.pizzeria.model.Day;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DayProxy {
    @Autowired
    private DayDao dao;

    public void save(Day d) {
        try {
            dao.save(d);
        } catch (SQLException e) {
            System.out.println("Errore durante il salvataggio: " + e.getMessage());
        }
    }

    public List<Day> getAll() {
        List<Day> days = new ArrayList<>();
        try{
            days = dao.getAll();
        } catch (SQLException e){
            System.out.println("Errore durante la ricerca del calendario: " + e.getMessage());
        }
        return days;
    }

    public void update(Day day) {
        try{
            dao.update(day);
        } catch (SQLException e){
            System.out.println("Errore durante la ricerca del calendario: " + e.getMessage());
        }
    }

    public Day findByDate(LocalDate date){
        Day day = new Day();
        try{
            day = dao.findByDate(date);
        } catch (SQLException e){
            System.out.println("Errore durante la ricerca del calendario: " + e.getMessage());
        }
        return day;
    }

    public boolean existsByDate(LocalDate data) {
        boolean flag = false;
        try{
            flag = dao.existsByDate(data);
        }
        catch(SQLException e){
            System.out.println("Errore durante la ricerca del calendario: " + e.getMessage());
        }
        return flag;
    }
}
