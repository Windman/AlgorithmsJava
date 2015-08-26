import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
    private final class ByPriority implements Comparator<Board>
    {
		private int m;
		
		public ByPriority(Solver s)
		{
			m = s.moves();
		}
		
		public int compare(Board b1, Board b2) {
			
			int p1 = b1.manhattan() +  m;
			int p2 = b2.manhattan() +  m;
			
			if (p1 > p2) {
				return 1;
			}
			else if (p1 < p2) {
				return -1;
			}
			else return 0;
		}
    }
	
    private int moves;
	private boolean solved;
	private final Comparator<Board> ByPriority = new ByPriority(this);
	private ArrayList<Board> track;
	
	public Solver(Board initial){
		if (initial == null) throw new java.lang.NullPointerException();
		
		solved = false;
		Board current = null;
		MinPQ<Board> minQ = new MinPQ<Board>(this.ByPriority);
		
		track = new ArrayList<Board>();
		
		minQ.insert(initial);
		current = minQ.delMin();
		
		moves = 0;
		
		if (current.isGoal()) {
			solved = true;
		}
		
		track.add(current);
		
		int flag = 0;
		while (!solved) {
			Iterable<Board> items = current.neighbors();
			for (Board b: items) {
				for (Board i: track) {
					if (i.equals(b))
						break;
					minQ.insert(b);
				}
			}
			
			if (!minQ.isEmpty()) {
				current = minQ.delMin();
				if (current.isGoal()) {
					solved = true; 
				}
					
				track.add(current);
			}
			else {
				solved = false;
				moves = -1;
				break;
			}
			
			//for debugging
			flag++;
			int N = initial.dimension();
			if (flag > N*N*N) {
				moves = -1;
				solved = false;
				break;
			}
		}// end while
				
		//finding duplicates
		/*
		int[] input = new int[track.size()];
		for(int i = 0; i < track.size(); i++) {
			input[i] = track.get(i).manhattan();
		}
		
		int current1 = input[0];
		boolean found = false;
				
		for (int i = 1; i < input.length; i++) {
		    if (current1 == input[i] && !found) {
		    	found = true;
		        track.remove(i);
		    } else if (current1 != input[i]) {
		        current1 = input[i];
		        found = false;
		    }
		}*/

		moves = track.size() - 1;
	}
    public boolean isSolvable() {
    	// is the initial board solvable?
    	return solved;
    }
    public int moves() {
    	// min number of moves to solve initial board; -1 if unsolvable
    	return moves;
    }
    public Iterable<Board> solution() {
    	// sequence of boards in a shortest solution; null if unsolvable
    	return track;
    }
    
    private static void PrintSolution(Solver solver) {
    	if (!solver.isSolvable())
	        StdOut.println("No solution possible");
	    else {
	        StdOut.println("Minimum number of moves = " + solver.moves());
	        for (Board board : solver.solution())
	            StdOut.println(board);
	    }
    }
    
    public static void main(String[] args) {
    	
    	int[][] data = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
    	Board b3_3 = new Board(data);
    	Solver s = new Solver(b3_3); 
    	PrintSolution(s);
    }
}
