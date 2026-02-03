package com.pizzeria.repository.DAO;

import com.pizzeria.model.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.test.context.ContextConfiguration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@JdbcTest
@ContextConfiguration(classes = {ReservationDao.class})
@DisplayName("ReservationDao Integration Tests")
class ReservationDaoTest {

    @Autowired
    private ReservationDao reservationDao;
    @Autowired
    private DataSource dataSource;
    private Reservation testReservation;

    @BeforeEach
    void setUp() {
        testReservation = new Reservation();
        testReservation.setName("Test User");
        testReservation.setContact("test@example.com");
        testReservation.setDate(LocalDate.now().plusDays(5));
        testReservation.setTime(LocalTime.of(18, 0));
        testReservation.setNumber(2);
    }

    @Test
    @DisplayName("Should save reservation and generate random code")
    void save_shouldGenerateRandomCode() throws SQLException {
        // When
        reservationDao.save(testReservation);

        // Then
        assertThat(testReservation.getRandomCode())
                .isNotNull()
                .hasSize(8)
                .matches("[A-Z0-9]{8}");
    }

    @Test
    @DisplayName("Should find reservation by random code")
    void findByRandomCode_shouldReturnReservation_whenCodeExists() throws SQLException {
        // Given
        reservationDao.save(testReservation);
        String code = testReservation.getRandomCode();

        // When
        Optional<Reservation> found = reservationDao.findByRandomCode(code);

        // Then
        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("Test User");
        assertThat(found.get().getContact()).isEqualTo("test@example.com");
        assertThat(found.get().getNumber()).isEqualTo(2);
    }

    @Test
    @DisplayName("Should return empty when code not found")
    void findByRandomCode_shouldReturnEmpty_whenCodeNotExists() throws SQLException {
        // When
        Optional<Reservation> found = reservationDao.findByRandomCode("NOTEXIST");

        // Then
        assertThat(found).isEmpty();
    }

    @Test
    @DisplayName("Should find all reservations")
    void findAll_shouldReturnAllReservations() throws SQLException {
        // Given: salva 3 prenotazioni
        Reservation res1 = createReservation("User1", "user1@test.com", 2);
        Reservation res2 = createReservation("User2", "user2@test.com", 4);
        Reservation res3 = createReservation("User3", "user3@test.com", 6);

        reservationDao.save(res1);
        reservationDao.save(res2);
        reservationDao.save(res3);

        // When
        List<Reservation> all = reservationDao.findAll();

        // Then
        assertThat(all)
                .hasSizeGreaterThanOrEqualTo(3)  // Potrebbe avere altri dati
                .extracting(Reservation::getName)
                .contains("User1", "User2", "User3");
    }

    @Test
    @DisplayName("Should update reservation")
    void updateRes_shouldModifyExistingReservation() throws SQLException {
        // Given: salva prenotazione iniziale
        reservationDao.save(testReservation);
        String code = testReservation.getRandomCode();

        // When: aggiorna i dati
        testReservation.setName("Updated Name");
        testReservation.setNumber(8);
        testReservation.setDate(LocalDate.now().plusDays(10));
        reservationDao.updateRes(testReservation);

        // Then: verifica che i dati siano cambiati
        Optional<Reservation> updated = reservationDao.findByRandomCode(code);
        assertThat(updated).isPresent();
        assertThat(updated.get().getName()).isEqualTo("Updated Name");
        assertThat(updated.get().getNumber()).isEqualTo(8);
    }

    @Test
    @DisplayName("Should delete reservation by code")
    void delete_shouldRemoveReservation() throws SQLException {
        // Given
        reservationDao.save(testReservation);
        String code = testReservation.getRandomCode();

        // When
        reservationDao.delete(code);

        // Then
        Optional<Reservation> found = reservationDao.findByRandomCode(code);
        assertThat(found).isEmpty();
    }

    @Test
    @DisplayName("Should generate unique codes for multiple reservations")
    void save_shouldGenerateUniqueCodes() throws SQLException {
        // Given: crea 5 prenotazioni
        Reservation res1 = createReservation("User1", "u1@test.com", 2);
        Reservation res2 = createReservation("User2", "u2@test.com", 2);
        Reservation res3 = createReservation("User3", "u3@test.com", 2);
        Reservation res4 = createReservation("User4", "u4@test.com", 2);
        Reservation res5 = createReservation("User5", "u5@test.com", 2);

        // When: salva tutte
        reservationDao.save(res1);
        reservationDao.save(res2);
        reservationDao.save(res3);
        reservationDao.save(res4);
        reservationDao.save(res5);

        // Then: tutti i codici devono essere diversi
        assertThat(res1.getRandomCode()).isNotEqualTo(res2.getRandomCode());
        assertThat(res2.getRandomCode()).isNotEqualTo(res3.getRandomCode());
        assertThat(res3.getRandomCode()).isNotEqualTo(res4.getRandomCode());
        assertThat(res4.getRandomCode()).isNotEqualTo(res5.getRandomCode());
    }

    // Helper method
    private Reservation createReservation(String name, String contact, int number) {
        Reservation res = new Reservation();
        res.setName(name);
        res.setContact(contact);
        res.setDate(LocalDate.now().plusDays(5));
        res.setTime(LocalTime.of(19, 0));
        res.setNumber(number);
        return res;
    }
}
