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
 *This is a Faster version of MergeSort and has a CUTOFF applied on size of array,
 * below which the array is sorted by Insertion sort which actually runs faster for shorter array
 * It also optimizes best case of Merge sort by eliminating the merge operation call for already ordered halves
 * @author manishjoshi394
 */
public class MergeFast {
    /**
     * CUTOFF describes the maximum number of items for which Insertion will be used
     */
    public final static int CUTOFF = 7;
    
    static void sort(Comparable[] arr)  // overloaded for trivial use
    {
        Comparable[] aux = new Comparable[arr.length];
        for (int i = 0; i < arr.length; ++i)
            aux[i] = arr[i];
        sort(arr, aux, 0, arr.length - 1);
    }
    
    public static void sort(Comparable[] arr, Comparable[] aux, int low, int high) 
    {
        if (low < high)
        {  
            int mid = low + (high - low) / 2;
            sort(aux, arr, low, mid);
            sort(aux, arr, mid + 1, high);
            merge(aux, arr, low, mid, high);
        }
    }
    private static void merge(Comparable[] arr, Comparable[] aux, int low, int mid, int high)
    {
        
        int x = low, y = mid + 1;
        for (int i = 0; i < high - low + 1; ++i)
        {
            if (x > mid)
                aux[low + i] = arr[y++];
            else if (y > high)
                aux[low + i] = arr[x++];
            else if (less(arr[y], arr[x]))
                aux[low + i] = arr[y++];
            else aux[low + i] = arr[x++];
        }
    }
    private static boolean less(Comparable a, Comparable b)
    {
        return a.compareTo(b) < 0;
    }

    public static void main(String[] args)
    {
        Integer[] arr = {7, 5, 4, 3, 609, 2, 1, 57, 10, 11, 12, 232};
        MergeFast.sort(arr);
        for (int x : arr)
            System.out.println(x);
    }
}
