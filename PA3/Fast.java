/*
* Fast.java
*/

import java.util.Arrays;

public class Fast
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
        Point[] newArray = pArray.clone();
        int count = 0;
        StdDraw.setPenRadius();
        
        for (int i = 0; i < newArray.length; i++) {
            Arrays.sort(newArray, i, newArray.length, newArray[i].SLOPE_ORDER);
            int j = i+1;
            if (j < newArray.length) {
                Point[] a = new Point[newArray.length];
                a[0] = newArray[i];
                do {
                    double s1 = newArray[i].slopeTo(newArray[j]);
                    double s2 = newArray[i].slopeTo(newArray[j-1]);
                    if (s1 == s2){
                        while (s1 == s2) {
                            if (j < newArray.length) {
                                count++;
                                a[count] = newArray[j-1];
                                s1 = newArray[i].slopeTo(newArray[j]);
                                s2 = newArray[i].slopeTo(newArray[j-1]);
                                j++;
                            }
                            else if (j == newArray.length) {
                                a[++count] = newArray[j-1];
                                break;
                            }
                            if (i > 0) {
                                double s3 = newArray[i].slopeTo(newArray[i-1]);
                                if (s2 == s3)
                                    break;
                            }
                        }
                        int broke = 0;
                        if (count >= 3) {
                            Arrays.sort(a, 0, count + 1);
                            for (int f = i; f >= 0; f--) {
                                double s4 = newArray[i].slopeTo(newArray[f]);
                                if (s4 == s2) {
                                    broke = 1;
                                    break;
                                }
                            }
                            if (broke != 1) {
                                a[count].drawTo(a[0]);
                                StdDraw.show(0);
                                for (int k = 0; k < count; k++) {
                                    System.out.printf("%s -> ", a[k].toString());
                                }
                                System.out.printf("%s", a[count].toString());
                                System.out.println();
                                count = 0;
                            }
                            j--;
                        }
                    }
                    count = 0;
                    j++;
                }
                while (j < newArray.length);
            }









//            for (int j = i; j < newArray.length; j++) {
//                if (j < 1) {
//                    count++;
//                } 
//                else {
//                    if (j == newArray.length - 1) {
//                        if (count >= 3) {
//                            Point[] a = new Point[count+1];
//                            int c = count + 1;
//                            for (int b = 0; b < c; b++) {
//                                a[b] = newArray[j-count];
//                                count--;
//                            }
//                            Arrays.sort(a);
//                            a[0].drawTo(a[a.length-1]);
//                            StdDraw.show(0);
//                            for (int k = j; k >= i; k--) {
//                                System.out.printf("%s", newArray[k].toString());
//                            }
//                            System.out.println();
//                        }
//                        count = 0;
//                    }
//                    else {
//                        Point[] a = new Point[newArray.length];
//                        double s1 = newArray[i].slopeTo(newArray[j]);
//                        double s2 = newArray[i].slopeTo(newArray[j-1]);
//                        if (s1 == Double.NEGATIVE_INFINITY || s2 == Double.NEGATIVE_INFINITY) {
//                            count++;
//                        }
//                        else if (s1 == s2) {
//                            count++;
//                        }
//                        else {
//                            if (count >= 3) {
//                                newArray[i].drawTo(newArray[j-1]);
//                                StdDraw.show(0);
//                                for (int k = j-1; k >= i; k--) {
//                                    System.out.printf("%s", newArray[k].toString());
//                                }
//                                System.out.println();
//                                count = 0;
//                            }
//                        }
//                    }
//                }
//            }
        }
    }
}





