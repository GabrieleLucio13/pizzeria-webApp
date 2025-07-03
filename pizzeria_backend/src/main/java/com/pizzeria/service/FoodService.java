package com.pizzeria.service;

import com.pizzeria.repository.Proxy.FoodProxy;
import com.pizzeria.model.Food;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FoodService {
    @Autowired
    private FoodProxy foodProxy;
    public List<Food> getFoods() {
        return foodProxy.findAll();
    }

    public void addFood(Food food){
        foodProxy.save(food);
    }
    public void deleteFood(Long id) {
        foodProxy.delete(id);
    }
    @PostConstruct
    public void init() {
        foodProxy.save(new Food("Pizze", "Fanciulla", "Pomodoro, Mozzarella, Basilico", 7));
        foodProxy.save(new Food("Pizze", "Geronimo", "Mozzarella, Gorgonzola, Fontina, Emmental", 9));
        foodProxy.save(new Food("Pizze", "Vivaldi", "Mozzarella, Pomodoro, Funghi, Prosciutto Cotto, Olive, Carciofi", 10));
        foodProxy.save(new Food("Pizze", "Biancaneve", "Mozzarella di Bufala, Pomodorini a fette, Fiordilatte", 10));
        foodProxy.save(new Food("Pizze", "Lucifero", "Mozzarella, Pomodoro, Spianata piccante, Olive Nere", 8));
        foodProxy.save(new Food("Pizze", "Pianura", "Mozzarella, Pomodoro, Rucola, Bresaola, parmigiano a scaglie", 11));
        foodProxy.save(new Food("Insalate", "Estiva", "patate, maionese, uova sode, prezzemolo, olive nere, tonno", 5));
        foodProxy.save(new Food("Insalate", "Dietologa", "Lattuga, aceto balsamico, mais, radicchio", 3));
        foodProxy.save(new Food("Insalate", "Arlecchina", "Lattuga, mirtilli, pomodori, uova sode, carote, aceto balsamico", 5));
        foodProxy.save(new Food("Insalate", "Kyoto", "verza, salmone affumicato, uvetta, salsa di soia, riso", 5));
        foodProxy.save(new Food("Arancini", "l'Americano", "Bacon, Uova Strapazzate, Mozzarella", 3 ));
        foodProxy.save(new Food("Arancini", "il Boscaiolo", "Funghi Porcini, Prezzemolo, Mozzarella, Speck", 3.50 ));
        foodProxy.save(new Food("Arancini", "l'Ortolano", "Peperoni, Melanzane, Mozzarella", 3 ));
        foodProxy.save(new Food("Arancini", "il Siciliano", "Mozzarella, Pomodoro, Piselli", 2.50 ));
        foodProxy.save(new Food("Bevande", "Coca Cola", "33 cl", 1.50 ));
        foodProxy.save(new Food("Bevande", "Birra alla spina(piccola)", "Moretti, Peroni, Nastro Azzurro", 1.50 ));
        foodProxy.save(new Food("Bevande", "Birra alla spina(media)", "Moretti, Peroni, Nastro Azzurro", 2.50 ));
        foodProxy.save(new Food("Bevande", "Acqua Minerale", "Liscia, Gassata(0.5lt)", 1.00 ));
        foodProxy.save(new Food("Bevande", "Chianti", "1.5lt", 15 ));
        foodProxy.save(new Food("Bevande", "Lambrusco", "1.5lt", 12 ));
        foodProxy.save(new Food("Bevande", "Vermentino", "1.5lt", 15 ));
        foodProxy.save(new Food("Bevande", "Prosecco", "1.5lt", 12 ));
    }

}
