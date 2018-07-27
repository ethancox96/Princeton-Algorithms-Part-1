/*
* Brute.java
*/

import java.util.Arrays;

public class Brute
{
    public static void main(String[] args)
    {
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);
        StdDraw.setPenRadius(0.01);  
        
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Point[] pArray = new Point[N];
        
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            Point p = new Point(x, y);
            p.draw();
            pArray[i] = p;
        }
        
        StdDraw.show(0);
        
        Arrays.sort(pArray);
        
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                for (int k = j + 1; k < N; k++) {
                    for (int m = k + 1; m < N; m++) {
                        Point r = pArray[i];
                        Point s = pArray[j];
                        Point t = pArray[k];
                        Point v = pArray[m];
                        
                        double slope1 = r.slopeTo(pArray[j]);
                        double slope2 = r.slopeTo(pArray[k]);
                        double slope3 = r.slopeTo(pArray[m]);
                        
                        if ((slope1 == slope2) && (slope1 == slope3)) {
                            StdDraw.setPenRadius();
                            r.drawTo(v);
                            //s.drawTo(v);
                            //t.drawTo(v);
                            System.out.printf("%s -> ", r.toString());
                            System.out.printf("%s -> ", s.toString());
                            System.out.printf("%s -> ", t.toString());
                            System.out.printf("%s\n", v.toString());
                            StdDraw.show(0);
                        }
                        
                    }
                }
            }
        }
    }
}

