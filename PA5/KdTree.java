/******************************************************************************
 *  Compilation:  javac KdTree.java
 *  Execution:    java KdTree
 *  Dependencies: StdDraw.java StdRandom.java
 *
 *  Immutable point data type for points in the plane.
 *
 ******************************************************************************/

import java.util.Iterator;

public class KdTree
{
    private Node root;
    private SET<Point2D> set;
    private int size = 0;
    
    private class Node
    {
        Node parent;
        Node leftChild;
        Node rightChild;
        Point2D point;
        int direction;
        int level;
        
        public Node(Node p, Node l, Node r, Point2D s)
        {
            parent = p;
            leftChild = l;
            rightChild = r;
            point = s;
            
            if (parent == null) {
                direction = 0;
                level = 1;
            }
            else if (parent.direction == 0) {
                direction = 1;
                level = parent.level + 1;
            }
            else {
                direction = 0;
                level = parent.level + 1;
            }
        }
        
        public double x()
        {
            return point.x();
        }
        
        public double y()
        {
            return point.y();
        }
    }
    
    public KdTree()
    {
        set = new SET<Point2D>();
    }
    
    public int size()
    {
        return size;
    }
    
    public void insert(Point2D p)
    {
        if (!set.contains(p))
        {
            Node cur = root;
            
            while (cur != null) {
                if (cur.direction == 0) {
                    if (p.x() < root.x()) {
                        Node temp = cur;
                        if (cur.leftChild == null) {
                            cur.leftChild = new Node(cur, null, null, p);
                            cur = cur.leftChild;
                            break;
                        }
                        else {
                            cur = cur.leftChild;
                            cur.parent = temp;
                        }
                    }
                    else {
                        Node temp = cur;
                        if (cur.rightChild == null) {
                            cur.rightChild = new Node(cur, null, null, p);
                            cur = cur.rightChild;
                            break;
                        }
                        else {
                            cur = cur.rightChild;
                            cur.parent = temp;
                        }
                    }
                }
                else {
                    if (p.y() < root.y()) {
                        Node temp = cur;
                        if (cur.leftChild == null) {
                            cur.leftChild = new Node(cur, null, null, p);
                            cur = cur.leftChild;
                            break;
                        }
                        else {
                            cur = cur.leftChild;
                            cur.parent = temp;
                        }
                    }
                    else {
                        Node temp = cur;
                        if (cur.rightChild == null) {
                            cur.rightChild = new Node(cur, null, null, p);
                            cur = cur.rightChild;
                            break;
                        }
                        else {
                            cur = cur.rightChild;
                            cur.parent = temp;
                        }
                    }
                }
            }
            
            if (cur == root) {
                root = new Node(null, null, null, p);
            }
            
            set.add(p);
            
            size++;
        }
    }
    
    public boolean contains(Point2D p)
    {
        return set.contains(p);
    }
    
    public void draw()
    {
        
    }
    
    public Iterable<Point2D> range(RectHV rect)
    {
        Stack<Point2D> points = new Stack<Point2D>();
        return points;
    }
    
    public Point2D nearest(Point2D p)
    {
        int level = root.level;
        Node cur = root;
        
        if (level == 1) {
            return cur.point;
        }
        
        cur = cur.leftChild;
        
    }
    
    public static void main (String[] args)
    {
        KdTree k = new KdTree();
        Point2D p = new Point2D(0.5, 0.5);
        k.insert(p);
        Point2D q = new Point2D(0.4, 0.6);
        k.insert(q);
    }
}




