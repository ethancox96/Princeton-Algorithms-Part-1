/******************************************************************************
 *  Compilation:  javac PointSET.java
 *  Execution:    java PointSet
 *  Dependencies: StdDraw.java StdRandom.java
 *
 *  Immutable point data type for points in the plane.
 *
 ******************************************************************************/

import java.util.Iterator;

public class PointSET
{
    private SET<Point2D> set;
    private int count;
    
    public PointSET()
    {
        set = new SET<Point2D>();
    }
    
    public int size()
    {
        return count;
    }
    
    public void insert(Point2D p)
    {
        set.add(p);
    }
    
    public boolean contains(Point2D p)
    {
        return set.contains(p);
    }
    
    public void draw()
    {
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(.01);
        for (Point2D p : set)
            p.draw();
    }
    
    public Iterable<Point2D> range(RectHV rect)
    {
        Stack<Point2D> points = new Stack<Point2D>();
        
        for (Point2D p : set) {
            if (rect.contains(p)) {
                points.push(p);
            }
        }
        
        return points;
    }
    
    public Point2D nearest(Point2D p)
    {
        double closestDist = 0;
        Point2D closest = p;
        int count = 0;
        
        for (Point2D that : set) {
            double temp = p.distanceTo(that);
            if (count == 0) {
                closestDist = temp;
                count++;
            }
            if (temp < closestDist) {
                closest = that;
                closestDist = temp;
            }
        }
        
        return closest;
    }
    
    public static void main(String[] args)
    {
        
    }
}




