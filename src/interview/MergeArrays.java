package interview;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Bag;

public class MergeArrays {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = new int[] { 1, 2, 3, 4, 5 };
		int[] b = new int[] { 1, 2, 3, 5, 18, 19, 20 };

		Bag<Integer> bag = new Bag<Integer>();
		int length = a.length + b.length;
		int j = 0;

		for (int i = 0; i < a.length; i++) {
			if (a[i] < b[j])
				bag.add(a[i]);
			else if (a[i] > b[j])
				bag.add(b[j]);
			else {
				bag.add(a[i]);
				bag.add(b[j]);
				j++;
			}
		}

		for (j = j; j < b.length; j++) {
			bag.add(b[j]);
		}

		for (int e : bag) {

			StdOut.print(e + " ");
		}
	}
}
