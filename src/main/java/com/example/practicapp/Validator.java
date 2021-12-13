package com.example.practicapp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private static final String EMAIL_PATTERN = "^(.+)@(\\S+)$";

    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    public static boolean isValid(final String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public static boolean isNumber(final String string) {
        final String NUMBERS_PATTERN = "[0-9]+";

        final Pattern pattern = Pattern.compile(NUMBERS_PATTERN);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }
}
