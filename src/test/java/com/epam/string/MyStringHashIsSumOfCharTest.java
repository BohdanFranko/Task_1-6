package com.epam.string;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyStringHashIsSumOfCharTest {
    @Test
    void equals_ReturnsTrue_EqualObjects() {
        MyStringHashIsSumOfChar firstString = new MyStringHashIsSumOfChar(new String("Hello"));
        MyStringHashIsSumOfChar secondString = new MyStringHashIsSumOfChar(new String("Hello"));

        assertEquals(firstString, secondString);
    }

    @Test
    void hashCode_ReturnsCorrectHashCode_LengthBiggerThan4() {
        MyStringHashIsSumOfChar myString = new MyStringHashIsSumOfChar("Check");

        int actual = myString.hashCode();
        int expected = 'C' + 'h' + 'e' + 'c';

        assertEquals(expected, actual);
    }

    @Test
    void hashCode_ReturnsCorrectHashCode_LengthSmallerThan4() {
        MyStringHashIsSumOfChar myString = new MyStringHashIsSumOfChar("Ch");

        int actual = myString.hashCode();
        int expected = 'C' + 'h';

        assertEquals(expected, actual);
    }

    @Test
    void hashCode_ReturnsZero_StringIsNull() {
        MyStringHashIsSumOfChar myString = new MyStringHashIsSumOfChar(null);

        int actual = myString.hashCode();

        assertEquals(-1, actual);
    }
}