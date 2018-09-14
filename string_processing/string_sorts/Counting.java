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

/**
 * The class implements a Stable version of the Counting sort. The sort method
 * takes two arrays - one for the integer keys; the other for the associated
 * Objects to the keys. After sorting, the Object data array is also arranged
 * according to the corresponding sorted integer keys.
 * <p>
 * Running time : O(n + R) in time and extra space. where R is the maximum value
 * among integer keys.
 *
 * @author Manish Joshi
 */
public class Counting {

    /**
     * Sorts the arrays {@code arr} and {@code data} in order of the integer
     * keys appearing in array {@code arr}. The counting sort is stable, so the
     * entries in the data array are sorted with stability as the associated
     * integer keys get sorted along.
     *
     * @param arr the integer keys
     * @param data associated data to keys
     * @param R the Radix
     */
    public static void sort(int[] arr, Object[] data, int R) {
        // defensive code
        if (R < 0) {
            throw new IllegalArgumentException("Radix R cannot be negative");
        }
        if (data != null && arr.length != data.length) {
            throw new IllegalArgumentException("The data array and integer array have different sizes, should be one-one.");
        }

        int N = arr.length;         // size of the array to be sorted

        // compute frequency of each key and put on the position key + 1 in the count array
        int[] count = new int[R + 1];
        for (int i = 0; i < N; ++i) {
            count[arr[i] + 1]++;
        }

        // compute cumulative frequency in the array by storing the running sum
        for (int r = 0; r < R; ++r) {
            count[r + 1] += count[r];
        }

        // put data in aux and auxData in sorted manner
        int[] aux = new int[N];
        Object[] auxData = new Object[N];
        for (int i = 0; i < N; ++i) {
            aux[count[arr[i]]] = arr[i];
            auxData[count[arr[i]]++] = data[i];
        }

        // modify the original array to sort the original
        for (int i = 0; i < N; ++i) {
            arr[i] = aux[i];
            data[i] = auxData[i];
        }
    }

    // for unit testing of the class
    public static void main(String[] args) {
        int[] arr = new int[]{4, 4, 3, 2, 2, 1};
        String[] data = new String[]{"Manish", "Joshi", "is", "gonna", "rock", "hard"};
        sort(arr, data, 5);
        for (String x : data) {
            System.out.println(x);
        }
    }
}
