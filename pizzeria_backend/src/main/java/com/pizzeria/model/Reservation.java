package com.pizzeria.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "reservations", uniqueConstraints = @UniqueConstraint(columnNames = {"nome_di_riferimento", "date", "time"}))
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String contact;
    @Column(unique = true)
    private String randomCode;
    @Column
    private int number;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime time;

    // 🔹 Costruttore vuoto richiesto da JPA
    public Reservation() {}


    public Reservation(String name, String contact, int number, LocalDate date, LocalTime time) {
        this.name = name;
        this.contact = contact;
        this.number = number;
        this.date = date;
        this.time = time;
    }

    //  Costruttore completo con id
    public Reservation(Long id, String name, String email, int number, LocalDate date, LocalTime time) {
        this.name = name;
        this.id = id;
        this.contact = email;
        this.number = number;
        this.date = date;
        this.time = time;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public LocalTime getTime() {
        return time;
    }
    public void setTime(LocalTime time) {
        this.time = time;
    }
    public String getRandomCode() {
        return randomCode;
    }
    public void setRandomCode(String randomCode) {
        this.randomCode = randomCode;
    }
}

