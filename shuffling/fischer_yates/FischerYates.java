/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shuffling.fischer_yates;

import edu.princeton.cs.algs4.StdRandom;

/**
 *Also known as Knuth Shuffle after Don Knuth
 * 
 * A simple linear time complexity algorithm which is very intuitive actually
 * @see Coursera course Algorithms Part 1 - Week 2- Module 2- Shuffling 
 * @author manishjoshi394
 */
public class FischerYates {
    
    
    private static void shuffle(Comparable[] arr)
    {
        for (int i = 0; i < arr.length; ++i)
        {
            int rand = StdRandom.uniform(i, arr.length);
            exch(arr, rand, i);
        }
    }
    
    private static void exch(Comparable[] arr, int a, int b)
    {
        Comparable swap = arr[a];
        arr[a] = arr[b];
        arr[b] = swap;
    }
    
    public static void main(String[] args)
    {
        Integer[] arr = { 1, 2, 3, 4, 5, 7, 8, 9, 10, 11, 12, 13, 14 };
        FischerYates.shuffle(arr);
        for (int x : arr)
            System.out.println(x);
    }
}



