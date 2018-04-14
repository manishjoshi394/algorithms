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
package sort.insertion;

import java.io.File;

/**
 *
 * @author manishjoshi394
 */
public class Insertion {
    public static void sort(Comparable[] arr) // overloaded for trivial use
    {
        sort(arr, 0, arr.length - 1);
    }
    public static void sort(Comparable[] arr, int a, int b)
    {
        for (int i = a + 1; i <= b; ++i)
        {
            Comparable key = arr[i];
            int j = i - 1;
            while (j >= a && less(key, arr[j]))
            {
                exch(arr, j, j + 1); // arr[j + 1] = arr[j]
                j--;
            }
              // arr[j + 1] = key;
        }
    }
    private static boolean less(Comparable u, Comparable v)
    {
        return (u.compareTo(v) < 0);
    }
    private static void exch(Comparable[] arr, int x, int y)
    {
        Comparable swap = arr[x];
        arr[x] = arr[y];
        arr[y] = swap;
    }
    
    public static void main(String[] args)
           
    {
       
       /*
       Integer[] arr = {2, 4, 2, 1, 13243, 435 , 324, 34 , 45,5 ,56 ,5 ,65,  5 , };
       Selection.sort(arr);
       for(int a : arr)
       {
            System.out.println(a + " ");
       }
       */
       
       File dir = new File("/home/manishjoshi394/Downloads");
       File[] files = dir.listFiles();
       Insertion.sort(files);
       for (File x : files)
       {
           System.out.println(x);
       }
   }
}
