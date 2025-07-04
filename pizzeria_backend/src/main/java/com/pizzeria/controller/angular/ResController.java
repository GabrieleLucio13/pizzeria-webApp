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
    public ResponseEntity<String> createRes(@RequestBody Reservation res) {
       try{
           resService.saveRes(res);
           mailService.sendEmail(res.getContact(),
                "Conferma Prenotazione",
                ("Prenotazione salvata con successo! Ecco il tuo codice: " + res.getRandomCode()) + ". Attenzione! Salva questo codice il prima possibile e non condividerlo con nessuno. Qualora volessi modificare la prenotazione, questo codice non sarà più valido, te ne invieremo un altro");

           return ResponseEntity.ok("Prenotazione salvata con successo!");
       } catch (IllegalArgumentException e) {
           return ResponseEntity.badRequest().body(e.getMessage());
       }
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
