import java.util.Comparator;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
    
	private final class ByPriority implements Comparator<Board>
    {
		int m;
		
		public ByPriority(Solver s)
		{
			m = s.moves();
		}
		
		public int compare(Board b1, Board b2) {
			
			int p1 = b1.manhattan() + m;
			int p2 = b2.manhattan() + m;
			
			if (p1 > p2) {
				return 1;
			}
			else if(p1 < p2) {
				return -1;
			}
			else return 0;
		}
    }
	
	private final Comparator<Board> ByPriority = new ByPriority(this);
	
	public Solver(Board initial){
    	// find a solution to the initial board (using the A* algorithm)
		if (initial == null) throw new java.lang.NullPointerException();
		MaxPQ<Board> q = new MaxPQ<Board>(this.ByPriority);
		q.insert(initial);
		q.insert(initial);
		q.insert(initial);
		StdOut.println(q.delMax().toString());
	}
    public boolean isSolvable() {
    	// is the initial board solvable?
    	return true;
    }
    public int moves() {
    	// min number of moves to solve initial board; -1 if unsolvable
    	return 0;
    }
    public Iterable<Board> solution() {
    	// sequence of boards in a shortest solution; null if unsolvable
    	return null;
    }
    public static void main(String[] args) {
    	
    	int[][] data3_3 = new int[][]{{8,1,3}, {4,0,2}, {7,6,5}};
    	Board b3_3 = new Board(data3_3);
    	Solver s = new Solver(b3_3);
    }
}
