package CCtC;

import java.util.ArrayList;
//Решение с координатами не подойдет, т.к. Point Занимает в пямяти больше 4 байт
public class Matrix {

	int[][] _matrix;
	int _n, _m;
	
	Matrix(int N, int M)
	{
		_n = N;
		_m = M;
		_matrix = new int[N][M];
		int value = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				_matrix[i][j] = ++value;
			}
		}		
	}
	
	public void Rotate90() {
		
		for (int layer = 0; layer < _m / 2; ++layer) {
			int first = layer;
			int last = _m - 1 - layer;
			for (int i = first; i < last; ++i) {
				int offset = i - first;
				//save top
				int top = _matrix[first][i];
				//left -> top
				_matrix[first][i] = _matrix[last- offset][first];
				//bottom -> left
				_matrix[last-offset][first] = _matrix[last][last-offset];
				//right -> bottom
				_matrix[last][last-offset] = _matrix[i][last];
				//top -> right
				_matrix[i][last] = top;
			}
		}
	}
		
	public String toString(){
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < _n; i++) {
			for (int j = 0; j < _m; j++) {
				sb.append(_matrix[i][j]);
			}
			sb.append("\r\n");
		}
		return sb.toString();
	}
	
	//Проверить 
	public static void main(String[] args) {
		Matrix m = new Matrix(3,3);
		m.Rotate90();
		System.out.println(m.toString());
	}
}
