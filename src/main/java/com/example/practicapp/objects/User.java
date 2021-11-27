package com.example.practicapp.objects;

public final class User {
    private static String login;
    private static String password;
    private static Boolean isadmin;
    private static int flag;
    private static int id;

    public int getId() {
        return id;
    }

    public static void setId(int id) {
        User.id = id;
    }
    public static String getLogin() {
        return login;
    }
    public static void setLogin(String login) {
        User.login = login;
    }
    public static String getPassword() {
        return password;
    }
    public static void setPassword(String password) {
        User.password = password;
    }
    public static Boolean isAdmin() {
        return isadmin;
    }
    public static int getFlag() {
        return flag;
    }
}
