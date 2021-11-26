package com.example.practicapp;

import com.example.practicapp.objects.User;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.*;
import java.util.Base64;
import java.util.Objects;

abstract class DatabaseController {
    private final static String jdbcURL = "jdbc:postgresql://localhost:5432/Practic";
    private final static String username = "postgres";
    private final static String password = "root";
    private static final String USER_SELECT_QUERY = "SELECT * FROM users WHERE login = ? AND password = ?";
    private static Connection connection;

    public static Connection getConnection() {
        try {
            if (Objects.isNull(connection) || connection.isClosed()) {
                connection = DriverManager.getConnection(jdbcURL, username, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    public static boolean isLoggedIn(String login, String password) throws SQLException, InvalidKeySpecException, NoSuchAlgorithmException {
        boolean result = false;
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        salt = login.getBytes();
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = factory.generateSecret(spec).getEncoded();
        password = Base64.getEncoder().encodeToString(hash);
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(USER_SELECT_QUERY)) {
            preparedStatement.setString(1,login);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User.setLogin(resultSet.getString("login"));
                User.setId(resultSet.getInt("id"));
//                User.setContact(resultSet.getString("contacts"));
                preparedStatement.close();
                result = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
