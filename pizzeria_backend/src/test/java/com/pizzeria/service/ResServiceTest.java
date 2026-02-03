package com.pizzeria.service;

import com.pizzeria.model.Reservation;
import com.pizzeria.model.Day;
import com.pizzeria.repository.Proxy.ResProxy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ResServiceTest {
    @Mock
    private ResProxy resProxy;
    @Mock
    private CalendarService calendarService;
    @InjectMocks
    private ResService resService;

    @Test
    void shouldThrowExceptionIfPizzeriaIsClosed() {
        Reservation res = new Reservation();
        res.setDate(LocalDate.of(2026, 1, 10));
        res.setTime(LocalTime.of(20, 0));

        Day giorno = new Day();
        giorno.setChiuso(true);

        when(calendarService.findByDate(res.getDate()))
                .thenReturn(giorno);

        assertThrows(IllegalArgumentException.class,
                () -> resService.saveRes(res));

        verify(resProxy, never()).save(any());
    }

    @Test
    void shouldThrowExceptionIfTimeIsBeforeOpening() {
        Reservation res = new Reservation();
        res.setDate(LocalDate.of(2026, 1, 10));
        res.setTime(LocalTime.of(17, 0));

        Day giorno = new Day();
        giorno.setChiuso(false);
        giorno.setApertura(LocalTime.of(18, 0));
        giorno.setChiusura(LocalTime.of(23, 0));

        when(calendarService.findByDate(res.getDate()))
                .thenReturn(giorno);

        assertThrows(IllegalArgumentException.class,
                () -> resService.saveRes(res));

        verify(resProxy, never()).save(any());
    }

    @Test
    void shouldSaveReservationWhenValid() {
        Reservation res = new Reservation();
        res.setDate(LocalDate.of(2026, 1, 10));
        res.setTime(LocalTime.of(20, 0));

        Day giorno = new Day();
        giorno.setChiuso(false);
        giorno.setApertura(LocalTime.of(18, 0));
        giorno.setChiusura(LocalTime.of(23, 0));

        when(calendarService.findByDate(res.getDate()))
                .thenReturn(giorno);

        resService.saveRes(res);

        verify(resProxy).save(res);
    }
}
