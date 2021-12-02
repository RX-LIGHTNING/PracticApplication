package com.example.practicapp;

import com.example.practicapp.objects.*;

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
    private static final String PRODUCT_INSERT_QUERY = "INSERT INTO products (name,description,price,picture) VALUES(?,?,?,?)";
    private static final String ORDERS_SELECT_QUERY = "SELECT * FROM orders WHERE organization = ?";
    private static final String PROVIDER_REQUEST_INSERT_QUERY = "INSERT INTO providersingredient (provider_id,ingredient_id,price) VALUES(?,?,?)";
    private static final String PROVIDER_SELECT_QUERY = "SELECT price, provider_id, ingredient_id,name, ing_id, id, organisation\n" +
            "FROM ingredients\n" +
            "INNER JOIN providersingredient ON ing_id = ingredient_id\n" +
            "INNER JOIN users ON provider_id = id where users.flag>2";
    private static final String PROVIDER_SELECT_BY_ID_QUERY = "SELECT price, provider_id, ingredient_id,name, ing_id, id, organisation\n" +
            "FROM ingredients\n" +
            " INNER JOIN providersingredient ON ing_id = ingredient_id\n" +
            " INNER JOIN users ON provider_id = id where users.flag>2 and providersingredient.provider_id = ?";
    private static final String ORDER_INSERT_QUERY = "INSERT INTO orders (organization, quantity, product, date, contacts) VALUES (?,?,?,?,?)";
    private static final String PROVIDER_REQUEST_DELETE_QUERY = "DELETE FROM providersingredient WHERE provider_id = ? AND ingredient_id = ?";
    private static Connection connection;
    private static final String INGREDIENT_SELECT_QUERY = "SELECT * FROM ingredients";

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
                User.setFlag(resultSet.getInt("flag"));
                User.setOrganization(resultSet.getString("organisation"));
                User.setIsadmin(resultSet.getBoolean("isadmin"));
                User.setContact(resultSet.getString("mail"));
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
                        resultSet.getBytes("picture"),
                        resultSet.getString("description"),
                        resultSet.getInt("id")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static List<Ingredient> getIngredients(){
        List<Ingredient> result = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INGREDIENT_SELECT_QUERY)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                result.add(new Ingredient(
                                //resultSet.getString("provider"),
                        resultSet.getString("name"),
                        resultSet.getInt("ing_id")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static List<Provider> getProviders(){
        List<Provider> result = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PROVIDER_SELECT_QUERY)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                result.add( new Provider(
                        resultSet.getInt("provider_id"),
                        resultSet.getInt("ingredient_id"),
                        resultSet.getString("organisation"),
                        resultSet.getInt("price"),
                        resultSet.getString("name")
                       ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }
    public static List<Provider> getProvidersById(int id){
        List<Provider> result = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PROVIDER_SELECT_BY_ID_QUERY)) {
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                result.add( new Provider(
                        resultSet.getInt("provider_id"),
                        resultSet.getInt("ingredient_id"),
                        resultSet.getString("organisation"),
                        resultSet.getInt("price"),
                        resultSet.getString("name")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }
    public static boolean OrderInsert(int quantity,String prod, Date date,String contact) {
        boolean result = false;
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(ORDER_INSERT_QUERY)) {
                preparedStatement.setString(1, User.getOrganization());
                preparedStatement.setInt(2, quantity);
                preparedStatement.setString(3, prod);
                preparedStatement.setDate(4, date);
                preparedStatement.setString(5,contact);
                preparedStatement.execute();
                result = true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return result;
    }

    public static List<Order> getOrders() {
        List<Order> result = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ORDERS_SELECT_QUERY)) {
            preparedStatement.setString(1,User.getOrganization());
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                result.add(new Order(
                        resultSet.getInt("id"),
                        resultSet.getString("organization"),
                        resultSet.getInt("quantity"),
                        resultSet.getString("product"),
                        resultSet.getDate("date"),
                        resultSet.getString("contacts"),
                        resultSet.getInt("status")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static boolean ProductInsert(String name,int price, String description,byte[] file) {
        boolean result = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PRODUCT_INSERT_QUERY)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setInt(3, price);
            preparedStatement.setBytes(4, file);
            preparedStatement.execute();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static boolean InsertIngredientPrice(int ing_id, int price) {
            boolean result = false;
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(PROVIDER_REQUEST_INSERT_QUERY)) {
                preparedStatement.setInt(1, User.getId());
                preparedStatement.setInt(2, ing_id);
                preparedStatement.setInt(3, price);
                preparedStatement.execute();
                result = true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return result;
    }
    public static boolean ProviderRequestCancel(int provid,int ingid) {
        boolean result = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PROVIDER_REQUEST_DELETE_QUERY)) {
            preparedStatement.setInt(1, provid);
            preparedStatement.setInt(2, ingid);
            preparedStatement.execute();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
