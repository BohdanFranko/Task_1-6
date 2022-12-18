package com.epam.validator;

public class SwitcherValidator {
    public boolean validate(String s) {
        return s.length() == 1 && s.charAt(0) >= '0' && s.charAt(0) <= '9';
    }
}
