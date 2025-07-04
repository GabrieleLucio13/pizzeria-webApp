package com.pizzeria.service;

import com.pizzeria.model.Day;
import com.pizzeria.repository.Proxy.ResProxy;
import com.pizzeria.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class ResService {
    @Autowired
    private ResProxy resProxy;

    @Autowired
    private CalendarService calendarService;

    public List<Reservation> getallRes() {
        return resProxy.findAll();
    }
    public void saveRes(Reservation res)  {
        LocalDate dataPrenotazione = res.getDate();
        LocalTime orarioPrenotazione = res.getTime();

        Day giorno = calendarService.findByDate(dataPrenotazione);

        if (giorno == null || giorno.isChiuso()) {
            throw new IllegalArgumentException("La pizzeria è chiusa in quella data.");
        }

        if (orarioPrenotazione.isBefore(giorno.getApertura()) || orarioPrenotazione.isAfter(giorno.getChiusura())) {
            throw new IllegalArgumentException("L’orario selezionato non è valido.");
        }
        resProxy.save(res);
    }
    public void deleteRes(Reservation res) {
        resProxy.delete(res.getRandomCode());
    }
    public Optional<Reservation> findByRandomCode(String randomCode){
        return resProxy.findByRandomCode(randomCode);
    }

    public void updateReservation(Reservation reservation) {
        resProxy.updateRes(reservation);
    }
}
