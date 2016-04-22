package alg;

import java.util.Scanner;

public class StdInputOutput {

	
	public static void main(String[] args) {
	       
		Scanner sc = new Scanner(System.in);
        int tests = sc.nextInt();
        for(int i = 0; i < tests; i++) { //Test interation
            int N = sc.nextInt();
            int[] data = new int[N];
            for(int j = 0; j < data.length; j++) {
                data[j] = sc.nextInt();
                System.err.print(data[j]);
            }
            
            
        }
    }

}
