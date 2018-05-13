package collinear_points_assignment;


import edu.princeton.cs.algs4.Merge;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;
import java.util.Scanner;


/**
 * This class can tell you whether four points lie on a line or not
 * It does not support more than 4 points check, This class is just a naive
 * implementation is Biquadratic order of growth.
 * VERY VERY POOR PERFORMANCE.
 * @author Manish Joshi
 */
public class BruteCollinearPoints {
    private LineSegment[] segments;
    private int n;
    public BruteCollinearPoints(Point[] points)
    {
        
        if (points == null)
            throw new java.lang.IllegalArgumentException("Null not allowed");
        if (hasNull(points))
            throw new java.lang.IllegalArgumentException("null points");
        if (hasDuplicates(points))
            throw new java.lang.IllegalArgumentException("Duplcates not allowed");
        
        n = 0;
        segments = new LineSegment[points.length * points.length];
        Point[] pointsCopy = Arrays.copyOf(points, points.length);
        
        Merge.sort(pointsCopy);
        for (int i = 0; i < pointsCopy.length; ++i)
            for (int j = i + 1; j < pointsCopy.length; ++j)
                for (int k = j + 1; k < pointsCopy.length; ++k)
                    for (int m = k + 1; m < pointsCopy.length; ++m)
                    {
                        Point p, q, r, s;
                        p = pointsCopy[i];
                        q = pointsCopy[j];
                        r = pointsCopy[k];
                        s = pointsCopy[m];
                        
                        if (p == null || q == null || r == null || s == null)
                            throw new java.lang.IllegalArgumentException("null point in array of points");
                        
                         
                        if (p.slopeTo(q) == q.slopeTo(r) && q.slopeTo(r) == r.slopeTo(s))
                        {
                            segments[n++] = new LineSegment(p, s); 
                            // System.out.println("bh");
                        }
                    }
        
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
        // In in = new In(args[0]);
        Scanner in = new Scanner("8\n10000      0\n" +
"0  10000\n" +
"3000   7000\n" +
"7000   3000\n" +
" 20000  21000\n" +
"3000   4000\n" +
"                         14000  15000\n" +
"                          12323 4545");
        int n = in.nextInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            points[i] = new Point(x, y);
        }
        // points[n] = null;

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
            StdDraw.show();
        }
        
    }
        
}
