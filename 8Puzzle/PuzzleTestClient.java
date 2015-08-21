import edu.princeton.cs.algs4.*;
import java.io.File;

public class PuzzleTestClient {

	public static void main(String[] args) {
		args = new String[]{"C:\\_SourcesJava\\AlgorithmsJava\\8Puzzle\\Tests\\"};
		  File dir = new File(args[0]);
		  File[] directoryListing = dir.listFiles();
		  if (directoryListing != null) {
		    for (File child : directoryListing) {
		    	In in = new In(child.getAbsolutePath());
			    int N = in.readInt(); 
				int[][] blocks = new int[N][N];
			    for (int i = 0; i < N; i++)
			        for (int j = 0; j < N; j++)
			            blocks[i][j] = in.readInt();
			    Board initial = new Board(blocks);
			    
			    StdOut.println(initial.toString());
			    
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
		  } else {
		    
		  }
	}
}
