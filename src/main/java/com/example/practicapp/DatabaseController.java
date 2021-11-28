package com.example.practicapp;

import com.example.practicapp.objects.User;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

abstract class DatabaseController {
    private final static String jdbcURL = "jdbc:postgresql://192.168.100.4:5432/Vkid";
    private final static String username = "postgres";
    private final static String password = "root";
    private static final String USER_SELECT_QUERY = "SELECT * FROM users WHERE login = ? AND password = ?";
    private static final String USER_FIND_QUERY = "SELECT * FROM users WHERE login = ?";
    private static final String USER_INSERT_QUERY = "INSERT INTO users (login, password, organisation,mail,flag) VALUES (?,?,?,?,?)";
    private static final String PRODUCT_SELECT_QUERY = "SELECT * FROM products";
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
    public static boolean signUp(String login, String password,String mail,String orgname,int flag) throws SQLException, InvalidKeySpecException, NoSuchAlgorithmException {
        boolean result = false;
        if(!isUserExist(login)&& !Objects.equals(login, "")&& !Objects.equals(password, "") && !Objects.equals(mail, "")&& !Objects.equals(orgname, "")) {
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16];
            random.nextBytes(salt);
            salt = login.getBytes();
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = factory.generateSecret(spec).getEncoded();
            password = Base64.getEncoder().encodeToString(hash);
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(USER_INSERT_QUERY)) {
                preparedStatement.setString(1, login);
                preparedStatement.setString(2, password);
                preparedStatement.setString(3, orgname);
                preparedStatement.setString(4, mail);
                preparedStatement.setInt(5, flag);
                preparedStatement.execute();
                result = true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else{result=false;}
        return result;
    }
    public static boolean isUserExist(String login) {
        boolean result = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(USER_FIND_QUERY)) {
            preparedStatement.setString(1,login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                preparedStatement.close();
                result = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static List<Product> getProducts(){
        List<Product> result = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PRODUCT_SELECT_QUERY)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                result.add(new Product(
                        resultSet.getString("name"),
                        resultSet.getInt("price"),
                        resultSet.getString("picture"),
                        resultSet.getString("description")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    
}
