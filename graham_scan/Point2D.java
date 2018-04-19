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
