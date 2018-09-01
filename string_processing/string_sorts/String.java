/* 
 * Copyright 2018 Manish Joshi.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package string_processing.string_sorts;

import java.util.Arrays;

/**
 * This class is an effort to replicate the functionalities of the standard
 * String class of Java. This class provides basic features of the string like
 * immutability and subset formation in constant order of time.
 *
 * @author Manish Joshi
 */
public final class String implements Comparable<String> {

    private final char[] value;       // the array of characters
    private final int offset;         // index of first char in the array
    private final int length;         // length of the string
    private final int hash;           // cache of hashCode()

    /**
     * Returns the {@code String} equivalent of the {@code java.lang.String}
     * provided as argument.
     *
     * @param that
     * @return the {@code String} equivalent of the {@code java.lang.String}
     * provided as argument.
     */
    public static String parseString(java.lang.String that) {
        return new String(that);
    }

    /**
     * Constructs a String object with the contents of given character array.
     *
     * @param value the source character array
     */
    public String(char[] value) {
        this.offset = 0;
        this.length = value.length;
        this.value = value;
        this.hash = this.hashCode();
    }

    /**
     * Creates a {@code String} with the contents of {@code java.lang.String}.
     *
     * @param that the {@code java.lang.String} object
     */
    public String(java.lang.String that) {
        this.offset = 0;
        this.length = that.length();
        this.value = new char[this.length()];
        for (int i = 0; i < this.length(); ++i) {
            this.value[i] = that.charAt(i);
        }
        this.hash = hashCode();
    }

    // constructs String with given char array contents and specified offset and length
    private String(int offset, int length, char[] value) {
        this.offset = offset;
        this.value = value;
        this.length = length;
        this.hash = this.hashCode();
    }

    /**
     * Returns the number of characters in the string.
     *
     * @return the number of characters in the string
     */
    public int length() {
        return length;
    }

    /**
     * Implementation of the Comparable interface.
     *
     * @param that the object to be compared
     * @return
     */
    @Override
    public int compareTo(String that) {
        if (this.length() < that.length()) {
            return -1;
        } else if (this.length() > that.length()) {
            return +1;
        } else {
            for (int i = 0; i < that.length(); ++i) {
                if (this.charAt(i) < that.charAt(i)) {
                    return -1;
                } else if (this.charAt(i) > that.charAt(i)) {
                    return +1;
                }
            }
        }
        return 0;
    }

    /**
     * Returns a hashCode for the object. This method is supported for benefit
     * of hash tables that might try to use this String class as their keys.
     * Hashcode is calulated using Modular hashing.
     *
     * @return a hashCode for the object
     */
    @Override
    public int hashCode() {
        int h = 0;
        int R = 13;
        int M = this.length();
        for (int i = 0; i < M; ++i) {
            h = (h * R + this.charAt(i)) % M;
        }
        return h;
    }

    /**
     * Standard Recipe for implementing equals() method in Java.
     * <p>
     * 1. Compare the references
     * <p>
     * 2. Null checks
     * <p>
     * 3. check class type
     * <p>
     * 4. cast the object
     * <p>
     * 5. compare instance variables
     *
     * @param obj the reference object with which to compare.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != java.lang.String.class && getClass() != obj.getClass()) {
            return false;
        }
        final String other;
        if (obj.getClass() == java.lang.String.class) {
            other = String.parseString((java.lang.String) obj);
        } else {
            other = (String) obj;
        }

        if (this.offset != other.offset) {
            return false;
        }
        if (this.length != other.length) {
            return false;
        }
        if (!Arrays.equals(this.value, other.value)) {
            return false;
        }
        return true;
    }

    /**
     * Returns {@code java.lang.String} representation of this object. This
     * method is supported for seamless conversion between Java's String type
     * and this String class.
     *
     * @return {@code java.lang.String} representation of this object
     */
    @Override
    public java.lang.String toString() {
        char[] val = new char[this.length()];
        for (int i = 0; i < this.length(); ++i) {
            val[i] = this.charAt(i);
        }
        return new java.lang.String(val);
    }

    /**
     * Returns the character at the position i in the string.
     *
     * @param i the query position
     * @return the character at the position i in the string
     */
    public char charAt(int i) {
        if (i < this.length) {
            return value[offset + i];
        }
        throw new java.util.NoSuchElementException("index " + i + " crosses the bounds");
    }

    /**
     * Returns a substring of the given string in range [from, to) extracted
     * from provided value array.
     *
     * @param from starting position inclusive
     * @param to end position exclusive
     * @return a substring of the given string in range [from, to) extracted
     * from provided value array
     */
    public String substring(int from, int to) {
        return new String(offset + from, to - from, this.value);
    }

    /**
     * Returns the {@code String} formed by appending {@code that} string to
     * {@code this} string.
     *
     * @param that the other string to be appended
     * @return the {@code String}
     */
    public String append(String that) {
        char[] newVal = new char[this.length() + that.length()];
        for (int i = 0; i < this.length(); ++i) {
            newVal[i] = this.charAt(i);
        }
        for (int i = 0; i < that.length(); ++i) {
            newVal[this.length + i] = that.charAt(i);
        }
        //System.out.println(newVal);
        return new String(newVal);
    }

    /**
     * Returns the {@code String} formed by appending the contents of the
     * specified {@code java.lang.String} object to this String.
     *
     * @param that a {@code java.lang.String} object
     * @return * Returns the {@code String} formed by appending the contents of
     * the specified {@code java.lang.String} object to this String
     */
    public String append(java.lang.String that) {
        return this.append(String.parseString(that));
    }

    // for informal unit testing of the class
    public static void main(java.lang.String[] args) {
        String s = new String("Manish");
        s = s.append(" Joshi");

        System.out.println(s.substring(0, 6));
        System.out.println(s.equals("Manish Joshi"));
    }
}
