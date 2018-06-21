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
package sort.heap;

import java.io.File; // Just used for the Main function where
            // i demonstrated use of the sort to order the files in a directory

/**
 * The class is a typical and classical Heap sort implementation using the sink
 * method similar to that used in the priority queue implementation
 * @see Binary Heap implementation of Priority queue ADT
 * 
 * Two steps are there -
 * 1. Heapify the array in linear time by sinking all the nodes in bottom up manner
 * 2. Sort down the array by popping out roots in a way a priorit queue would do
 * BUT keeping the removed element at the space at the end of the heap that just got 
 * freed up by popping.
 * 
 * BUT ONE CAVEAT is here - 
 * The heap is assumed to have root at 1 but actual supplied array starts at 0
 * So we just make a small conversion of indices in actual data modifier and
 * accessor methods i.e, we just make changes in less and exch functions to make 
 * them adjust the indices by decrementing by 1
 * @see The below code, I hope you will understand.
 * 
 * 
 * @author Manish Joshi
 */
public class Heap {
    public static void sort(Comparable[] arr)
    {
        int N = arr.length;
        for (int i = N / 2; i >= 1; --i)
            sink(arr, i, N);
        while (N > 1)
        {
            exch(arr, 1, N--);
            sink(arr, 1, N);
        }
        
        
    }
    
    private static void sink(Comparable[] arr, int k, int N)
    {
        while (2 * k <= N)
        {
            int j = 2 * k;
            if (j < N && less(arr, j, j + 1))  j++;
            
            if (!less(arr, k, j))  break;
            exch(arr, k, j);
            k = j;
        }
    }
    
    private static boolean less(Comparable[] arr, int u, int v)
    {
        --u; // Fixing the indices
        --v;
        return arr[u].compareTo(arr[v]) < 0;
    }
    
    private static void exch(Comparable[] arr, int a, int b)
    {
        --a; // Index fix
        --b;
        Comparable swap = arr[a];
        arr[a] = arr[b];
        arr[b] = swap;
    }
    
    
    public static void main(String[] args)
    {
        File dir = new File("/proc");
       // Integer[] arr = {6, 6, 6,6 ,6 };
     Integer[] arr = {7, 5, 4 , 3 ,3, 5,  2, 1};
        
        File[] files = dir.listFiles();
        Heap.sort(arr);
        Heap.sort(files);
        
        for (File x : files)
            System.out.println(x);
        for (int x : arr)
            System.out.println(x);
    }
}
