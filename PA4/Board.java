/*
* Board.java
*/

import java.util.LinkedList;
import java.util.Comparator;

public class Board
{
    private int[][] board;
    private int N;
    
    /*
     * construct a board from an N-by-N array of blocks 
     * (where blocks[i][j] = block in row i, column j)
    */
    public Board(int[][] blocks)
    {
        N = blocks.length;
        board = new int[N][N];
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks.length; j++) {
                board[i][j] = blocks[i][j];
            }
        }
    }
    
    public int dimension()
    {
        return N;
    }
    
    public int hamming()
    {
        int count = 0;
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] != (i*N) + j + 1) {
                    if (i == N - 1 && j == N - 1) {
                        count += 0;
                    }
                    else
                        count++;
                }
            }
        }
        
        return count;
    }
    
    public int manhattan()
    {
        int count = 0;
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] != ((i*N) + j + 1)) {
                    if (i == N - 1 && j == N - 1) {
                        if (board[i][j] != 0) {
                            int tRow = board[i][j] / N;
                            int tCol = board[i][j] % N - 1;
                            if (tCol == -1)
                                tCol = N - 1;
                            if (tCol == N - 1) {
                                if (tRow != 0)
                                    tRow--;
                            }
                            int dRow = Math.abs(i - tRow);
                            int dCol = Math.abs(j - tCol);
                            count += dRow;
                            count += dCol;
                        }
                    }
                    else {
                        int tRow = 0, tCol = 0;
                        if (board[i][j] == 0) {
                            tRow = i;
                            tCol = j;
                        }
                        else {
                            tRow = board[i][j] / N;
                            tCol = board[i][j] % N - 1;
                            if (tCol == -1)
                                tCol = N - 1;
                            if (tCol == N - 1) {
                                if (tRow != 0)
                                    tRow--;
                            }
                        }
                        int dRow = Math.abs(i - tRow);
                        int dCol = Math.abs(j - tCol);
                        count += dRow;
                        count += dCol;
                    }
                }
            }
        }
        
        return count;
    }
    
    public boolean isGoal()
    {
        if (hamming() == 0)
            return true;
        
        return false;
    }
    
    public Board twin()
    {
        int randRow = StdRandom.uniform(N);
        int randCol = StdRandom.uniform(N);
        
        Board b2 = new Board(board);
        
        if (b2.board[randRow][randCol] == 0) {
            if (randRow == 0)
                randRow++;
            else if (randRow == N - 1)
                randRow--;
            else
                randRow++;
            
            if (randCol == 0)
                randCol++;
            else if (randCol == N - 1)
                randCol--;
            else
                randCol++;
        }
        
        if (randCol == 0) {
            if (b2.board[randRow][randCol + 1] == 0) {
                if (randRow != N - 1) {
                    randRow++;
                }
                else
                    randRow--;
            }
            int temp = b2.board[randRow][randCol];
            b2.board[randRow][randCol] = b2.board[randRow][randCol + 1];
            b2.board[randRow][randCol + 1] = temp;
        }
        
        else if (randCol == N - 1) {
            if (b2.board[randRow][randCol - 1] == 0) {
                if (randRow != N - 1) {
                    randRow++;
                }
                else
                    randRow--;
            }
            int temp = b2.board[randRow][randCol];
            b2.board[randRow][randCol] = b2.board[randRow][randCol - 1];
            b2.board[randRow][randCol - 1] = temp;
        }
        
        else {
            if (b2.board[randRow][randCol + 1] == 0) {
                if (randRow != N - 1) {
                    randRow++;
                }
                else
                    randRow--;
            }
            int temp = b2.board[randRow][randCol];
            b2.board[randRow][randCol] = b2.board[randRow][randCol + 1];
            b2.board[randRow][randCol + 1] = temp;
        }
        
        return b2;
    }
    
    public boolean equals(Object y)
    {
        if (!(y instanceof Board))
            return false;
        
        Board that = (Board) y;
        
        if (that.N != this.N)
            return false;
        
        //return this.field1.equals(that.field1)
         //   && this.field2.equals(that.field2);
            
            
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (this.board[i][j] != that.board[i][j])
                    return false;
            }
        }
        
        return true;
    }
    
    public Iterable<Board> neighbors()
    {
        Stack<Board> neighbors = new Stack<Board>();
        
        int i = 0, j = 0;
        for (int m = 0; m < N; m++) {
            for (int n = 0; n < N; n++) {
                if (board[m][n] == 0) {
                    i = m;
                    j = n;
                    break;
                }
            }
        }
        
        if (j == 0) {
            if (i == 0) {
                neighborSwap(i, j, i, j + 1);
                Board b = new Board(board);
                neighbors.push(b);
                
                neighborSwap(i, j + 1, i, j);              //restore the board
                
                neighborSwap(i, j, i + 1, j);
                Board b2 = new Board(board);
                neighbors.push(b2);
                
                neighborSwap(i + 1, j, i, j);              //restore the board
            }
            
            else if (i == N - 1)
            {
                neighborSwap(i, j, i, j + 1);
                Board b = new Board(board);
                neighbors.push(b);
                
                neighborSwap(i, j + 1, i, j);              //restore the board
                
                neighborSwap(i, j, i - 1, j);
                Board b2 = new Board(board);
                neighbors.push(b2);
                
                neighborSwap(i - 1, j, i, j);              //restore the board
            }
            
            else {
                neighborSwap(i, j, i, j + 1);
                Board b = new Board(board);
                neighbors.push(b);
                
                neighborSwap(i, j + 1, i, j);              //restore the board
                
                neighborSwap(i, j, i - 1, j);
                Board b2 = new Board(board);
                neighbors.push(b2);
                
                neighborSwap(i - 1, j, i, j);              //restore the board
                
                neighborSwap(i, j, i + 1, j);
                Board b3 = new Board(board);
                neighbors.push(b3);
                
                neighborSwap(i + 1, j, i, j);              //restore the board
            }
        }    
            
            else if (j == N - 1)
            {
                if (i == 0) {
                    neighborSwap(i, j, i, j - 1);
                    Board b = new Board(board);
                    neighbors.push(b);
                    
                    neighborSwap(i, j - 1, i, j);              //restore the board
                    
                    neighborSwap(i, j, i + 1, j);
                    Board b2 = new Board(board);
                    neighbors.push(b2);
                    
                    neighborSwap(i + 1, j, i, j);              //restore the board
                }
                
                else if (i == N - 1)
                {
                    neighborSwap(i, j, i, j -1);
                    Board b = new Board(board);
                    neighbors.push(b);
                    
                    neighborSwap(i, j - 1, i, j);              //restore the board
                    
                    neighborSwap(i, j, i - 1, j);
                    Board b2 = new Board(board);
                    neighbors.push(b2);
                    
                    neighborSwap(i - 1, j, i, j);              //restore the board
                }
                
                else {
                    neighborSwap(i, j, i, j - 1);
                    Board b = new Board(board);
                    neighbors.push(b);
                    
                    neighborSwap(i, j - 1, i, j);              //restore the board
                    
                    neighborSwap(i, j, i - 1, j);
                    Board b2 = new Board(board);
                    neighbors.push(b2);
                    
                    neighborSwap(i - 1, j, i, j);              //restore the board
                    
                    neighborSwap(i, j, i + 1, j);
                    Board b3 = new Board(board);
                    neighbors.push(b3);
                    
                    neighborSwap(i + 1, j, i, j);              //restore the board
                }
            }
            
            else {
                if (i == 0) {
                    neighborSwap(i, j, i, j - 1);
                    Board b = new Board(board);
                    neighbors.push(b);
                    
                    neighborSwap(i, j - 1, i, j);              //restore the board
                    
                    neighborSwap(i, j, i, j + 1);
                    Board b2 = new Board(board);
                    neighbors.push(b2);
                    
                    neighborSwap(i, j + 1, i, j);              //restore the board
                    
                    neighborSwap(i, j, i + 1, j);
                    Board b3 = new Board(board);
                    neighbors.push(b3);
                    
                    neighborSwap(i + 1, j, i, j);              //restore the board
                }
                
                else if (i == N - 1) {
                    neighborSwap(i, j, i, j - 1);
                    Board b = new Board(board);
                    neighbors.push(b);
                    
                    neighborSwap(i, j - 1, i, j);              //restore the board
                    
                    neighborSwap(i, j, i, j + 1);
                    Board b2 = new Board(board);
                    neighbors.push(b2);
                    
                    neighborSwap(i, j + 1, i, j);              //restore the board
                    
                    neighborSwap(i, j, i - 1, j);
                    Board b3 = new Board(board);
                    neighbors.push(b3);
                    
                    neighborSwap(i - 1, j, i, j);              //restore the board
                }
                
                else {
                    neighborSwap(i, j, i, j - 1);
                    Board b = new Board(board);
                    neighbors.push(b);
                    
                    neighborSwap(i, j - 1, i, j);              //restore the board
                    
                    neighborSwap(i, j, i, j + 1);
                    Board b2 = new Board(board);
                    neighbors.push(b2);
                    
                    neighborSwap(i, j + 1, i, j);              //restore the board
                    
                    neighborSwap(i, j, i + 1, j);
                    Board b3 = new Board(board);
                    neighbors.push(b3);
                    
                    neighborSwap(i + 1, j, i, j);              //restore the board
                    
                    neighborSwap(i, j, i - 1, j);
                    Board b4 = new Board(board);
                    neighbors.push(b4);
                    
                    neighborSwap(i - 1, j, i, j);              //restore the board
                }
            }
        
        return neighbors;
    }
    
    private void neighborSwap(int row, int col, int targetRow, int targetCol)
    {
        int temp = board[row][col];
        board[row][col] = board[targetRow][targetCol];
        board[targetRow][targetCol] = temp;
    }
    
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", board[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }
    
    public static void main(String[] args)
    {
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
            blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);
        System.out.println(initial);
        System.out.println(initial.twin());
        
    }
}




