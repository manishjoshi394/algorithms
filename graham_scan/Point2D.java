/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graham_scan;

import java.util.Comparator;

/**
 *
 * @author Manish Joshi
 */
public class Point2D // immutable point data type.. Immutable means the point once constructed , cant be changed
{
   private final double x;
   private final double y;
   
   public final Comparator BY_POLAR_ORDER = new PolarOrder();
   public final static Comparator Y_ORDER = new YOrder();
   
   public Point2D(double x, double y) {
       this.x = x;
       this.y = y;
   }
   
   public double getX()
   {
       return x;
   }
   public double getY()
   {
       return y;
   }
   
   public static int ccw(Point2D u, Point2D v, Point2D w)
   {
       /**
        * The expression is simplified expansion of vector product of two sides of the triangle formed,
        * It gives the twice the signed area of the triangle
        * @see the Determinant or cross product in Coordinate geometry
        */
       double area2 = (w.y - v.y) * (v.x - u.x) - (v.y - u.y) * (w.x - v.x); 
       if (area2 < 0)       return -1; // Clockwise
       else if (area2 > 0)  return +1; // Anti- clockwise or counter clockwise
       else                 return 0; // Collinear
       
   }
   public static class YOrder implements Comparator<Point2D>
   {
        @Override
        public int compare(Point2D o1, Point2D o2) {
            
        if (o1.y - o2.y == 0) return 0;
            return (o1.y - o2.y > 0) ? +1 : -1;
        }   
   }
   public class PolarOrder implements Comparator<Point2D>
   {
       @Override
       public int compare(Point2D p1, Point2D p2)    
       {
           double dy1 = p1.y - y;
           double dy2 = p2.y - y;
           if (dy1 == 0 && dy2 == 0)  {return 0;}
           else if (dy1 >= 0 && dy2 < 0)  return -1;
           else if (dy2 >= 0 && dy1 < 0) return +1;
           else return -ccw(Point2D.this, p1, p2);
       }
       
   }
}
