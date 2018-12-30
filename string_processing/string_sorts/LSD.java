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

import java.lang.String;
import java.util.Arrays;

/**
 * The {@code LSD} class provides static Methods to sort 32-bit Integers and
 * fixed length strings in Linear time (multiplied by length of String). (The
 * length upto which the strings are sorted is same and can be chosen by the
 * method caller)
 * <p>
 * The class uses LSD Radix sort as its core mechanism. Overall the methods are
 * 2-3x faster than Arrays.sort().
 * <p>
 * In Big Oh notation, running time is - {@code O(W(N + R))}
 *
 * @author Manish Joshi
 */
public class LSD {

    /**
     * Sorts the strings in the given array upto the length of minimum length
     * string.
     *
     * @param a the string array to be sorted
     */
    public static void sort(String[] a) {
        int minLength = Integer.MAX_VALUE;
        for (int i = 0; i < a.length; ++i) {
            minLength = Math.min(minLength, a[i].length());
        }
        System.out.println(minLength);
        sort(a, minLength);
    }

    /**
     * Sorts the strings in the given array using first W characters of the
     * Strings,
     *
     * @param a The String array to be sorted
     * @param W upto W characters from the beginning the strings will be sorted
     */
    public static void sort(String[] a, int W) {
        for (int i = 0; i < a.length; ++i) {
            if (a[i].length() < W) {
                throw new IllegalArgumentException("String at index " + i + " has length smaller than W, which is NOT allowed.");
            }
        }

        int N = a.length;
        int R = 256;
        String[] aux = new String[N];

        for (int pos = W - 1; pos >= 0; pos--) {
            int count[] = new int[R + 1];

            // compute frequency counts            
            for (int i = 0; i < N; ++i) {
                count[a[i].charAt(pos) + 1]++;
            }

            // build cumulative frequency 
            for (int r = 0; r < R; ++r) {
                count[r + 1] += count[r];
            }

            // distribute the data in aux array while sorting it
            for (int i = 0; i < N; ++i) {
                aux[count[a[i].charAt(pos)]++] = a[i];
            }
            
            // copy back into the original array
            for (int i = 0; i < N; ++i) {
                a[i] = aux[i];
            }
        }
    }

    /**
     * Sorts the 32-bit integer in the linear time {@code O(N)} using LSD radix
     * sort.
     * <p>
     * <b>Implementation Note:</b> 32-bit integer is divided into 4 Bytes and
     * then sorted with Counting sort in 4 passes.
     *
     * @param a The integer array to be sorted
     */
    public static void sort(int[] a) {
        final int BITS = 32;        // each int is 32-bits
        final int BITS_PER_BYTE = 8;
        final int R = 1 << BITS_PER_BYTE;       // each byte can take 2^8 = 256 different values 
        final int W = BITS / BITS_PER_BYTE;     // 4 bytes 
        final int MASK = R - 1;         // 0xFF
        
        int N = a.length;
        int[] aux = new int[N];
        
        for (int pos = 0; pos < W; ++pos) {
                
            // compute frequency counts
            int[] count = new int[R + 1];
            for (int i = 0; i < N; ++i) {
                int d = (a[i] >> BITS_PER_BYTE * pos) & MASK;
                count[d + 1]++;
            }
            
            // compute cumulates
            for (int r = 0; r < R; ++r) {
                count[r + 1] += count[r];
            } 
            
            // for most significant byte, sign bit has to be taken in consideration
            // that's why 0x80-0xFF must come before 0x00-0x7F
            if (pos == W - 1) {
                int shift1 = count[R] - count[R / 2];
                int shift2 = count[R / 2];
                for (int r = 0; r < R / 2; ++r) {
                    count[r] += shift1;
                }
                for (int r = R / 2; r < R; ++r) {
                    count[r] -= shift2;
                }
            }
            
            // distribute the data
            for (int i = 0; i < N; ++i) {
                int d = (a[i] >> BITS_PER_BYTE * pos) & MASK;
                aux[count[d]++] = a[i] ;
            }
            
            // copy back into the original array
            for (int i = 0; i < N; ++i) {
                a[i] = aux[i];
            }
        }
    } 

    // for unit testing of the class
    public static void main(String[] args) {
        String[] a = {"Manish", "Joshi", "isa", "ofcourse", "Azb", "Aza", "Really", "Good", "Boy", "Badass"};
        int[] arr = {3, -2 , 1, 10 , 20, 0, -1, -100};
        LSD.sort(arr);
        for (int x : arr) {
            System.out.println(x);
        }
    }

}
