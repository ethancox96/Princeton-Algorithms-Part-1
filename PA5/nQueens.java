/*
* CS11 PA5 nQueens.java
*/

public class nQueens
{
   private boolean grid[][];
   public nQueens(int n)
   {
      grid[][] = new boolean[n][n];
      for (int i = 0; i < n; i++) {
         for (int j = 0; j < n; j++) {
	    grid[i][j] = false;
	 }
      }
   }

   static void nextPermutation(int[] A)
   {
      int pivot;
      for (int i = A.length; i < 1; i--) {
         if (A[i] < A[i+1]) {
	    pivot = A[i];
            break;
	 }
      }
      
}
