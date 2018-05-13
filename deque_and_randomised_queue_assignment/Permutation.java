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
package deque_and_randomised_queue_assignment;



import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * This class is just a part of the assignment i did on Coursera (See ReadMe).
 * This in not useful to you if you just seek the code of Deque and Randomized queues.
 * @author manishjoshi394
 */
public class Permutation {
    public static void main(String[] args)
    {
        int k = Integer.parseInt(args[0]);
        
        RandomizedQueue<String> storage = new RandomizedQueue<>();
        
        while (!StdIn.isEmpty())
        {
            storage.enqueue(StdIn.readString());
            
        }
        
        while (k > 0)
        {
            StdOut.println(storage.dequeue());
            --k;
        }
        
    }
}
