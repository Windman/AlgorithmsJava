import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Queue;

public class Board implements Comparable<Board>{

	private final int N;
	private final int[][] tiles;
	private int[][] twins;

	public Board(int[][] blocks) {
		if (blocks == null)
			throw new java.lang.NullPointerException();

		N = blocks[0].length;
		tiles = cloneArray(blocks);  
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
				int pos = position(i + 1, j + 1);
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
		return value - N * (getRowIndex(value) - 1);
	}

	private int getRowIndex(int value) {
		if (value % N == 0)
			return value / N;
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
				h = h + Math.abs(getColumnIndex(tiles[i][j]) - (j + 1))
						+ Math.abs(getRowIndex(tiles[i][j]) - (i + 1));
			}
		}
		return h;
	}
		
	public boolean isGoal() {
		if (hamming() == 0) return true;
		return false;
	}
	
	private static void exch(int[] v, int i, int j) {
		int swap = v[i];
		v[i] = v[j];
		v[j] = swap;
	}
	
	private static void exchInCol(int[][] data, int c, int r, int r2) {
		int cup = data[r][c];
		data[r][c] = data[r2][c];
		data[r2][c] = cup;
	}
	
	public Board twin() {
		// a board that is obtained by exchanging two adjacent blocks in the
		// same row
		int cancel = 0, r = 0, c = 0;
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				if (tiles[row][col] == 0) {
					r = row;
					c = col;
					cancel = 1;
					break;
				}
			}
			if (cancel == 1)
				break;
		}
		
		twins = cloneArray(tiles);
		
		int c1 = 0;
		int c2 = 0;
		
		if ((c + 1) <= N-1 && ((c - 1) >= 0))  {
			exch(twins[r], c+1, c-1);
		}
		
		return new Board(twins);
	}

	public boolean equals(Object other) {
		// does this board equal y?
		if (other == this)
			return true;
		if (other == null)
			return false;
		if (other.getClass() != this.getClass())
			return false;
		Board that = (Board) other;
		if (this.manhattan() != that.manhattan())
			return false;
		return true;
	}
	
	private static int[][] cloneArray(int[][] src) {
	    int length = src.length;
	    int[][] target = new int[length][src[0].length];
	    for (int i = 0; i < length; i++) {
	        System.arraycopy(src[i], 0, target[i], 0, src[i].length);
	    }
	    return target;
	}
	
	public Iterable<Board> neighbors() {
		Queue<Board> q = new Queue<Board>();
		int r = 0, c = 0, cancel = 0;
		
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				if (tiles[row][col] == 0) {
					r = row;
					c = col;
					cancel = 1;
					break;
				}
			}
			if (cancel == 1)
				break;
		}
		
		if ((r + 1) <= N-1) {
			int[][] dataR1 = cloneArray(tiles);  
			exchInCol(dataR1, c, r, r + 1);
			q.enqueue(new Board(dataR1));
		}
		
		if ((r - 1) >= 0) {
			int[][] dataR2 = cloneArray(tiles);  
			exchInCol(dataR2, c, r, r - 1);
			q.enqueue(new Board(dataR2));
		}
		if ((c + 1) <= N-1)  {
			int[][] dataC1 = cloneArray(tiles);  
			exch(dataC1[r], c, c + 1);
			q.enqueue(new Board(dataC1));
		}
		if ((c - 1) >= 0) {
			int[][] dataC2 = cloneArray(tiles);  
			exch(dataC2[r], c, c - 1);
			q.enqueue(new Board(dataC2));
		}
				
		return q;
	}

	public int compareTo(Board that) {
		// TODO Auto-generated method stub
		if (this.hamming() > that.hamming())
			return 1;
		else if (this.hamming() < that.hamming())
			return -1;
		else return 0;
	}
	
	public String toString() {
		// string representation of this board (in the output format specified
		// below)
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
		int[][] data = new int[][] { { 1, 2 }, { 3, 0 } };
		int[][] data2_2 = new int[][] { { 2, 0 }, { 3, 1 } };
		int[][] data3_3 = new int[][] { { 8, 1, 3 }, { 4, 0, 2 }, { 7, 6, 5 } };
		int[][] data3_3_g = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };
		int[][] datatwin = new int[][] { { 5, 0, 4 }, { 2, 3, 8 }, { 7, 1, 6 } };
		
		Board b = new Board(data);
		Board b2_2 = new Board(data2_2);
		Board b3_3 = new Board(data3_3);
		Board b3_3_g = new Board(data3_3_g);
		Board bdTween = new Board(datatwin);
		//Board b3_3_c = new Board(data3_3_c);
		
		System.out.println("Print board 3*3");
		System.out.println(b3_3.toString());

		System.out.println("Hamming");
		try {
			assert (b.hamming() == 0);
			assert (b2_2.hamming() == 2);
			assert (b3_3.hamming() == 5);

		} catch (java.lang.AssertionError e) {
			System.out.println("Hamming assertion faild!");
		}

		System.out.println("2*2 " + b.hamming() + " == 0");
		System.out.println("2*2 " + b2_2.hamming() + " == 2");
		System.out.println("3*3 " + b3_3.hamming() + " == 5");
		
		assert (b3_3.getColumnIndex(1) == 1);
		assert (b3_3.getColumnIndex(3) == 3);
		assert (b3_3.getColumnIndex(5) == 2);
		assert (b3_3.getColumnIndex(8) == 2);

		assert (b3_3.getRowIndex(1) == 1);
		assert (b3_3.getRowIndex(2) == 1);
		assert (b3_3.getRowIndex(3) == 1);
		assert (b3_3.getRowIndex(5) == 2);
		assert (b3_3.getRowIndex(7) == 3);
		System.out.println();
		
		System.out.println("Manhattan");

		try {
			assert (b.manhattan() == 0);
			assert (b2_2.manhattan() == 3);
			assert (b3_3.manhattan() == 10);

		} catch (java.lang.AssertionError e) {
			System.out.println("Manhattan assertion faild!");
		}

		System.out.println("2*2 " + b.manhattan() + " == 0");
		System.out.println("2*2 " + b2_2.manhattan() + " == 3");
		System.out.println("3*3 " + b3_3.manhattan() + " == 10");
		// Is Goal
		assert (b3_3.isGoal() == false);
		assert (b3_3_g.isGoal() == true);
		// Is Equal
		assert (b2_2.equals(b3_3) == false);
		assert (b3_3.equals(b3_3_g) == false);
		//assert (b3_3.equals(b3_3) == true);
		
		System.out.println("Print original board 3*3 neighbors");
		StdOut.println("Original:");
		StdOut.println(b3_3.toString());
		StdOut.println("Neighbors:");
		
		for (Board brd: b3_3.neighbors()) {
			StdOut.println(brd.toString());
		}
		
		System.out.println();
		System.out.println("Print original board 3*3");
		System.out.println(bdTween.toString());
		System.out.println("Print twin board 3*3");
		System.out.println(bdTween.twin().toString());
	}

	

}
