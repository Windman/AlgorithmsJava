import edu.princeton.cs.algs4.*;

public class RandomizedQueueTests {
	
	public static void RemoveElements(int size)
	{
		RandomizedQueue<Integer> q = new RandomizedQueue<Integer>();
		for	(int i = 1; i<= size; i++)
		{
			q.enqueue(i);
		}
		
		StdOut.print(q.toString());
		StdOut.println();
		
		for	(int i = q.size(); i>0; i--)
		{
			StdOut.print(q.dequeue()+" ");
		}
		
		//TODO check size
	}
	
	public static void SampleTest(int size)
	{
		RandomizedQueue<Integer> q = new RandomizedQueue<Integer>();
		for	(int i = 1; i<= size; i++)
		{
			q.enqueue(i);
		}
		StdOut.println();
		StdOut.println(q.sample());
	}
}
