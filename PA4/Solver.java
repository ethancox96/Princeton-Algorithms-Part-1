/*
* Solver.java
*/

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

public class Solver
{
    private Boolean solved;
    private SearchNode origNode;
    private SearchNode twinNode;
    
    public Solver(Board initial)
    {
        MinPQ<SearchNode> pq = new MinPQ<SearchNode>();
        MinPQ<SearchNode> pq2 = new MinPQ<SearchNode>();
        solved = false;
        
        Board twinB = initial.twin();
        
        origNode = new SearchNode(initial, null);
        pq.insert(origNode);
            
        twinNode = new SearchNode(twinB, null);
        pq2.insert(twinNode);
            
        while (true) {
            origNode = pq.delMin();
            
            if (origNode.b.isGoal()) {
                solved = true;
                break;
            }

            for (Board b : origNode.b.neighbors()) {
                if (origNode.prevNode == null || !b.equals(origNode.prevNode.b)) {
                    SearchNode sn = new SearchNode(b, origNode);
                    pq.insert(sn);
                }
            }
                
            twinNode = pq2.delMin();
               
            if (twinNode.b.isGoal()) {
                break;
            }
            
            for (Board b : twinNode.b.neighbors()) {
                if (twinNode.prevNode == null || !b.equals(twinNode.prevNode.b)) {
                    SearchNode sn = new SearchNode(b, twinNode);
                    pq2.insert(sn);
                }
            }
        }
    }
    
    private class SearchNode implements Comparable<SearchNode>
    {
        Board b;
        SearchNode prevNode;
        int level;
        int manhattan;
        int priority;
        
        public SearchNode(Board node, SearchNode curNode)
        {
            b = node;
            prevNode = curNode;
            
            if (curNode != null)
                level = curNode.level + 1;
            else
                level = 0;
            
            manhattan = node.manhattan();
            priority = manhattan + level;
        }
        
        public int compareTo(SearchNode that)
        {   
            if (this.priority < that.priority)
                return -1;
            
            else if (this.priority >  that.priority)
                return 1;
            
            else {
                if (this.manhattan < that.manhattan)
                    return -1;
                
                else if (this.manhattan > that.manhattan)
                    return 1;
                
                else
                    return 0;
            }
        }
    }
        
    public boolean isSolvable()
    {
        return solved;
    }
    
    public int moves()
    {
        if (isSolvable())
            return origNode.level;
        else
            return -1;
    }
    
    public Iterable<Board> solution()
    {
        Stack<Board> s = new Stack<Board>();
        SearchNode node = origNode;
        
        if (isSolvable()) {
            while (node != null) {
                s.push(node.b);
                node = node.prevNode;
            }
        } else {
            return null;
        }
        
        return s;
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
        
        // solve the puzzle
        Solver solver = new Solver(initial);
        
        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}




