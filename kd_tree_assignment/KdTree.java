package kd_tree_assignment;


import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

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
 *
 * @author Manish Joshi
 */
public class KdTree {
    private static final boolean X = true;
    private static final boolean Y = false;
    
    private Node root;
    private int n = 0;
    private static class Node
    {
        Point2D p;
        
        Node left;
        Node right;
        
        private RectHV rect;
        public Node(Point2D p, RectHV rect)
        {
            this.p = p;
            this.rect = rect;
        }
    }
    
    public boolean isEmpty()                      // is the set empty? 
    {
        return size() == 0;
    }
    
    public int size()                         // number of points in the set 
    {
        return n;
    }
    
    private int compare(Point2D p1, Point2D p2, boolean orient) // 
    {   // Compares two points based on the orientation provided
        double dx = p1.x() - p2.x();
        double dy = p1.y() - p2.y();
        
        if (orient == X)
        {
            if (dx < 0) return -1;
            else if (dx > 0)    return +1;
            else return 0;
        }
        else    // orientation is Y
        {
            if (dy < 0) return -1;
            else if (dy > 0)    return +1;
            else    return 0;
        }
    }
   
    /*
    private void setRectSnip(RectHV rect,
            RectHV rectLB, RectHV rectRT, Point2D p, boolean orient) 
    {
        
    }
    */
    
    public void insert(Point2D p)              // add the point to the set (if it is not already in the set)
    {
        if (p == null)  throw new java.lang.IllegalArgumentException();
        root = put(root, p, X, new RectHV(0.0, 0.0, 1.0, 1.0));
        ++n;
    }
    
    private Node put(Node x, Point2D p, boolean orientation, RectHV rect)
    {        
        if (x == null)  return new Node(p, rect);
        int cmp = compare(p, x.p, orientation);
        
        RectHV rectLB, rectRT;
        
        
        if (p.equals(x.p))  {
            x.p = p;
            --n;
        }
        else if (cmp < 0)   
        {
            if (x.left == null)
            {
                if (orientation == X)
                {
                    rectLB = new RectHV(rect.xmin(), rect.ymin(), x.p.x(), rect.ymax());
                }

                else // orient is Y
                {
                    rectLB = new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), x.p.y());
                }
            }
            else    rectLB = x.left.rect;
            x.left = put(x.left, p, !orientation, rectLB);
        }
        else if (cmp >= 0)   
        {
            if (x.right == null)
            {
                if (orientation == X)
                {
                    rectRT = new RectHV(x.p.x(), rect.ymin(), rect.xmax(), rect.ymax());
                }

                else // orient is Y
                {
                    rectRT = new RectHV(rect.xmin(), x.p.y(), rect.xmax(), rect.ymax());
                }
            }
            else    rectRT = x.right.rect;
            x.right = put(x.right, p, !orientation, rectRT);
        }
        return x;
    }
    
    public boolean contains(Point2D p)            // does the set contain point p? 
    {
        if (p == null)  throw new java.lang.IllegalArgumentException();
        Node x = root;
        boolean orient = X;
        while (x != null)
        {
            int cmp = compare(p, x.p, orient);
            if (cmp < 0)    x = x.left;
            else if (cmp > 0)   x = x.right;
            else if (p.equals(x.p)) return true;
            else x = x.right;
            orient = !orient;
        }
        return false;
    }
    
    public void draw()                         // draw all points to standard draw 
    {
        inOrder(root, X);
    }
    
    private void inOrder(Node x, boolean orient)
    {
        if (x == null)  return ; // base case of recursion
        inOrder(x.left, !orient);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        StdDraw.point(x.p.x(), x.p.y());
        StdDraw.setPenRadius();
        
        if (orient == X)
        {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(x.p.x(), x.rect.ymin(), x.p.x(), x.rect.ymax());
        }
        else
        {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(x.rect.xmin(), x.p.y(), x.rect.xmax(), x.p.y());
        }
        inOrder(x.right, !orient);
    }
   
    public Iterable<Point2D> range(RectHV rect)             // all points that are inside the rectangle (or on the boundary) 
    {
        if (rect == null)   throw new java.lang.IllegalArgumentException();
        Queue<Point2D> q = new LinkedList<>();
        range(root, rect, q, X); // comparing by X at first
        return q;
    }
    
    private void range(Node x, RectHV rect, Queue<Point2D> q, boolean orient)
    {
        if (x == null)  return;
        if (!rect.intersects(x.rect))   return;
        if (rect.contains(x.p))    q.add(x.p);
        range(x.left, rect, q, orient);
        range(x.right, rect, q, orient);
    }
    
    public Point2D nearest(Point2D p)             // a nearest neighbor in the set to point p; null if the set is empty 
    {
        if (p == null)  throw new java.lang.IllegalArgumentException();
        if (isEmpty())  return null;
        return nearest(root, p, root.p, X);
    }
    
    private Point2D nearest(Node x, Point2D p, Point2D champ, boolean orient)
    {
        if (x == null)  return champ;
        
        // if the closest is closer than the rectangle corresponding to this node 
        // do not go further
        if (champ.distanceSquaredTo(p) < x.rect.distanceSquaredTo(p))   return champ;
        
        // check current node
        if (p.distanceSquaredTo(x.p) < p.distanceSquaredTo(champ))
            champ = x.p;
        
        // compare the query point to current point
        int cmp = compare(p, x.p, orient);
        
        if (p.equals(x.p))  return p;
        else if (cmp < 0)   
        {   // Go towards query point first
            champ = nearest(x.left, p, champ, !orient);
            champ = nearest(x.right, p, champ, !orient);
        }
        else if (cmp >= 0)   
        {
            champ = nearest(x.right, p, champ, !orient);
            champ = nearest(x.left, p, champ, !orient);
        }
        
        return champ;
    }
    
    public static void main(String[] args)       
    {
        KdTree mySet = new KdTree();
        mySet.insert(new Point2D(0.7, 0.2));
        mySet.insert(new Point2D(0.5, 0.4));
        mySet.insert(new Point2D(0.2, 0.3));
        mySet.insert(new Point2D(0.4, 0.7));
        mySet.insert(new Point2D(0.9, 0.6));
        mySet.draw();
        StdDraw.line(mySet.nearest(new Point2D(0, .0)).x(), mySet.nearest(new Point2D(0, .0)).y(), 0, 0);
        for (Point2D p : mySet.range(new RectHV(0.0, 0.0, 1, 0.99)))
            System.out.println(mySet.size());
    }
}
