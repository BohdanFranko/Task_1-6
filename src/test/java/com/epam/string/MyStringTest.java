package com.epam.string;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyStringTest {
    @Test
    void equals_ReturnsTrue_EqualObjects() {
        MyString firstString = new MyString(new String("Hello"));
        MyString secondString = new MyString(new String("Hello"));

        assertEquals(firstString, secondString);
    }

    @Test
    void hashCode_ReturnsCorrectHashCode_LengthBiggerThan4() {
        MyString myString = new MyString("Check");

        int actual = myString.hashCode();
        int expected = myString.getUnderString().length() + 'C' + 'h' + 'e' + 'c';

        assertEquals(expected, actual);
    }

    @Test
    void hashCode_ReturnsCorrectHashCode_LengthSmallerThan4() {
        MyString myString = new MyString("Ch");

        int actual = myString.hashCode();
        int expected = myString.getUnderString().length() + 'C' + 'h';

        assertEquals(expected, actual);
    }

    @Test
    void hashCode_ReturnsZero_StringIsNull() {
        MyString myString = new MyString(null);

        int actual = myString.hashCode();

        assertEquals(0, actual);
    }
}