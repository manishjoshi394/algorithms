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
import java.util.Stack;
import java.util.Arrays;
/**
 * The program uses Graham scan algorithms to find the convex hull of given points in a 2D coordinate plane
 * Graham scan is an efficient algorithms for finding Convex hull of a set 2D plane points
 * However, the following implementation is not fit for robust use as it may show unexpected behaviour in redundant cases.
 * @author Manish Joshi
 */
public class ConvexHull {
    
    static Stack<Point2D> hull = new Stack<>();
    public static void print(Point2D[] p)
    {
        Arrays.sort(p, Point2D.Y_ORDER); // p[0] now contains point with lowest y coordinate
        Arrays.sort(p, p[0].BY_POLAR_ORDER);
        
        hull.push(p[0]);
        hull.push(p[1]);
        
        Point2D temp = hull.pop();
        int i = 2;
        while (i < p.length)
        {
            while (Point2D.ccw(hull.peek(), temp, p[i]) <= 0)
            {
                temp = hull.pop();
            }
            hull.push(temp);
            temp = p[i];
        
            i++;
        }
        hull.push(temp);
        
/*        for (int i = 2; i < p.length; ++i)
        {
            Point2D top = hull.pop();
            while (Point2D.ccw(hull.peek(), top, p[i]) <= 0)
            {
                top = hull.pop();
            }
            hull.push(top);
            hull.push(p[i]);
        }
  */      
        for (Point2D x : hull)
        {
            System.out.println(x.getX() + " " + x.getY());
        }
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Point2D[] p = new Point2D[6];
        p[0] = new Point2D(0, 1);
        p[1] = new Point2D(0, 2);
        p[2] = new Point2D(0, 0);
        p[3] = new Point2D(0, 1.5);
        p[4] = new Point2D(2.5, 0.5);
        p[5] = new Point2D(3, 2);
        ConvexHull.print(p);
    }
}


