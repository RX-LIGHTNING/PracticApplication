package com.example.practicapp;

import com.example.practicapp.objects.*;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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
    private final static String jdbcURL = "jdbc:postgresql://localhost:5432/Vkid";
    private final static String username = "postgres";
    private final static String password = "root";
    private static final String USER_SELECT_QUERY = "SELECT * FROM users WHERE login = ? AND password = ?";
    private static final String USER_FIND_QUERY = "SELECT * FROM users WHERE login = ?";
    private static final String USER_INSERT_QUERY = "INSERT INTO users (login, password, organisation,mail,flag) VALUES (?,?,?,?,?)";
    private static final String PRODUCT_SELECT_QUERY = "SELECT * FROM products";
    private static final String PRODUCT_UPDATE_QUERY = "UPDATE products set name = ?, description = ?, price = ?, picture = ?, recipe = ?, quantity = ? WHERE id = ?";
    private static final String PRODUCT_INSERT_QUERY = "INSERT INTO products (name,description,price,picture,recipe,quantity) VALUES(?,?,?,?,?,?)";
    private static final String ORDERS_SELECT_QUERY = "SELECT * FROM orders WHERE organization = ?";
    private static final String ALL_ORDERS_SELECT_QUERY = "SELECT * FROM orders";
    private static final String PROVIDER_REQUEST_INSERT_QUERY = "INSERT INTO providersingredient (provider_id,ingredient_id,price) VALUES(?,?,?)";
    private static final String PROVIDER_SELECT_QUERY = "SELECT status, price, provider_id, ingredient_id,name, ing_id, id, organisation\n" +
            "FROM ingredients\n" +
            "INNER JOIN providersingredient ON ing_id = ingredient_id\n" +
            "INNER JOIN users ON provider_id = id where users.flag>2";
    private static final String PROVIDER_SELECT_BY_ID_QUERY = "SELECT status, price, provider_id, ingredient_id,name, ing_id, id, organisation\n" +
            "FROM ingredients\n" +
            " INNER JOIN providersingredient ON ing_id = ingredient_id\n" +
            " INNER JOIN users ON provider_id = id where users.flag>2 and providersingredient.provider_id = ?";
    private static final String ORDER_UPDATE_QUERY = "UPDATE orders set status = ? WHERE id = ?";
    private static final String ORDER_INSERT_QUERY = "INSERT INTO orders (organization, quantity, product, contacts) VALUES (?,?,?,?)";
    private static final String PROVIDER_REQUEST_DELETE_QUERY = "DELETE FROM providersingredient WHERE provider_id = ? AND ingredient_id = ?";
    private static Connection connection;
    private static final String INGREDIENT_SELECT_QUERY = "SELECT * FROM ingredients";
    private static final String RECIPE_SELECT_QUERY = "SELECT ingredients.ing_id,recipes.name recipename, ingredients.name ingredientname, quantity, recipes.rec_id\n" +
            "FROM ingredients\n" +
            "INNER JOIN ingredientstorecipes ON ingredients.ing_id = ingredientstorecipes.ing_id\n" +
            "INNER JOIN recipes ON recipes.rec_id = ingredientstorecipes.rec_id where recipes.rec_id = ?";
    private static final String RECIPES_SELECT_QUERY = "SELECT * from recipes";
    private static final String RECIPE_UPDATE_QUERY = "UPDATE ingredientstorecipes set quantity = ? where rec_id = ? and ing_id = ?";
    private static final String RECIPE_ING_INSERT_QUERY = "INSERT INTO ingredientstorecipes(quantity,rec_id,ing_id) VALUES(?,?,?)";
    private static final String RECIPE_NAME_UPDATE_QUERY = "UPDATE recipes set name = ? where rec_id = ?";
    private static final String RECIPE_INSERT_QUERY = "INSERT INTO recipes(name) VALUES(?)";
    private static final String RECIPE_DELETE_QUERY = "DELETE FROM recipes WHERE rec_id=?;";
    private static final String INGREDIENT_INSERT_QUERY = "INSERT INTO ingredients(name) VALUES(?)";
    private static final String INGREDIENT_UPDATE_QUERY = "UPDATE ingredients set name = ? WHERE ing_id = ?";
    private static final String INGREDIENT_DELETE_QUERY = "DELETE FROM ingredients WHERE ing_id = ?";
    private static final String PROVIDER_STATUS_CHANGE = "UPDATE providersingredient set status = ? where ingredient_id = ?";
    private static final String PROVIDER_INGREDIENT_STATUS_CHANGE = "UPDATE providersingredient set status = ? where ingredient_id = ? and provider_id = ?";
    private static final String INDIRECT_COSTS_SELECT_QUERY= "SELECT * FROM indirectcosts\n" +
            "WHERE EXTRACT(MONTH FROM month) = EXTRACT(MONTH FROM Current_timestamp) \n" +
            "AND EXTRACT(YEAR FROM month) = EXTRACT(YEAR FROM Current_timestamp) \n";
    private static final String PRODUCT_LEFT_ORDERS_QUERY ="SELECT quantity FROM orders WHERE product = ? AND status != -1";
    private static final String PRODUCT_LEFT_QUERY ="SELECT quantity FROM products WHERE name = ?";
    private static final String PRODUCT_DELETE_QUERY = "DELETE FROM products WHERE id = ?";
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
    public static void productDelete(int id) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PRODUCT_DELETE_QUERY)) {
            preparedStatement.setInt(1,id);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
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
                        resultSet.getInt("id"),
                        resultSet.getInt("recipe"),
                        resultSet.getInt("quantity")
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
                        resultSet.getString("name"),
                        resultSet.getBoolean("status")
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
                        resultSet.getString("name"),
                        resultSet.getBoolean("status")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }
    public static boolean OrderInsert(int quantity,String prod,String contact) {
        boolean result = false;
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(ORDER_INSERT_QUERY)) {
                preparedStatement.setString(1, User.getOrganization());
                preparedStatement.setInt(2, quantity);
                preparedStatement.setString(3, prod);
                preparedStatement.setString(4,contact);
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
    public static List<Order> getAllOrders() {
        List<Order> result = new ArrayList<>();
        if(User.isAdmin()) {
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(ALL_ORDERS_SELECT_QUERY)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
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
        }
        return result;
    }
    public static boolean ProductInsert(String name, int price, String description, File temp, int recipe, int quantity) throws IOException {
        boolean result = false;
        byte[] file = Files.readAllBytes(temp.toPath());
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PRODUCT_INSERT_QUERY)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setInt(3, price);
            preparedStatement.setBytes(4, file);
            preparedStatement.setInt(5, recipe);
            preparedStatement.setInt(6, quantity);
            preparedStatement.execute();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean ProductUpdate(String name, int price, String description, byte[] file, int recipe, int id, int quantity) throws IOException {
        boolean result = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PRODUCT_UPDATE_QUERY)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setInt(3, price);
            preparedStatement.setBytes(4, file);
            preparedStatement.setInt(5, recipe);
            preparedStatement.setInt(6, quantity);
            preparedStatement.setInt(7, id);
            preparedStatement.executeUpdate();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static void OrderCancel(int id, int status) throws IOException {
        boolean result = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ORDER_UPDATE_QUERY)) {
            preparedStatement.setInt(1, status);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static List<Recipe> getRecipes() {
        List<Recipe> result= new ArrayList<Recipe>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(RECIPES_SELECT_QUERY)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                result.add(new Recipe(resultSet.getInt("rec_id"),
                        resultSet.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static List<Ingredient> getRecipeIngredients(int id) {
        List<Ingredient> result= new ArrayList<Ingredient>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(RECIPE_SELECT_QUERY)) {
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                result.add(new Ingredient(
                        resultSet.getString("ingredientname"),
                        resultSet.getInt("ing_id"),
                        resultSet.getInt("quantity")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static boolean updateRecipe(int rec_id, int ing_id, int quantity, String name){
        boolean result = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(RECIPE_UPDATE_QUERY)

        ) {
            preparedStatement.setInt(1,quantity);
            preparedStatement.setInt(2,rec_id);
            preparedStatement.setInt(3,ing_id);
            if(preparedStatement.executeUpdate()>0){

            }
            else{
                PreparedStatement preparedStatement2 = connection.prepareStatement(RECIPE_ING_INSERT_QUERY);
                preparedStatement2.setInt(1,quantity);
                preparedStatement2.setInt(2,rec_id);
                preparedStatement2.setInt(3,ing_id);
                preparedStatement2.execute();
                result = true;
            }
            PreparedStatement preparedStatement3 = connection.prepareStatement(RECIPE_NAME_UPDATE_QUERY);
            preparedStatement3.setString(1,name);
            preparedStatement3.setInt(2,rec_id);
            preparedStatement3.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static boolean insertRecipe(String name){
        boolean result = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(RECIPE_INSERT_QUERY)

        ) {
            preparedStatement.setString(1,name);
            preparedStatement.execute();
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static boolean deleteRecipe(int rec_id){
        boolean result = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(RECIPE_DELETE_QUERY)

        ) {
            preparedStatement.setInt(1,rec_id);
            preparedStatement.execute();
            return true;
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

    public static void insertIngredient(String updatedstring) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INGREDIENT_INSERT_QUERY)) {
            preparedStatement.setString(1, updatedstring);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void updateIngredient(int selecteditem,String updatedstring) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INGREDIENT_UPDATE_QUERY)) {
            preparedStatement.setString(1, updatedstring);
            preparedStatement.setInt(2, selecteditem);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void deleteIngredient(int selecteditem) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INGREDIENT_DELETE_QUERY)) {
            preparedStatement.setInt(1, selecteditem);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void updateProviderStatus(int ing_id,int provider_id) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PROVIDER_STATUS_CHANGE)) {
            preparedStatement.setBoolean(1,false);
            preparedStatement.setInt(2,ing_id);
            preparedStatement.executeUpdate();
            PreparedStatement preparedStatement2 = connection.prepareStatement(PROVIDER_INGREDIENT_STATUS_CHANGE);
            preparedStatement2.setBoolean(1,true);
            preparedStatement2.setInt(2,ing_id);
            preparedStatement2.setInt(3,provider_id);
            preparedStatement2.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static int getIndirectCost() {
        int sum = 0;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INDIRECT_COSTS_SELECT_QUERY)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                sum+= resultSet.getInt("cost");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sum;
    }
    public static double OrderesProduct(String name){
        double sum = 0;
        double sum2 = 0;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PRODUCT_LEFT_ORDERS_QUERY)) {
            preparedStatement.setString(1,name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                sum+= resultSet.getInt("quantity");
            }
            PreparedStatement preparedStatement2 = connection.prepareStatement(PRODUCT_LEFT_QUERY);
            preparedStatement2.setString(1,name);
            resultSet = preparedStatement2.executeQuery();
            while(resultSet.next()){
                sum2+= resultSet.getInt("quantity");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ((sum)/(sum2/100))/100;
    }
    public static int leftProduct(String name){
        int sum = 0;
        int sum2 = 0;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PRODUCT_LEFT_ORDERS_QUERY)) {
            preparedStatement.setString(1,name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                sum+= resultSet.getInt("quantity");
            }
            PreparedStatement preparedStatement2 = connection.prepareStatement(PRODUCT_LEFT_QUERY);
            preparedStatement2.setString(1,name);
            resultSet = preparedStatement2.executeQuery();
            while(resultSet.next()){
                sum2+= resultSet.getInt("quantity");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sum2-sum;
    }
}
