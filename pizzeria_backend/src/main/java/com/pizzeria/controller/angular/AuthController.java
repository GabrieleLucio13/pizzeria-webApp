package com.pizzeria.controller.angular;

import com.pizzeria.model.DTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody DTO login, HttpSession session) {
        if ("admin".equals(login.getUsername()) && "1234".equals(login.getPassword())) {
            session.setAttribute("loggedIn", true);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenziali errate");
        }
    }

    @GetMapping("/check-session")
    public ResponseEntity<?> checkSession(HttpSession session) {
        Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");
        if (loggedIn != null && loggedIn) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}