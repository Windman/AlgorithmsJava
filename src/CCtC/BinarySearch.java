package CCtC;

import edu.princeton.cs.algs4.Stopwatch;

public class BinarySearch {

	public static void main(String[] args) {
		int length = 100000000;
		int[] data = new int[length];
		
		//ORDERED Array
		for (int i = 0; i < data.length; i++) {
			data[i] = i;
		}
		
		Stopwatch stopwatch = new Stopwatch();
		System.out.println(binarySearch(data, length -1));
		double t1 = stopwatch.elapsedTime();
		System.out.println("Sec:" +t1);
		
		System.out.println(naivSearch(data, length -1));
		System.out.println("Sec:" + (stopwatch.elapsedTime() - t1));
		
		//assert (binarySearch(data, 998) == 998);

	}
	
	public static int naivSearch(int[] data, int forSearch) {
		for (int i = 0; i < data.length; i++) {
			if (data[i] == forSearch) {
				return i;
			}
		}
		return -1;
	}
	
	public static int binarySearch(int[] data, int forSearch){
		int startIdx = 0;
		int endIdx = data.length;
		
		int center = (endIdx - startIdx)/2;
		while((endIdx - startIdx) >=0 ) {
			
			if (data[center] == forSearch) {
				return center;
			}
			else if (data[center] < forSearch) {
				center = startIdx + (endIdx - startIdx)/2;
				startIdx = center;
			}
			else if (data[center] > forSearch) {
				center = endIdx - (endIdx - startIdx)/2;
				endIdx = center;
			}
		}
		
		return -1;
	}

}
