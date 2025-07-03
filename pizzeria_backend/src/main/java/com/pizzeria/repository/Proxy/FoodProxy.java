package com.pizzeria.repository.Proxy;

import com.pizzeria.repository.DAO.FoodDao;
import com.pizzeria.model.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class FoodProxy{
    @Autowired
    private FoodDao dao;

    public void save(Food f) {
        try {
            dao.save(f);
        } catch (SQLException e) {
            System.out.println("Errore durante il salvataggio: " + e.getMessage());
        }
    }

    public List<Food> findAll(){
        List<Food> foods = new ArrayList<>();
        try {
             foods = dao.findAll();
        } catch (SQLException e) {
            System.out.println("Errore durante la ricerca: " + e.getMessage());
        }
        return foods;
    }

    public void delete(Long id) {
        try {
            dao.delete(id);
        } catch (SQLException e) {
            System.out.println("Errore durante la rimozione del piatto: " + e.getMessage());
        }
    }
}
