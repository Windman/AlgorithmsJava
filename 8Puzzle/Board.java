import java.io.ObjectInputStream.GetField;
import java.util.Comparator;

import edu.princeton.cs.algs4.*;

public final class Board{
    
	private final int N;
	private final int[][] tiles;
	private int[][] twins;
	
	public Board(int[][] blocks) {
    	if (blocks == null) throw new java.lang.NullPointerException();
		
		N = blocks[0].length;
		tiles = blocks.clone();
		twins = null;
	}
    
    public int dimension() {
    	// board dimension N
    	return N;
    }
    
    private int position(int row, int col) {
		return row * N - (N - col);
	}
    
	public int hamming() {
    	// number of blocks out of place
		int h = 0;
    	for (int i = 0; i < N; i++) {
    		for (int j = 0; j < N; j++) {
    			int pos = position(i+1,j+1);
    			if (tiles[i][j] == 0) {
    				continue;
    			}
    			    			
    			if (pos != tiles[i][j]) {
    				h++;
    			}
    		}
    	}
    	return h;
    }
	
	private int getColumnIndex(int value) {
		return value - N*(getRowIndex(value)-1);
	}
    
    private int getRowIndex(int value) {
    	if (value % N == 0)
    		return value /N;
    	return (value / N) + 1;
    }
    
    public int manhattan() {
    	// sum of Manhattan distances between blocks and goal
    	int h = 0;
    	for (int i = 0; i < N; i++) {
    		for (int j = 0; j < N; j++) {
    			if (tiles[i][j] == 0) {
    				continue;
    			}
    			h = h + Math.abs(getColumnIndex(tiles[i][j]) - (j+1)) + Math.abs(getRowIndex(tiles[i][j]) - (i+1));
    		}
    	}
    	return h;
    }
    
    public boolean isGoal() {
    	// is this board the goal board?
    	for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
    			if (tiles[row][col] == 0)
    				continue;
				if (tiles[row][col] != position(row+1, col+1))
    				return false;
    		}
    	}
    	return true;
    }
    
    private static void exch(int[] v, int i, int j) {
        int swap = v[i];
        v[i] = v[j];
        v[j] = swap;
    }
    
    public Board twin() {
    	// a board that is obtained by exchanging two adjacent blocks in the same row
    	twins = tiles.clone();
    	int rndRow = StdRandom.uniform(0, N-1);
    	int rndCol1 = 0;
    	int rndCol2 = 0;
    	
    	while(true) {
    		rndCol1 = StdRandom.uniform(0, N-1);
        	rndCol2 = StdRandom.uniform(0, N-1);
        	if (rndCol1 != rndCol2)
        		break;
    	}
    			
    	exch(twins[rndRow], rndCol1, rndCol2);
    	
    	return new Board(twins);
    }
    
    public boolean equals(Object other) {
    	// does this board equal y?
    	if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false;
        Board that = (Board) other;
        if (this.manhattan() != that.manhattan()) return false;
        return true;
    }
    
    public Iterable<Board> neighbors() {
    	// all neighboring boards
    	return null;
    }
    
    public String toString() {
    	// string representation of this board (in the output format specified below)
        StringBuilder s = new StringBuilder();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tiles[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }
    
    public static void main(String[] args) {
    	
    	int n = 2;
    	int[][] data = new int[][]{{1,2}, {3,0}};
    	int[][] data2_2 = new int[][]{{2,0}, {3,1}};
    	int[][] data3_3 = new int[][]{{8,1,3}, {4,0,2}, {7,6,5}};
    	int[][] data3_3_g = new int[][]{{1,2,3}, {4,5,6}, {7,8,0}};
    	
    	Board b = new Board(data);
    	Board b2_2 = new Board(data2_2);
    	Board b3_3 = new Board(data3_3);
    	Board b3_3_g = new Board(data3_3_g);
    	
    	System.out.println("Print board 3*3");
    	System.out.println(b3_3.toString());
    	
    	System.out.println("Hamming");
    	try {
    		assert(b.hamming() == 0);
        	assert(b2_2.hamming() == 2);
        	assert(b3_3.hamming() == 5);
        	
		} catch (java.lang.AssertionError e) {
			System.out.println("Hamming assertion faild!");
		}
    	
    	System.out.println("2*2 "+b.hamming()+ " == 0" );
    	System.out.println("2*2 "+b2_2.hamming()+" == 2");
    	System.out.println("3*3 "+b3_3.hamming()+" == 5");
    	    	
    	assert(b3_3.getColumnIndex(1) == 1);
    	assert(b3_3.getColumnIndex(3) == 3);
    	assert(b3_3.getColumnIndex(5) == 2);
    	assert(b3_3.getColumnIndex(8) == 2);
    	
    	assert(b3_3.getRowIndex(1) == 1);
    	assert(b3_3.getRowIndex(2) == 1);
    	assert(b3_3.getRowIndex(3) == 1);
    	assert(b3_3.getRowIndex(5) == 2);
    	assert(b3_3.getRowIndex(7) == 3);
    	
    	System.out.println();
    	
    	System.out.println("Manhattan");
    	
    	try {
    		assert(b.manhattan() == 0);
        	assert(b2_2.manhattan() == 3);
        	assert(b3_3.manhattan() == 10);
        	
		} catch (java.lang.AssertionError e) {
			System.out.println("Manhattan assertion faild!");
		}
    	    	    	
    	System.out.println("2*2 "+b.manhattan()+ " == 0" );
    	System.out.println("2*2 "+b2_2.manhattan()+" == 3");
    	System.out.println("3*3 "+b3_3.manhattan()+" == 10");
    	
    	//Is Goal
    	assert(b3_3.isGoal() == false);
    	assert(b3_3_g.isGoal() == true);
    	
    	//Is Equal
    	assert(b2_2.equals(b3_3) == false);
    	assert(b3_3.equals(b3_3_g) == false);
    	assert(b3_3.equals(b3_3) == true);
    	
    	System.out.println(b3_3.toString());
    	System.out.println(b3_3.twin().toString());
    }

	
}
