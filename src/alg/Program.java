package alg;
import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.StdOut;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MaxPQ q = new MaxPQ<Integer>(new Integer[]{70, 62, 58, 45, 31, 16, 40, 24, 44, 18});
		q.delMax();
		q.delMax();
		q.delMax();
		StdOut.print();
	}

}
