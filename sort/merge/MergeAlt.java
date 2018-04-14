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
 *
 * @author manishjoshi394
 */
public class MergeAlt {
    public static void sort(int[] arr, int p, int r)
    {
        if (p < r)
        {
            int q = p + (r - p) / 2;
            sort(arr, p, q);
            sort(arr, q + 1, r);
            merge(arr, p, q, r);
        }
    }
    private static void merge(int[] arr, int p, int q, int r)
    {
        int n1 = q - p + 1;
        int n2 = r - q;
        
        int[] L = new int[n1 + 1];
        int[] R = new int[n2 + 1];
        
        for (int i = 0; i < n1; ++i)
        {
            L[i] = arr[p + i];
        }
        for (int i = 0; i < n2; ++i)
        {
            R[i] = arr[q + 1 + i];
        }
        
        L[n1] = Integer.MAX_VALUE;
        R[n2] = Integer.MAX_VALUE;
        
        int x = 0, y = 0;
        
        for (int i = 0; i < r - p + 1; ++i)
        {
            if (L[x] <= R[y])
                arr[p + i] = L[x++];
            else
                arr[p + i] = R[y++];
        }
    }
    public static void main(String[] args)
    {
        int[] arr = {7, 5, 4, 3, 2, 1, 10};
        MergeAlt.sort(arr, 0, arr.length - 1);
        for (int x : arr)
            System.out.println(x);
    }
}
