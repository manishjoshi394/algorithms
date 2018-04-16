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

/**
 * This class gives a bottom up version of MergeSort which is neater than its top-down recursive counterpart. 
 * Top down version is still faster than this version because of caching reasons in recursion.
 * This method gives a really compact and elegant code for implementing the merge sort
 * @author Manish Joshi
 */
public class BottomUpMerge {
  
    private static Comparable[] aux;
    
    private static void merge(Comparable[] arr, int low, int mid, int high)
    {
        for (int i = low; i <= high; ++i)
            aux[i] = arr[i];
        
        int x = low, y = mid + 1;
        for (int i = low; i <= high; ++i)
        {
            if (x > mid)                    arr[i] = aux[y++];
            else if (y > high)              arr[i] = aux[x++];
            else if (less(aux[y], aux[x]))  arr[i] = aux[y++];
            else                            arr[i] = aux[x++];
        }
    }
    
    public static void sort(Comparable[] arr)
    {
        int N = arr.length;
        aux = new Comparable[N];
        for (int sz = 1; sz < N; sz += sz)
        {
            for (int low = 0; low < N - sz; low += sz + sz)
                merge(arr, low, low + sz - 1, Math.min(low + sz + sz - 1, N - 1));
        }
    }
    
    private static boolean less(Comparable a, Comparable b)
    {
        return a.compareTo(b) < 0;
    }
    
    /**
     * Test client
     * @param args The command line arguments 
     */
    public static void main(String[] args)
    {
        Integer[] arr = {15042018, 7, 5, 4, 3, 609, 2, 1, 57, 10, 11, 12, 232};
        BottomUpMerge.sort(arr);
        for (int x : arr)
            System.out.println(x);
    }
}
