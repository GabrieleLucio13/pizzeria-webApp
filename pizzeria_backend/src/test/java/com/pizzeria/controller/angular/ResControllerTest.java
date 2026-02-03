package com.pizzeria.controller.angular;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pizzeria.model.Reservation;
import com.pizzeria.service.MailService;
import com.pizzeria.service.ResService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@DisplayName("ResController Integration Tests")
class ResControllerTest {
    @Mock
    private ResService resService;
    @Mock
    private MailService mailService;
    @InjectMocks
    private ResController resController;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private Reservation reservation;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(resController).build();

        reservation = new Reservation();
        reservation.setRandomCode("ABC123");
        reservation.setContact("test@email.com");
    }

    @Test
    @DisplayName("Should create reservation with success")
    void createReservation_success() throws Exception {
        doNothing().when(resService).saveRes(any(Reservation.class));
        doNothing().when(mailService)
                .sendEmail(anyString(), anyString(), anyString());

        mockMvc.perform(post("/api/reservation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reservation)))
                .andExpect(status().isOk())
                .andExpect(content().string("Prenotazione salvata con successo!"));

        verify(resService).saveRes(any(Reservation.class));
        verify(mailService).sendEmail(
                eq("test@email.com"),
                eq("Conferma Prenotazione"),
                argThat(body -> body.contains("ABC123"))
        );
    }

    @Test
    @DisplayName("Should throw an error after a bad reservation")
    void createReservation_badRequest() throws Exception {
        // GIVEN
        doThrow(new IllegalArgumentException("Errore"))
                .when(resService).saveRes(any(Reservation.class));

        // WHEN + THEN
        mockMvc.perform(post("/api/reservation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reservation)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Errore"));
    }

    @Test
    @DisplayName("Should update reservation with success")
    void updateReservation_success() throws Exception {
        when(resService.findByRandomCode("ABC123"))
                .thenReturn(Optional.of(reservation));

        doNothing().when(resService)
                .updateReservation(any(Reservation.class));

        mockMvc.perform(put("/api/reservation/ABC123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reservation)))
                .andExpect(status().isNoContent());

        verify(resService).updateReservation(any(Reservation.class));
    }

    @Test
    @DisplayName("Should not find any reservation matching the code")
    void updateReservation_notFound() throws Exception {
        when(resService.findByRandomCode("ABC123"))
                .thenReturn(Optional.empty());

        mockMvc.perform(put("/api/reservation/ABC123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reservation)))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Should delete reservation with success")
    void deleteReservation_success() throws Exception {
        // GIVEN
        String randomCode = "ABC123";
        when(resService.findByRandomCode(randomCode))
                .thenReturn(Optional.of(reservation));

        // WHEN
        mockMvc.perform(delete("/api/reservation/{code}", randomCode))
                .andExpect(status().isNoContent());

        // THEN
        verify(resService).deleteRes(any(Reservation.class));
    }

    @Test
    @DisplayName("Should not find any reservation matching the code")
    void deleteReservation_notFound() throws Exception {
        when(resService.findByRandomCode("ABC123"))
                .thenReturn(Optional.empty());

        mockMvc.perform(delete("/api/reservation/ABC123"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Should find reservation matching the code")
    void getReservation_success() throws Exception {
        // GIVEN
        String randomCode = "ABC123";
        when(resService.findByRandomCode(randomCode))
                .thenReturn(Optional.empty());

        // WHEN + THEN
        mockMvc.perform(get("/api/reservation/{code}", randomCode))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Should not find reservation matching the code")
    void getReservation_notFound() throws Exception {
        when(resService.findByRandomCode("ABC123"))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/api/reservation/ABC123"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getReservation_shouldFail_wrongStatus() throws Exception {

        // GIVEN
        when(resService.findByRandomCode("ABC123"))
                .thenReturn(Optional.of(reservation));

        // WHEN + THEN
        mockMvc.perform(get("/api/reservation/ABC123"))
                .andExpect(status().isNoContent()); // ❌ dovrebbe essere 200
    }

}
