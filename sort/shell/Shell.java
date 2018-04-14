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
package sort.shell;

import java.io.File;
import sort.selection.Selection;
import sort.insertion.Insertion;
import edu.princeton.cs.algs4.Stopwatch;

/**
 *
 * 
 * to help with analysis and prove the correctness of the algo we use only two methods exchanges and comparisons 
 * the comparable mechanism is encapsulated with 'less' method
 * and we use exch to make swaps and avoid directly modifying array data.
 * 
 * @author manishjoshi394
 * @see coursera course Algorithms part I week 2 Module 2
 */


public class Shell {
    public static void sort(Comparable[] arr)
    {
        int N = arr.length;
        
        // Generate the increment sequence
        // to h-sort the array
        // h sorting involves sorting h interleaved subsequences of the array
        
        
       int h = 1;
        while (h < N / 3)
        {
            h = h * 3 + 1; // 3x + 1 increment sequence , values 1 4 13 40 121
        }

        while (h >= 1)
        {
            for (int i = 0 + h; i < N; ++i)
            {
                int j = i;
                
                while (j >= h && less(arr[j], arr[j - h]))
                {
                    exch(arr, j - h , j);
                    j -= h;
                }
            }
            
            h = h / 3; // next increment
        }
    
    }
    
    private static boolean less(Comparable u, Comparable v)
    {
        return u.compareTo(v) < 0;
    }
    
    private static void exch(Comparable[] arr, int u, int v)
    {
        Comparable var = arr[u];
        arr[u] = arr[v];
        arr[v] = var;
    }
    /**
    *Test client. @param args the command line arguments
    */
    public static void main(String[] args)
    {
        
        File dir = new File("/proc")  ;
        
        File[] files = dir.listFiles();
        Stopwatch watch = new Stopwatch();
        Shell.sort(files);
        System.out.println(watch.elapsedTime());
        files = dir.listFiles();
        watch = new Stopwatch();
        Insertion.sort(files);
        System.out.println(watch.elapsedTime());
        files = dir.listFiles();
        watch = new Stopwatch();
        Selection.sort(files);
        System.out.println(watch.elapsedTime());
        /*for (File x : files)
        {
            System.out.println(x + " ");
        }
        Integer[] arr = {2, 4, 2, 1, 13243, 435 , 324, 34 , 45,5 ,56 ,5 ,65,  5 , 45, 455, 4,7 ,676,767,7 ,67  ,67, 45 ,655, 46  };
       Shell.sort(arr);
       for(int a : arr)
       {
            System.out.println(a + " ");
       }*/
    }
}
