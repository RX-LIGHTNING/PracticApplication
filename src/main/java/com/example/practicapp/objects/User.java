package com.example.practicapp.objects;

public final class User {
    private static String login;
    private static String password;
    private static Boolean isadmin;
    private static String organization;
    private static String contacts;
    private static int flag;
    private static int id;

    public static String getOrganization() {
        return organization;
    }
    public static void setOrganization(String organization) {
        User.organization = organization;
    }
    public static int getFlag() {
        return flag;
    }
    public static void setFlag(int flag) {
        User.flag = flag;
    }
    public static void setContact(String contacts) {
        User.contacts = contacts;
    }
    public static int getId() {
        return id;
    }
    public static void setIsadmin(Boolean isadmin) {
        User.isadmin = isadmin;
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
    public static void exit(){
        login = "";
        password = "";
        organization ="";
        flag=0;
        id=0;
    }
}
