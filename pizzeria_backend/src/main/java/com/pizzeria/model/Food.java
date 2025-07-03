package com.pizzeria.model;

import jakarta.persistence.*;

@Entity
@Table(name = "foods")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String name;
    private String ingredients;
    private double price;

    //  COSTRUTTORE PER JPA
    public Food(){}


    // 🔹 Costruttore senza ID (per creare nuovi oggetti)
    public Food(String type, String name, String ingredients, double price) {
        this.type = type;
        this.name = name;
        this.ingredients = ingredients;
        this.price = price;
    }
    public Food(Long id, String type, String name, String ingredients, double price) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.ingredients = ingredients;
        this.price = price;
    }
    public Long getId() { return id;}
    public String getType(){ return this.type;}
    public String getName(){ return this.name;}
    public String getIngredients(){ return this.ingredients;}
    public double getPrice(){ return this.price;}

    public void setId(Long id) {
        this.id = id;
    }
    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public void setPrice(double price) {
        this.price = price;
    }


}
