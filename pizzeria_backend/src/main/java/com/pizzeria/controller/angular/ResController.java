package com.pizzeria.controller.angular;

import com.pizzeria.model.Reservation;
import com.pizzeria.service.ResService;
import com.pizzeria.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/reservation")
@CrossOrigin("*")
public class ResController {

    @Autowired
    private ResService resService;
    @Autowired
    private MailService mailService;

    @PostMapping
    public void createRes(@RequestBody Reservation res) {
        resService.saveRes(res);
        mailService.sendEmail("utente@example.com",
                "Conferma Prenotazione",
                ("Prenotazione salvata con successo! Ecco il tuo codice: " + res.getRandomCode())
        );
    }

    @PutMapping("/{randomCode}")
    public ResponseEntity<Void> updateReservation(@PathVariable String randomCode, @RequestBody Reservation updatedReservation) {
        Optional<Reservation> res = resService.findByRandomCode(randomCode);
        if (res.isPresent()) {
            updatedReservation.setRandomCode(randomCode);
            resService.updateReservation(updatedReservation);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{randomCode}")
    public ResponseEntity<Void> deleteByCode(@PathVariable String randomCode) {
        Optional<Reservation> res = resService.findByRandomCode(randomCode);
        if (res.isPresent()) {
            resService.deleteRes(res.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{randomCode}")
    public ResponseEntity<Reservation> getResByCode(@PathVariable String randomCode){
        Optional<Reservation> res = resService.findByRandomCode(randomCode);
        return res.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
