package com.pizzeria.repository.Proxy;

import com.pizzeria.repository.DAO.ReservationDao;
import com.pizzeria.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ResProxy {

    @Autowired
    private ReservationDao dao;

    public void save(Reservation r) {
        try {
            dao.save(r);
            System.out.println("Prenotazione salvata con successo! Ecco il tuo codice: " + r.getRandomCode());
        } catch (SQLException e) {
            System.out.println("Errore durante il salvataggio: " + e.getMessage());
        }

    }

    public Optional<Reservation> findByRandomCode(String code) {
        try {
            return dao.findByRandomCode(code);
        } catch (SQLException e) {
            System.out.println("Errore nella ricerca: " + e.getMessage());
            return Optional.empty();
        }
    }
    public List<Reservation> findAll(){
        List<Reservation> reservations = new ArrayList<>();
        try {
            reservations = dao.findAll();
        } catch (SQLException e) {
            System.out.println("Errore durante la ricerca: " + e.getMessage());
        }
        return reservations;
    }
    public void delete(String code){
        try {
            dao.delete(code);
        } catch (SQLException e) {
            System.out.println("Errore nella cancellazione: " + e.getMessage());
        }
    }


    public void updateRes(Reservation reservation) {
        try {
            dao.updateRes(reservation);
        } catch (SQLException e) {
            System.out.println("Errore nella modifica: " + e.getMessage());
        }
    }

}


