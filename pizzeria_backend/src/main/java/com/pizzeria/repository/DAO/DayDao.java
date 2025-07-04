package com.pizzeria.repository.DAO;

import com.pizzeria.model.Day;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DayDao {
    @Autowired
    private DataSource dataSource;

    public boolean existsByDate(LocalDate data) throws SQLException {
        String sql = "SELECT COUNT(*) FROM calendar WHERE data = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setDate(1, Date.valueOf(data));
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    public void save(Day day) throws SQLException {
        String sql = "INSERT INTO calendar (data, apertura, chiusura, chiuso, giorno, mese) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setDate(1, Date.valueOf(day.getData()));
            statement.setTime(2, Time.valueOf(day.getApertura()));
            statement.setTime(3, Time.valueOf(day.getChiusura()));
            statement.setBoolean(4, day.isChiuso());
            statement.setString(5, day.getGiorno());
            statement.setString(6, day.getMese());

            statement.executeUpdate();
        }
    }

    public List<Day> getAll() throws SQLException {
        List<Day> days = new ArrayList<>();
        String sql = "SELECT * FROM calendar";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                Day day = new Day();
                day.setData(rs.getDate("data").toLocalDate());
                day.setApertura(rs.getTime("apertura").toLocalTime());
                day.setChiusura(rs.getTime("chiusura").toLocalTime());
                day.setChiuso(rs.getBoolean("chiuso"));
                day.setGiorno(rs.getString("giorno"));
                day.setMese(rs.getString("mese"));

                days.add(day);
            }
        }
        return days;
    }


    public void update(Day day) throws SQLException {
        String sql = "UPDATE calendar SET apertura = ?, chiusura = ?, chiuso = ?, giorno = ?, mese = ? WHERE data = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setTime(1, Time.valueOf(day.getApertura()));
            statement.setTime(2, Time.valueOf(day.getChiusura()));
            statement.setBoolean(3, day.isChiuso());
            statement.setString(4, day.getGiorno());
            statement.setString(5, day.getMese());
            statement.setDate(6, Date.valueOf(day.getData()));

            statement.executeUpdate();
        }
    }

    public Day findByDate(LocalDate date) throws SQLException {
        String sql = "SELECT * FROM calendar WHERE data = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setDate(1, Date.valueOf(date));
            ResultSet rs = statement.executeQuery();

        if (rs.next()) {
            Day day = new Day();
            day.setData(rs.getDate("data").toLocalDate());
            day.setGiorno(rs.getString("giorno"));
            day.setMese(rs.getString("mese"));
            day.setApertura(rs.getTime("apertura").toLocalTime());
            day.setChiusura(rs.getTime("chiusura").toLocalTime());
            day.setChiuso(rs.getBoolean("chiuso"));
            return day;
        }
        return null;
        }
    }

}
