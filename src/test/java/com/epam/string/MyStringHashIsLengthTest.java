package com.epam.string;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyStringHashIsLengthTest {
    @Test
    void equals_ReturnsTrue_EqualObjects() {
        MyStringHashIsLength firstString = new MyStringHashIsLength(new String("Hello"));
        MyStringHashIsLength secondString = new MyStringHashIsLength(new String("Hello"));

        assertEquals(firstString, secondString);
    }

    @Test
    void equals_ReturnsFalse_SameHashNotEquals() {
        MyStringHashIsLength firstString = new MyStringHashIsLength(new String("He123"));
        MyStringHashIsLength secondString = new MyStringHashIsLength(new String("Hello"));

        assertEquals(firstString.hashCode(), secondString. hashCode());
        assertNotEquals(firstString, secondString);
    }

    @Test
    void hashCode_ReturnsCorrectHashCode() {
        MyStringHashIsLength myString = new MyStringHashIsLength("Check");

        int actual = myString.hashCode();
        int expected = 5;

        assertEquals(expected, actual);
    }

    @Test
    void hashCode_ReturnsFailedNumber_StringIsNull() {
        MyStringHashIsLength myString = new MyStringHashIsLength(null);

        int actual = myString.hashCode();

        assertEquals(-1, actual);
    }
}