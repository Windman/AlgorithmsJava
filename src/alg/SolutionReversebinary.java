package alg;

import java.util.Scanner;

public class SolutionReversebinary {

		//Author: Kilovatiy Maksim
		//Problem: Reversed Binary Numbers
			
		private static int gornerConversion(char[] input){
			
			int s = 0 * 2 + Character.getNumericValue(input[0]);
			
			for (int i = 1; i < input.length; i++) {
				int t = s * 2 + Character.getNumericValue(input[i]);
				s = t;
			}
			
			return s;
		}
		
		private static char[] reverseString(String inputStr){
			
			char[] input = inputStr.toCharArray();
			
			int stopIndex;
			stopIndex = input.length/2;
			if (input.length % 2 != 0)
				stopIndex = (input.length+1)/2;
			
			stopIndex--;
			
			int j = input.length-1;
			
			char swap;
			
			for (int i = 0; i < input.length; i++) {
				
				swap = input[i];
				input[i] = input[j];
				input[j] = swap;
				
				j--;
				
				if (i == stopIndex) break;
			}
			
			return input;
		}
		
		public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
			try {
				int N = 1;          
				if (sc.hasNext()) {
					N = sc.nextInt();
					if (N >= 1 && N <= 1000000000) {
						System.out.println(gornerConversion(reverseString(Integer.toBinaryString(N))));
					}
					else
						throw new IllegalArgumentException("number out of the bounds");
				}
				else 
					throw new IllegalArgumentException("no input");
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
}
