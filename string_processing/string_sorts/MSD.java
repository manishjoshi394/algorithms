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
 * The class implements recursive MSD radix sort.
 *
 * @author Manish Joshi
 */
public class MSD {
    private static final int R = 256;
    private static String[] aux;
    
    /**
     * Sorts the strings provided as Arguments Lexiographically.
     *
     * @param a the array of strings to be sorted
     */
    public static void sort(String[] a) {
        int N = a.length;
        aux = new String[N];
        sort(a, 0, N - 1, 0);
    }

    private static void sort(String[] a, int lo, int hi, int d) {
        if (lo >= hi) return;
        int[] count = new int[R + 2];
        for (int i = lo; i <= hi; ++i) {
            count[charAt(a[i], d) + 2]++;
        }
        
        for (int r = 0; r < R + 1; ++r) {
            count[r + 1] += count[r];
        }
        
        for (int i = lo; i <= hi; ++i) {
            aux[count[charAt(a[i], d) + 1]++] = a[i];
        }
        
        for (int i = lo; i <= hi; ++i) {
            a[i] = aux[i - lo];
        }
        
        for (int r = 0; r < R; ++r) {
            sort(a, lo + count[r], lo + count[r + 1] - 1, d + 1);
        }
    }
    
    private static int charAt(String s, int d) {
        if (d >= s.length()) return -1;
        return s.charAt(d);
    }
    
    public static void main(String[] args) {
        String[] arr = new String[] {"MAnish", "ANeesh", "Joshi", "C", "A", "B"};
        sort(arr);
        for (String s : arr)
            System.out.println(s);
        MSD s = new MSD();
        System.out.println(s.hashCode());
    }
}
