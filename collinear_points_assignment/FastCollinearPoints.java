package collinear_points_assignment;


import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;
import java.util.Scanner;

/**
 * This class finds all collinear points in a given set of points
 * and supports any number of collinear points,
 * The performance has an upper bound of n^3lgN.
 * Though the performance looks bad but it is actually fair considering 
 * the computational complexity of the problem.
 * 
 * The method segments() returns the largest segment formed by a single set of 
 * collinear points among the given set of arbitrary points.
 * 
 * Constructor requires an array of Points to be checked.
 * 
 * P.S. Considerably daunting code. <i>In Hindi : Waat lag gyi</i>
 * @author Manish Joshi
 */

public class FastCollinearPoints 
{
    private LineSegment[] segments;
    private int n;
    public FastCollinearPoints(Point[] points)  
    {
        
        if (points == null)
            throw new java.lang.IllegalArgumentException("Null not allowed");
        
        
        if (hasNull(points))
            throw new java.lang.IllegalArgumentException("null points");
        
        
        if (hasDuplicates(points))
            throw new java.lang.IllegalArgumentException("Duplicate points");
        
        segments = new LineSegment[points.length * points.length];
        n = 0;
       
        Point[] pointsCopy = Arrays.copyOf(points, points.length);
        
       
        for (int i = 0; i < points.length; ++i)
        {
            Arrays.sort(pointsCopy);
            Arrays.sort(pointsCopy, pointsCopy[i].slopeOrder());
            
            for (int first = 1, last = 2; last < points.length; ++last)
            {
                while (last < pointsCopy.length
                        && pointsCopy[0].slopeTo(pointsCopy[first]) 
                        == pointsCopy[0].slopeTo(pointsCopy[last]))
                {
                    last++;
                }
                
                if (last - first >= 3 
                        && pointsCopy[0].compareTo(pointsCopy[first]) < 0)// && pointsCopy[0].compareTo())
                {
                    segments[n++] = new LineSegment(pointsCopy[0], pointsCopy[last - 1]);
                }
                
                first = last;
            }
        }
        
       /* segments = new LineSegment[points.length * points.length];
        n = 0;
        
      
           
        for (int i = 0; i < points.length; ++i)
        {
            if (points[i] == null)  throw new java.lang.IllegalArgumentException("Null not allowed");
        
            Point[] pointsCopy = Arrays.copyOf(points, points.length);
            Arrays.sort(pointsCopy);
            Arrays.sort(pointsCopy, pointsCopy[i].slopeOrder());
            
            for (int last = 2, first = 1; last < pointsCopy.length; last++)
            {   
                // if (pointsCopy[origin].compareTo(pointsCopy[origin + 1]) < 0)
                
                while (last < pointsCopy.length && 
                        pointsCopy[0].slopeTo(pointsCopy[first]) == 
                        pointsCopy[0].slopeTo(pointsCopy[last]))
                {
                    last++;
                }
                if (last - first >= 3 && pointsCopy[0].compareTo(pointsCopy[first]) < 0)
                {
                        segments[n++] = new LineSegment(pointsCopy[0], pointsCopy[last - 1]);
                    
                }
                first = last;
            }
        }

        int i = 0;
        LineSegment[] temp = new LineSegment[n];
        for (LineSegment x: segments)
        {
            if (x != null)
                temp[i] = segments[i++];
        }
        segments = temp; 
        /* segments = new LineSegment[points.length * points.length];
        int n = 0;
        while () */
    }
    public int numberOfSegments()        
    {
        return n;

    }
    public LineSegment[] segments()                
    {
        LineSegment[] temp = new LineSegment[n];
        int i = 0;
        for (LineSegment x : segments)
            if (x != null)
                temp[i] = segments[i++];
        return temp;
    }
    
    private boolean hasDuplicates(Point[] points)
    {
        for (int i = 0; i < points.length; ++i)
            for (int j = i + 1; j < points.length; ++j)
            if (points[i].compareTo(points[j]) == 0)
                return true;
        return false;
    }
    private boolean hasNull(Point[] points)
    {
        for (Point x : points)
            if (x == null)
                return true;
        return false;
    }
    public static void main(String[] args)
    {        
     // read the n points from a file
        //In in = new In(args[0]);
        Scanner in = new Scanner("8\n10000      0\n" +
"0  10000\n" +
"3000   7000\n" +
"7000   3000\n" +
" 20000  21000\n" +
"3000   4000\n" +
"                         14000  15000\n" +
"                          12323 4545");
        int n = in.nextInt();
        Point[] points = new Point[n + 1];
        points[n] = null;
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        System.out.println(collinear.numberOfSegments());
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
            StdDraw.show();
        }
        
    }
}

