public class DequeTests {

	public static void RemoveFirstElemets(int size) {
		Deque<Integer> q = new Deque<Integer>();
		for (int i = 1; i <= size; i++) {
			q.addFirst(i);
		}

		StdOut.println("Size: "+q.size());

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
		for (int i = q.size(); i >= 1; i--) {
			StdOut.println(q.removeLast());
		}
		StdOut.println();
	}

	public static void Test1() {
		Deque<Integer> deque = new Deque<Integer>();
		deque.addLast(0);
        StdOut.println(deque.removeLast());     
        deque.addLast(2);
        StdOut.println(deque.removeLast());     
        deque.addLast(4);
        StdOut.println(deque.removeLast()) ;     
        StdOut.println(deque.isEmpty());
        deque.addLast(7);
        deque.addLast(8);
        StdOut.println(deque.removeLast());
	}
	
	public static void Test2() {
		Deque<Integer> deque = new Deque<Integer>();
		deque.addFirst(0);
		deque.addFirst(1);
		deque.addFirst(2);
        StdOut.println(deque.removeLast());     
    }
	
	
	public static void main(String[] args) {
		Test2();
		/*
		Test1();
		AddLastElemets(10);
		RemoveFirstElemets(10);
		RemoveLastElemets(10);*/
	}
}
