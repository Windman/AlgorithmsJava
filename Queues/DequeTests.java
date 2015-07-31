public class DequeTests {

	public static void RemoveFirstElemets(int size) {
		Deque<Integer> q = new Deque<Integer>();
		for (int i = 1; i <= size; i++) {
			q.addFirst(i);
		}

		StdOut.println();

		for (int i = q.size(); i > 0; i--) {
			StdOut.print(q.removeFirst() + " ");
		}
	}

	public static void RemoveLastElemets(int size) {
		Deque<Integer> q = new Deque<Integer>();
		for (int i = 1; i <= size; i++) {
			q.addLast(i);
		}
		StdOut.println();
		for (int i = q.size(); i > 0; i--) {
			StdOut.print(q.removeLast() + " ");
		}
		StdOut.println();
	}

	public static void AddLastElemets(int size) {
		Deque<Integer> q = new Deque<Integer>();
		for (int i = 1; i <= size; i++) {
			if (i % 2 == 0)
				q.addLast(i);
			else q.addFirst(i);
		}
		StdOut.println("Size: "+q.size());
		for (int i = q.size(); i >= 0; i--) {
			StdOut.println(q.removeLast());
		}
		StdOut.println();
	}

	public static void main(String[] args) {
		
		//TODO Execution Problem with removeLast()
		AddLastElemets(10);
		/*
		RemoveFirstElemets(10);
		RemoveLastElemets(10);*/
	}
}
