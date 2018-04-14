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
package sort.merge;

import sort.insertion.Insertion;

/**
 *
 * @author manishjoshi394
 */
public class Merge {
    
    static void sort(Comparable[] arr)  // overloaded for trivial use
    {
        Comparable[] aux = new Comparable[arr.length];
        sort(arr, aux, 0, arr.length - 1);
    }
    
    public static void sort(Comparable[] arr, Comparable[] aux, int low, int high) 
    {
        if (low < high)
        {  
            int mid = low + (high - low) / 2;
            sort(arr, aux, low, mid);
            sort(arr, aux, mid + 1, high);
            merge(arr, aux, low, mid, high);
        }
    }
    private static void merge(Comparable[] arr, Comparable[] aux, int low, int mid, int high)
    {
        for (int i = low; i <= high; ++i)
        {
            aux[i] = arr[i];
        }
        int x = low, y = mid + 1;
        for (int i = 0; i < high - low + 1; ++i)
        {
            if (x > mid)
                arr[low + i] = aux[y++];
            else if (y > high)
                arr[low + i] = aux[x++];
            else if (less(aux[y], aux[x]))
                arr[low + i] = aux[y++];
            else arr[low + i] = aux[x++];
        }
    }
    private static boolean less(Comparable a, Comparable b)
    {
        return a.compareTo(b) < 0;
    }

        public static void main(String[] args)
    {
        Integer[] arr = {7, 5, 4, 3, 2, 1, 10, 232};
        Merge.sort(arr);
        for (int x : arr)
            System.out.println(x);
    }
}
