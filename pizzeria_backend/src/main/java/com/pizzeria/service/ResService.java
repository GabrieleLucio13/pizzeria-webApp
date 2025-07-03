package com.pizzeria.service;

import com.pizzeria.repository.Proxy.ResProxy;
import com.pizzeria.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResService {
    @Autowired
    private ResProxy resProxy;

    public List<Reservation> getallRes() {
        return resProxy.findAll();
    }
    public void saveRes(Reservation res)  {
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
