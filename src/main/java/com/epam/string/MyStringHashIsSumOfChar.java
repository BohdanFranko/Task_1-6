package com.epam.string;

import java.util.Objects;

/**
 * Class MyString contains class String and overrides methods equals and hashCode
 */
public class MyStringHashIsSumOfChar {
    private String underString;

    public String getUnderString() {
        return underString;
    }

    public void setUnderString(String underString) {
        this.underString = underString;
    }

    public MyStringHashIsSumOfChar(String underString) {
        setUnderString(underString);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyStringHashIsSumOfChar myString = (MyStringHashIsSumOfChar) o;
        return Objects.equals(underString, myString.getUnderString());
    }

    /**
     * Returns the hash code value for this object. The hash code is the result of following calculation:
     *
     * @return hash code value that is calculated by a sum of String length and its 4 first characters
     */
    @Override
    public int hashCode() {
        int numOfCharacters = 4;
        if(underString == null) {
            return -1;
        }
        int hash = 0;
        for (int i = 0; i < Math.min(underString.length(), numOfCharacters); i++) {
            hash += underString.charAt(i);
        }
        return hash;
    }

    @Override
    public String toString() {
        return underString;
    }
}

