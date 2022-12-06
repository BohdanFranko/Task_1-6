package com.epam.string;

import java.util.Objects;

/**
 * Class MyString contains class String and overrides methods equals and hashCode
 */
public class MyStringHashIsLength {
    private String underString;

    public String getUnderString() {
        return underString;
    }

    public void setUnderString(String underString) {
        this.underString = underString;
    }

    public MyStringHashIsLength(String underString) {
        setUnderString(underString);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyStringHashIsLength myString = (MyStringHashIsLength) o;
        return Objects.equals(underString, myString.underString);
    }

    /**
     * Returns the hash code value for this object. The hash code is the result of following calculation:
     *
     * @return hash code value that is calculated by a sum of String length and its 4 first characters
     */
    @Override
    public int hashCode() {
        if (underString != null) {
            return underString.length();
        } else return -1;
    }

    @Override
    public String toString() {
        return underString;
    }
}

