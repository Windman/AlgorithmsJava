public class Percolation {

	private class Site
	{
		//написать реализацию класса
		private boolean _isopen;
			
		public Site(){
			Init();
		}
		
		private void Init(){
			_isopen = false;
		}
		
		public boolean isOpen(){
			return _isopen;
		}
		
		public void setOpen(){
			_isopen = true;
		}
	}
	
	Site[] _grid;
	int _gridSize;
	int _n;
	WeightedQuickUnionUF _qu;
	
	int _virtTopIdx, _virtBottomIdx;
	
	public Percolation(int N) // create N-by-N grid, with all sites blocked
	{
		_n = N;
		int virtVertices = 2;
		_gridSize = _n*_n+virtVertices;
		_grid = new Site[_gridSize];
		
		for (int i = 0; i < _grid.length; i++) {
			_grid[i] = new Site();
		}
		
		_qu = new WeightedQuickUnionUF(_gridSize);
		
		_virtTopIdx = 0;
		_virtBottomIdx = _gridSize-1;
	}

	public void open(int i, int j) // open site (row i, column j) if it is not open already
	{
 		if (i <= 0 || i > _n) throw new IndexOutOfBoundsException("row index i out of bounds");
		if (j <= 0 || j > _n) throw new IndexOutOfBoundsException("row index j out of bounds");
		
		int idx = xyTo1D(i,j);
		
		if(!_grid[idx].isOpen())
			_grid[idx].setOpen();
		
		if (i == 1)
			_qu.union(_virtTopIdx, idx);
		else if(i==_n)
			_qu.union(_virtBottomIdx, idx);
		
		if (_grid[xyTo1D(i,j+1)].isOpen())
			_qu.union(idx, xyTo1D(i,j+1));
		if (_grid[xyTo1D(i,j-1)].isOpen())
			_qu.union(idx, xyTo1D(i,j-1));
		if ((i+1) <= _n && _grid[xyTo1D(i+1,j)].isOpen())
			_qu.union(idx, xyTo1D(i+1,j));
		if ((i-1) > 0 && _grid[xyTo1D(i-1,j)].isOpen())
			_qu.union(idx, xyTo1D(i-1,j));
		
	}
	
	public boolean isOpen(int i, int j) // is site (row i, column j) open?
	{
		boolean res = false;
		int idx = xyTo1D(i,j);
		if (_grid[idx] != null && _grid[idx]._isopen)
			res = true;
		return res;
	}

	public boolean isFull(int i, int j) // is site (row i, column j) full?
	{
		boolean res = false;
		int idx = xyTo1D(i,j);
		if (_qu.connected(idx, _virtBottomIdx) || _qu.connected(idx, _virtTopIdx))
			res = true;
		else if (_qu.connected(idx, xyTo1D(i,j+1)) || _qu.connected(idx, xyTo1D(i,j-1)))
			res = true;
		else if ((i+1)<=_n && _qu.connected(idx, xyTo1D(i+1,j)))
			res = true;
		else if((i-1)>0 && _qu.connected(idx, xyTo1D(i-1,j)))
			res = true;
		return res;
	}

	public boolean percolates() // does the system percolate?
	{
		return _qu.connected(_virtBottomIdx, _virtTopIdx);
	}
	
	private int xyTo1D(int row, int col)
	{
		return row*_n-(_n-col);
	}
	
	/*public static void main(String[] args)
	{
		Percolation p =new Percolation(3);
		p.open(1,1);
		p.open(1,2);
		p.open(2,1);
		p.open(3,1);
		
		
		p.isFull(2,1);
	}*/
}
