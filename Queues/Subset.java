public class Subset {

	public static void main(String[] args) {
		RandomizedQueue<String> q = new RandomizedQueue<String>();

		int k = StdIn.readInt();
		while (true) {
			String s = StdIn.readString();
			q.enqueue(s);
			if (q.size() >= k) {
				for (int i = 0; i < k; i++) {
					StdOut.println(q.sample());
				}
				break;
			}
		}

	}
}
