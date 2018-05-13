package sort.quick;


import java.io.File;
import sort.insertion.Insertion;
import sort.quick.FisherYates;

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

/**
 * QuickSort implementation with three way partitioning 
 * @author Manish Joshi
 */
public class QuickFast {
    static final int CUTOFF = 10;
    public static void sort(Comparable[] arr)
    {
        FisherYates.shuffle(arr);
        sort(arr, 0, arr.length - 1);
    }
    
    public static void sort(Comparable[] arr, int low, int high)
    {
        if (high - low + 1 <= CUTOFF)
        {
            Insertion.sort(arr);
            return ;
        }
        int lt = low;
        int ht = high;
        
        int i = low ;
        
        Comparable v = arr[low];
        
        while (i < ht)
        {
            if (less(arr[i], v))
                exch(arr, i++, lt++);
            else if (less(v, arr[i]))
                exch(arr, i, ht--);
            else ++i;
        }
        
        sort(arr, low, lt - 1);
        sort(arr, ht + 1, high);
    }
    
    
    private static boolean less(Comparable u, Comparable v)
    {
        return u.compareTo(v) < 0;
    }
    
    private static void exch(Comparable[] arr, int i, int j)
    {
        Comparable swap = arr[i];
        arr[i] = arr[j];
        arr[j] = swap;
    }
    public static void main(String[] args)
    {
        File dir = new File("/proc");
       // Integer[] arr = {6, 6, 6,6 ,6 };
     Integer[] arr = {7, 5, 4 , 3 ,3, 5,  2, 1};
        
        File[] files = dir.listFiles();
        QuickFast.sort(arr, 0, arr.length - 1);
        QuickFast.sort(files);
        
        for (File x : files)
            System.out.println(x);
        for (int x : arr)
            System.out.println(x);
    }
}
