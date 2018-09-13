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

import edu.princeton.cs.algs4.StdOut;
import java.lang.String;

/**
 *
 * @author Manish Joshi
 */
public class Alphabet {

    /**
     * The binary alphabet {0, 1}.
     */
    public static final Alphabet BINARY = new Alphabet("01");
    
    /**
     * The BASE-64 alphabet (with 64 characters).
     */
    public static final Alphabet BASE64 = new Alphabet("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/");

    /**
     * The Decimal system alphabet.
     */
    public static final Alphabet DECIMAL = new Alphabet("0123456789");
    
    private char[] alphabet;         // the chars in the alphabet
    private int[] indices;          // indices
    private final int R;             // the radix of the alphabet

    /**
     * Creates a new alphabet set from chars in String s.
     *
     * @param s a String
     */
    public Alphabet(String s) {

        // check for duplicate chars and invalidate them
        boolean[] unicode = new boolean[Character.MAX_VALUE];
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (unicode[c]) {
                throw new IllegalArgumentException("Illegal alphabet: repeated character '" + c + "'");
            } else {
                unicode[c] = true;
            }
        }

        alphabet = s.toCharArray();
        indices = new int[Character.MAX_VALUE];
        R = alphabet.length;
        for (int i = 0; i < alphabet.length; ++i) {
            indices[i] = -1;
        }

        for (int i = 0; i < alphabet.length; ++i) {
            indices[alphabet[i]] = i;
        }
    }

    // intializes new Alphabet with given radix using corresponding UNICODE chars
    private Alphabet(int radix) {
        this.R = radix;
        alphabet = new char[R];
        indices = new int[R];

        for (int i = 0; i < R; ++i) {
            alphabet[i] = (char) i;
        }
        for (int i = 0; i < R; ++i) {
            indices[i] = i;
        }
    }

    /**
     * Initializes a new Alphabet with 256 as the radix.
     */
    public Alphabet() {
        this(256);
    }

    /**
     * Returns the corresponding alphabet char for given index.
     *
     * @param index the given index
     * @return the corresponding alphabet char for given index
     */
    public char toChar(int index) {
        if (index < 0 || index >= R) {
            throw new java.util.NoSuchElementException("index is out of the bound :" + index);
        }

        return alphabet[index];
    }

    /**
     * Convert the given {@code char c} to an index between 0 and R - 1, where R
     * is total number of distinct chars in the alphabet.
     *
     * @param c
     * @return an Index corresponding to the given char
     */
    public int toIndex(char c) {
        if (c > indices.length || indices[c] == -1) {
            throw new IllegalArgumentException("Character " + c + " is not a part of the Alphabet");
        }
        return indices[c];
    }

    /**
     * Returns true if c is valid char in the Alphabet set.
     *
     * @param c the given char c
     * @return true if c is valid char in the Alphabet set
     */
    public boolean contains(char c) {
        return indices[c] != -1;
    }

    /**
     * Returns the radix(number of distinct characters) of the alphabet.
     *
     * @return the radix(number of distinct characters) of the alphabet
     */
    public int R() {
        return R;
    }

    /**
     * Returns log(base 2) of R, which is equal to number of bits to represent R
     * in binary form.
     *
     * @return log(base 2) of R
     */
    public int lgR() {
        int lgR = 0;
        for (int t = R - 1; t >= 1; t /= 2) {
            lgR++;
        }
        return lgR;
    }

    /**
     * Converts the {@code String s} to a sequence of corresponding base R
     * integers.
     *
     * @param s the given String
     * @return an array of base R integers corresponding to the given String
     */
    public int[] toIndices(String s) {
        int[] out = new int[s.length()];
        for (int i = 0; i < s.length(); ++i) {
            out[i] = toIndex(s.charAt(i));
        }
        return out;
    }

    /**
     * Converts the {@code indices} array to a String of corresponding
     * alphabets.
     *
     * @param indices array containing index form of chars
     * @return a String corresponding to given set of indices
     */
    String toChars(int[] indices) {
        StringBuilder s = new StringBuilder(indices.length);
        for (int i = 0; i < indices.length; ++i) {
            s.append(toChar(indices[i]));
        }
        return s.toString();
    }

    /**
     * For unit testing of the {@code Alphabet} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        int[]  encoded1 = Alphabet.BASE64.toIndices("ManishJoshiReallyGoodBoy");
        String decoded1 = Alphabet.BASE64.toChars(encoded1);
        StdOut.println(decoded1);
 
    //    int[]  encoded2 = Alphabet.DNA.toIndices("AACGAACGGTTTACCCCG");
    //    String decoded2 = Alphabet.DNA.toChars(encoded2);
       //StdOut.println(decoded2);

        int[]  encoded3 = Alphabet.DECIMAL.toIndices("01234567890123456789");
        String decoded3 = Alphabet.DECIMAL.toChars(encoded3);
        StdOut.println(decoded3);
    }
}
