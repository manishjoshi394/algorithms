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
package sort.selection;
import java.io.File;

/**
 *
 * @author manishjoshi394
 */
public class Selection {
   public static void sort(Comparable[] arr)
   {
       for (int i = 0; i < arr.length - 1; ++i)
       {
           int pos = i;
           for (int j = i + 1; j < arr.length; ++j)
           {
               if (less(arr[j], arr[pos]))
               {
                   pos = j;
               }
           }
           exch(arr, pos, i);
       }
   }
   private static boolean less(Comparable a, Comparable b)
   {
       return (a.compareTo(b) < 0);
   }
   private static void exch(Comparable[] arr, int a, int b)
   {
       // swap the a and b indices
       Comparable temp =  arr[a];
       arr[a] = arr[b];
       arr[b] = temp;
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
       Selection.sort(files);
       for (File x : files)
       {
           System.out.println(x);
       }
   }
}
