package CCtC;

import java.util.*;

public class ArraysAndStrings {

	public static void main(String[] args) {
		Rotate_1_6();
	}
	
	public static void Rotate_1_6(){
		
		Matrix m = new Matrix(2,4);
		System.out.println(m.toString());
		System.out.println();
		m.Rotate90();
		System.out.println(m.toString());
	}
	
	
	public static void TransformString_1_4()
	{
		String input = "Mr John Smith Black      ";
		char[] str = input.toCharArray();
		
		ArrayList<Integer> aux = new ArrayList<Integer>();
		aux.add(-1);
		int lastPos = -1;
		
		//Preparation
		for (int i = 0; i < str.length; i++) {
			if(i < str.length -1 && str[i] == ' ' && str[i+1] != ' '){
				aux.add(i);
			}
			else if(i < str.length -2 && str[i] != ' ' && str[i+1] == ' '&& str[i+2] == ' ')
				lastPos = i;
		}		
		
		int[] p = convertIntegers(aux);
		//End of preparation
		
		for (int i = p.length-1; i >= 1; i--) {
			int m = i * 2;
			
			for (int j = lastPos; j > p[i]; j--) {
				str[j+m] = str[j];
			}
			str[p[i] + m]   = '0';
			str[p[i]-1 + m] = '2';
			str[p[i]-2 + m] = '%';
			lastPos = p[i] -1;
		}
		
		for (int i = 0; i < str.length; i++) {
			System.out.print(str[i]);
		}
	}
	
	public static int[] convertIntegers(ArrayList<Integer> integers)
	{
	    int[] ret = new int[integers.size()];
	    Iterator<Integer> iterator = integers.iterator();
	    for (int i = 0; i < ret.length; i++)
	    {
	        ret[i] = iterator.next().intValue();
	    }
	    return ret;
	}
}


