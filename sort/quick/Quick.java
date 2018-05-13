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
package sort.quick;

import edu.princeton.cs.algs4.Stopwatch;
import java.io.File;
import sort.insertion.Insertion;
import sort.merge.MergeFast;
import sort.selection.Selection;
import sort.shell.Shell;

/**
 * Classic QuickSort with Tweak of MedianOf3 partitioning.
 * @author Manish Joshi
 */

public class Quick {
    public final static int CUTOFF = 10; 
    
    public static void sort(Comparable[] arr)
    {
        FisherYates.shuffle(arr);
        sort(arr, 0, arr.length - 1);
        Insertion.sort(arr, 0, arr.length - 1);    //Pick up later for insertion sort on small subarrays 
    }
    
    public static void sort(Comparable[] arr, int low, int high)
    {
        if (high - low + 1 <= CUTOFF)   return ;                // Do nothing for small arrays during recursion
        
        int m = median(arr, low, low + (high - low) / 2, high);
        exch(arr, m, high);
        
        int pivot = partition(arr, low, high);
        sort(arr, low, pivot - 1);
        sort(arr, pivot + 1, high);

    }
    
    public static int partition(Comparable[] arr, int low, int high)
    {
        int i = low;
        int j;
        for (j = low; j < high; ++j)
            if (less(arr[j], arr[high]))
                    exch(arr, j, i++);
        exch(arr, high, i);
        return i;
    }
    
    private static int median(Comparable[] arr, int a, int b, int c)
    {
        Comparable[] temp = {arr[a], arr[b], arr[c]};
        Insertion.sort(temp);
        if (temp[1].compareTo(arr[a]) == 0)  return a;
        else if (temp[1].compareTo(arr[b]) == 0) return b;
        else return c;
    }
    
    public static void main(String[] args)
    {
        
        Integer[] arr = {7, 5, 4, 4, 3, 609, 2, 1, 57, 10, 11, 12, 232};
        Quick.sort(arr);
        for (int x : arr)
           System.out.println(x);
        
         
        File dir = new File("/proc")  ;
        
        File[] files = dir.listFiles();
        Stopwatch watch = new Stopwatch();
        MergeFast.sort(files);
        System.out.println(watch.elapsedTime());
        files = dir.listFiles();
        watch = new Stopwatch();
        Quick.sort(files, 0, files.length - 1);
        //for (File x : files)
          //  System.out.println(x);
        System.out.println(watch.elapsedTime());
        files = dir.listFiles();
        watch = new Stopwatch();
        Selection.sort(files);
        System.out.println(watch.elapsedTime());
        
    }

    private static boolean less(Comparable u, Comparable v) {
        return u.compareTo(v) < 0;
    }
    
    private static void exch(Comparable[] arr, int a, int b)
    {
        Comparable swap = arr[a];
        arr[a] = arr[b];
        arr[b] = swap;
    }
}