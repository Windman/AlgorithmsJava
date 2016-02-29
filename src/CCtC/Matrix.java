package CCtC;

import java.util.ArrayList;

public class Matrix {

	Point[][] _matrix;
	int N;
	
	Matrix(Point[][] matrix)
	{
		_matrix = matrix;
		N = _matrix.length;
	}

	static class Point
	{
		int X, Y, Color, Val;
		
		Point(int x, int y, int v)
		{
			X = x;
			Y = y;
			Color = 0;
			Val = v;
		}
		
		public String toString(){
			return ""+Val;
		}
	}
			
	public int getDimention(){
		return _matrix.length;
	}
	
	public int getNewX(int Y) {
		return Y;
	}

	public int getNewY(int X) {
		return N - X -1;
	}

	public void Rotate90(int X, int Y) {
		
		if (_matrix[X][Y].Color == 1)
			return;
		
		int x = getNewX(X);
		int y = getNewY(Y);
		_matrix[X][Y].Color = 1;
		
		Rotate90(x,y);
	}
		
	public String toString(){
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(_matrix[i][j].toString());
			}
			sb.append("\r\n");
		}
		return sb.toString();
	}
	
	//Проверить 
	public static void main(String[] args) {
		int dimention = 3;
		
		int v = 1;
		Point[][] matrix = new Point[dimention][dimention];
		for (int i = 0; i < dimention; i++) {
			for (int j = 0; j < dimention; j++) {
				matrix[i][j] = new Point(i, j, v++);
			}
		}
		
		Matrix m = new Matrix(matrix);
		m.Rotate90(0,0);
		m.toString();
	}
}
