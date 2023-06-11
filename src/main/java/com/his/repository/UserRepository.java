package com.his.repository;

import com.his.model.User;
import com.his.util.PasswordUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {
    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet resultSet = null;
    public User getUserByUsername(String username) {
        try {
            connection = DatabaseConnector.getConnection();
            String query = "SELECT * FROM users WHERE username = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, username);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                // User found, retrieve the password hash and salt
                String passwordHash = resultSet.getString("password_hash");
                byte[] salt = resultSet.getBytes("salt");
                String role = resultSet.getString("role");
                return new User(username, passwordHash, salt, role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();

        }
        return null; // User not found or error occurred
    }

    public User createUser(String username, String password, String role){
        byte[] salt = PasswordUtil.generateSalt();
        String passwordHash = PasswordUtil.hashPassword(password,salt);
        try{
            connection = DatabaseConnector.getConnection();
            String query = "INSERT INTO users (username, password_hash, salt, role) VALUES (?, ?, ?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, passwordHash);
            statement.setBytes(3, salt);
            statement.setString(4, role);

            int rowsAffected = statement.executeUpdate();

            if(rowsAffected > 0){
                return new User(username,passwordHash,salt,role);
            }
        }catch(SQLException e){
            e.printStackTrace();
        } finally {
            closeDB();
        }
        return null; //User creation failed
    }

    private void closeDB(){
        DatabaseConnector.closeResultSet(resultSet);
        DatabaseConnector.closeStatement(statement);
        DatabaseConnector.closeConnection(connection);
    }
}

