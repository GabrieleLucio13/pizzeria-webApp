package com.pizzeria.repository.DAO;

import com.pizzeria.model.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class FoodDao {
    @Autowired
    private DataSource dataSource;

    public void save(Food food) throws SQLException {
        String sql = "INSERT INTO foods (type, name, ingredients, price) VALUES (?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, food.getType());
            statement.setString(2, food.getName());
            statement.setString(3, food.getIngredients());
            statement.setDouble(4, food.getPrice());

            statement.executeUpdate();
        }
    }

    public List<Food> findAll() throws SQLException {
        List<Food> foods = new ArrayList<>();
        String sql = "SELECT * FROM foods";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                Food food = new Food();
                food.setId(rs.getLong("id"));
                food.setType(rs.getString("type"));
                food.setName(rs.getString("name"));
                food.setIngredients(rs.getString("ingredients"));
                food.setPrice(rs.getDouble("price"));

                foods.add(food);
            }
        }

        return foods;
    }

    public void delete(Long id) throws SQLException{
        String sql = "DELETE FROM foods WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }
}