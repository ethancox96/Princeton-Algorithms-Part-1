/*
*  Percolation.java
*/

public class Percolation {
   private boolean[][] grid;
   private int[] bottomOpen;
   private int count = 0;
   private int rowSize;
   private WeightedQuickUnionUF uf;
   public Percolation(int N) {
      if (N <= 0) throw new IllegalArgumentException("N <= 0");
      rowSize = N;
      grid = new boolean[rowSize][rowSize];
      uf = new WeightedQuickUnionUF(rowSize*rowSize +1);
      bottomOpen = new int[rowSize];
      int i, j;
      for (i = 0; i < N; i++) {
         for (j = 0; j < N; j++) {
           grid[i][j] = false;
        }
      }
   }

   public void open(int i, int j) {
       if (validate(i, j)) {
           i -= 1;
           j -= 1;
           if (!grid[i][j]) {
               grid[i][j] = true;
           }
           if (rowSize == 1) {
               uf.union(gridPosition(i, j), rowSize*rowSize);
               bottomOpen[count] = gridPosition(i, j);
               count++;
           }
           else {
               if (i == 0) {
                   uf.union(gridPosition(i, j), rowSize*rowSize);
                   if (j == 0) {
                       if (isOpen(i+1, j+2)) {
                           uf.union(gridPosition(i, j), gridPosition(i, (j+1)));
                       }
                       if (isOpen(i+2, j+1)) {
                           uf.union(gridPosition(i, j), gridPosition((i+1), j));
                       }
                   }
                   else if (j == (rowSize-1)) {
                       if (isOpen(i+1, j)) {
                           uf.union(gridPosition(i, j), gridPosition(i, (j-1)));
                       }
                       if (isOpen(i+2, j+1)) {
                           uf.union(gridPosition(i, j), gridPosition((i+1), j));
                       }
                   }
                   else {
                       if (isOpen(i+1, j)) {
                           uf.union(gridPosition(i, j), gridPosition(i, (j-1)));
                       }
                       if (isOpen(i+2, j+1)) {
                           uf.union(gridPosition(i, j), gridPosition((i+1), j));
                       }
                       if (isOpen(i+1, j+2)) {
                           uf.union(gridPosition(i, j), gridPosition(i, (j+1)));
                       }
                   }
               }
               else if (i == (rowSize-1)) {
                   bottomOpen[count] = gridPosition(i, j);
                   count++;
                   if (j == 0) { 
                       if (isOpen(i, j+1)) {
                           uf.union(gridPosition(i, j), gridPosition((i-1), j));
                       }
                       if (isOpen(i+1, j+2)) {
                           uf.union(gridPosition(i, j), gridPosition(i, (j+1)));
                       }
                   }
                   else if (j == (rowSize-1)) {
                       if (isOpen(i, j+1)) {
                           uf.union(gridPosition(i, j), gridPosition((i-1), j));
                       }
                       if (isOpen(i+1, j)) {
                           uf.union(gridPosition(i, j), gridPosition(i, (j-1)));
                       }
                   }
                   else {
                       if (isOpen(i, j+1)) {
                           uf.union(gridPosition(i, j), gridPosition((i-1), j));
                       }
                       if (isOpen(i+1, j)) {
                           uf.union(gridPosition(i, j), gridPosition(i, (j-1)));
                       }
                       if (isOpen(i+1, j+2)) {
                           uf.union(gridPosition(i, j), gridPosition(i, (j+1)));
                       }
                   }
               }
               else {
                   if (j == 0) {
                       if (isOpen(i, j+1)) {
                           uf.union(gridPosition(i, j), gridPosition((i-1), j));
                       }
                       if (isOpen(i+2, j+1)) {
                           uf.union(gridPosition(i, j), gridPosition((i+1), j));
                       }
                       if (isOpen(i+1, j+2)) {
                           uf.union(gridPosition(i, j), gridPosition(i, (j+1)));
                       }
                   }
                   else if (j == (rowSize-1)) {
                       if (isOpen(i, j+1)) {
                           uf.union(gridPosition(i, j), gridPosition((i-1), j));
                       }
                       if (isOpen(i+2, j+1)) {
                           uf.union(gridPosition(i, j), gridPosition((i+1), j));
                       }
                       if (isOpen(i+1, j)) {
                           uf.union(gridPosition(i, j), gridPosition(i, (j-1)));
                       }
                   }
                   else {
                       if (isOpen(i, j+1)) {
                           uf.union(gridPosition(i, j), gridPosition((i-1), j));
                       }
                       if (isOpen(i+2, j+1)) {
                           uf.union(gridPosition(i, j), gridPosition((i+1), j));
                       }
                       if (isOpen(i+1, j+2)) {
                           uf.union(gridPosition(i, j), gridPosition(i, (j+1)));
                       }
                       if (isOpen(i+1, j)) {
                           uf.union(gridPosition(i, j), gridPosition(i, (j-1)));
                       }
                   }
               }
           }
       }
   }

   public boolean isOpen(int i, int j) {
      if (validate(i, j)) {
          i--;
          j--;
          return grid[i][j];
      }
      return grid[i][j];
   }

   public boolean isFull(int i, int j) {
      if (validate(i, j)) {
          i--;
          j--;
          if (isOpen(i+1, j+1)) {
              return uf.connected(gridPosition(i, j), rowSize*rowSize);
          }
          else {
              return false;
          }
      }
      return false;
   }
   
   private int gridPosition(int i, int j) {
       return (rowSize*i) + j;
   }
   
   private boolean validate(int i, int j) {
       if (i < 1 || i > rowSize) throw new IndexOutOfBoundsException("row index i out of bounds");
       if (j < 1 || j > rowSize) throw new IndexOutOfBoundsException("column index j out of bounds");
       return true;
   }
   
   public boolean percolates() {
       int i;
       for (i = 0; i < count; i++) {
           if (uf.connected(bottomOpen[i], rowSize*rowSize)) {
               return true;
           }
       }
       return false;
   }
}
