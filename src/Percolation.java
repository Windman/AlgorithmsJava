/**
 * @author Maksim Kilovatiy Percolation implementation
 */
public class Percolation {

	/**
	 * Site structure
	 *
	 */
	private class Site {
		private boolean isopen;

		public Site() {
			isopen = false;
		}

		public boolean isOpen() {
			return isopen;
		}

		public void setOpen() {
			isopen = true;
		}
	}

	Site[] grid;
	int gridSize;
	int size;
	WeightedQuickUnionUF qunion;

	int virtTopIdx, virtBottomIdx;

	/**
	 * create N-by-N grid, with all sites blocked
	 * 
	 * @param n
	 *            matrix size
	 */
	public Percolation(final int n) {

		if (n <= 0) {
			throw new java.lang.IllegalArgumentException("n or t are <= 0");
		}
		size = n;
		int virtVertices = 2;
		gridSize = size * size + virtVertices;
		grid = new Site[gridSize];

		for (int i = 0; i < grid.length; i++) {
			grid[i] = new Site();
		}

		qunion = new WeightedQuickUnionUF(gridSize);

		virtTopIdx = 0;
		virtBottomIdx = gridSize - 1;
	}

	/**
	 * open site (row i, column j) if it is not open already
	 * 
	 * @param i
	 *            - row
	 * @param j
	 *            - column
	 */
	public void open(final int i, final int j) {

		if (i <= 0 || i > size)
			throw new IndexOutOfBoundsException("row index i out of bounds");
		if (j <= 0 || j > size)
			throw new IndexOutOfBoundsException("row index j out of bounds");

		int idx = xyTo1D(i, j);

		if (!grid[idx].isOpen())
			grid[idx].setOpen();

		if (i == 1)
			qunion.union(virtTopIdx, idx);
		else if (i == size)
			qunion.union(virtBottomIdx, idx);

		if (grid[xyTo1D(i, j + 1)].isOpen())
			qunion.union(idx, xyTo1D(i, j + 1));
		if (grid[xyTo1D(i, j - 1)].isOpen())
			qunion.union(idx, xyTo1D(i, j - 1));
		if ((i + 1) <= size && grid[xyTo1D(i + 1, j)].isOpen())
			qunion.union(idx, xyTo1D(i + 1, j));
		if ((i - 1) > 0 && grid[xyTo1D(i - 1, j)].isOpen())
			qunion.union(idx, xyTo1D(i - 1, j));
	}

	/**
	 * is site (row i, column j) open?
	 * 
	 * @param i
	 *            - x coord
	 * @param j
	 *            - y coord
	 * @return is open
	 */
	public final boolean isOpen(final int i, final int j) {
		boolean res = false;
		int idx = xyTo1D(i, j);
		if (grid[idx] != null && grid[idx].isopen) {
			res = true;
		}
		return res;
	}

	/**
	 * is site (row i, column j) full?
	 * 
	 * @param i
	 *            - x coord
	 * @param j
	 *            - y coord
	 * @return is full
	 */
	public final boolean isFull(final int i, final int j) {
		boolean res = false;
		int idx = xyTo1D(i, j);
		if (qunion.connected(idx, virtBottomIdx)
				|| qunion.connected(idx, virtTopIdx)) {
			res = true;
		} else if (qunion.connected(idx, xyTo1D(i, j + 1))
				|| qunion.connected(idx, xyTo1D(i, j - 1))) {
			res = true;
		} else if ((i + 1) <= size && qunion.connected(idx, xyTo1D(i + 1, j))) {
			res = true;
		} else if ((i - 1) > 0 && qunion.connected(idx, xyTo1D(i - 1, j))) {
			res = true;
		}

		return res;
	}

	/**
	 * @return does the system percolate?
	 */
	public boolean percolates() {
		return qunion.connected(virtBottomIdx, virtTopIdx);
	}

	/**
	 * @param row
	 * @param col
	 * @return conversion 2D into 1D
	 */
	private int xyTo1D(final int row, final int col) {
		return row * size - (size - col);
	}
}
