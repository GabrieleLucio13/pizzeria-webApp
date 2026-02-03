package com.pizzeria.repository.DAO;

import com.pizzeria.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class ReservationDao {
    @Autowired
    private DataSource dataSource;

    public void save(Reservation res) throws SQLException {
        String sql = "INSERT INTO reservations (name, contact, date, time, number, random_code) VALUES (?, ?, ?, ?, ?, ?)";
        String code = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        res.setRandomCode(code);

        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, res.getName());
            statement.setString(2, res.getContact());
            statement.setDate(3, java.sql.Date.valueOf(res.getDate()));
            statement.setTime(4, java.sql.Time.valueOf(res.getTime()));
            statement.setInt(5, res.getNumber());
            statement.setString(6, res.getRandomCode());

            statement.executeUpdate();

        }
    }

    public Optional<Reservation> findByRandomCode(String code) throws SQLException {
        String sql = "SELECT * FROM reservations WHERE random_code = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, code);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                Reservation r = new Reservation();
                r.setId(rs.getLong("id"));
                r.setName(rs.getString("name"));
                r.setContact(rs.getString("contact"));
                r.setDate(rs.getDate("date").toLocalDate());
                r.setTime(rs.getTime("time").toLocalTime());
                r.setNumber(rs.getInt("number"));
                r.setRandomCode(rs.getString("random_code"));

                return Optional.of(r);
            }

            return Optional.empty();
        }
    }

    public List<Reservation> findAll() throws SQLException {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservations";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                Reservation r = new Reservation();
                r.setId(rs.getLong("id"));
                r.setName(rs.getString("name"));
                r.setContact(rs.getString("contact"));
                r.setDate(rs.getDate("date").toLocalDate());
                r.setTime(rs.getTime("time").toLocalTime());
                r.setNumber(rs.getInt("number"));
                r.setRandomCode(rs.getString("random_code"));

                reservations.add(r);
            }
        }

        return reservations;
    }

    public void delete(String randomCode) throws SQLException{
        String sql = "DELETE FROM reservations WHERE random_code = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, randomCode);
            statement.executeUpdate();
        }
    }

    public void updateRes(Reservation res) throws SQLException {

        String sql = "UPDATE reservations SET name = ?, contact = ?, date = ?, time = ?, number = ? WHERE random_code = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {


            statement.setString(1, res.getName());
            statement.setString(2, res.getContact());
            statement.setDate(3, Date.valueOf(res.getDate()));
            statement.setTime(4, Time.valueOf(res.getTime()));
            statement.setInt(5, res.getNumber());
            statement.setString(6, res.getRandomCode());

            statement.executeUpdate();
        }
    }
}
