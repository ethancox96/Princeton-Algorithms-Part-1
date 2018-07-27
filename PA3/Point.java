/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new SlopeOrder();       // YOUR DEFINITION HERE

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        /* YOUR CODE HERE */
        double deltaY = (double) that.y - (double) this.y;
        double deltaX = (double) that.x - (double) this.x;
        
        if (deltaY == 0) {
            if (deltaX == 0)
                return Double.NEGATIVE_INFINITY;
            
            else 
                return Math.abs(deltaY/deltaX);
        }
        
        else if (deltaX == 0) 
            return Double.POSITIVE_INFINITY;
        
        return deltaY / deltaX;
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        /* YOUR CODE HERE */
        if (this.y == that.y) {
            if (this.x < that.x)
                return -1;
            
            else if (this.x == that.x)
                return 0;
            
            else
                return 1;
        }
        
        else if (this.y < that.y)
            return -1;
        
        else
            return 1;
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }
    
    private class SlopeOrder implements Comparator<Point>
    {
        public int compare(Point q1, Point q2)
        {
            double s1 = Point.this.slopeTo(q1);
            double s2 = Point.this.slopeTo(q2);
            
            if (s1 < s2)
                return -1;
            else if (s1 == s2)
                return 0;
            else
                return 1;
        }
    }

    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
    }
}
