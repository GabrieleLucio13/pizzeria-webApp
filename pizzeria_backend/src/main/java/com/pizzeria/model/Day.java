package com.pizzeria.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "calendar")
public class Day {

    @Id
    private LocalDate data;
    private String giorno;
    private String mese;
    private LocalTime apertura = LocalTime.MIN;  // Default: 00:00
    private LocalTime chiusura = LocalTime.MIN;  // Default: 00:00
    private boolean chiuso;

    //getter e setter
    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }
    public LocalTime getApertura() {
        return apertura;
    }
    public void setApertura(LocalTime apertura) {
        this.apertura = apertura;
    }
    public LocalTime getChiusura() {
        return chiusura;
    }
    public void setChiusura(LocalTime chiusura) {
        this.chiusura = chiusura;
    }
    public boolean isChiuso() {
        return chiuso;
    }
    public void setChiuso(boolean chiuso) {
        this.chiuso = chiuso;
    }

    public String getGiorno() {
        return giorno;
    }
    public String getMese() {
        return mese;
    }

    public void setGiorno(String giorno) {
        this.giorno = giorno;
    }
    public void setMese(String mese) {
        this. mese = mese;
    }
}
