package alg;

import edu.princeton.cs.algs4.BinarySearch;
import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.princeton.cs.algs4.StdOut;

public class Program {

	public static void main(String[] args) {

		int[] data = new int[]{3, 2, 5, 6, 1, 4, 6, 7};
		
		Quick q = new Quick();
		q.Partition(data, 1, 1);
		
		for(int i = 0; i <= data.length; i++){
			StdOut.println(data[i]);
		}
		//StdOut.println(15/2);
		//StdOut.println(BinarySearch.rank(25, new int[]{12, 18, 21, 25, 41, 53, 56, 59, 67, 73, 82, 83, 85, 96, 98}));
	}
}
