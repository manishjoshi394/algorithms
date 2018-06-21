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
package kd_tree_assignment;


import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import java.util.Queue;
import java.util.TreeSet;

/**
 * The class implements a Brute force inefficient way to store 2D points 
 * and provides sub-optimal implementations of 2D-RangeSearch and nearest 
 * neighbor search
 * @author Manish Joshi
 */
public class PointSET {
    private final TreeSet<Point2D> set;
    public PointSET()
    {
        set = new TreeSet<>();
    }
    
    public boolean isEmpty()
    {
        return set.isEmpty();
    }
    
    public int size()
    {
        return set.size();
    }
    
    public void insert(Point2D p)
    {
        if (p == null)  throw new java.lang.IllegalArgumentException("NULL not required");
        if (!contains(p))
            set.add(p);
    }
    
    public boolean contains(Point2D p)
    {
        if (p == null)  throw new java.lang.IllegalArgumentException("Nulls not allowed");
        return set.contains(p);
    }
    
    public void draw()
    {
        for (Point2D p : set)
            StdDraw.point(p.x(), p.y());
    }
    
    /**
     * Implementation of the 2D range search
     * @param rect
     * @return iterable object containing points that fall in the range
     */
    public Iterable<Point2D> range(RectHV rect) 
    {
        if (rect == null)   throw new java.lang.IllegalArgumentException("Nulls not allowed");
        Queue<Point2D> q = new java.util.LinkedList<>();
        for (Point2D p : set)   
            if (rect.contains(p))
                q.add(p);
        return q;
    }
    
    /**
     * Implementation of the nearest neighbor search
     * @param p The point for which nearest neighbor is required
     * @return 
     */
    public Point2D nearest(Point2D p)
    {
        if (p == null)  throw new java.lang.IllegalArgumentException("Nulls not allowed");
        if (isEmpty())  return null;
        Point2D closest = set.first();
        for (Point2D q : set)   
            if (closest.distanceSquaredTo(p) > q.distanceSquaredTo(p))
                closest = q;
        return closest;
    }
    
    public static void main(String[] args)
    {
        PointSET mySet = new PointSET();
        mySet.insert(new Point2D(0, 0));
        mySet.insert(new Point2D(1, 1));
        mySet.insert(new Point2D(1, 1));
        System.out.println(mySet.size());
        for (Point2D p : mySet.range(new RectHV(.5, .5, 2.5, 2.5)))
            System.out.println(mySet.size());
    }
}
