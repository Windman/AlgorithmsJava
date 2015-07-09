package alg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Helper {
	
	public static int[] CreateArray(int number)
	{
		List<Integer> data = new ArrayList<Integer>();
		for(Integer i = 0; i<number; i++)
		{
			data.add(i);
		}
		Collections.shuffle(data);
		Integer[] arrayOfIntegers = data.toArray(new Integer[data.size()]);	
		
		int[] arrayOfInt = new int[number];
		
		for(int i = 0; i<number; i++)
		{
			arrayOfInt[i] = arrayOfIntegers[i];
		}
		
		return arrayOfInt;
	}
	
	public static void PrintArray(int[] data)
	{
		for(Integer i = 0; i<data.length; i++)
		{
			System.out.print(data[i]);
			
			if (i==data.length-1)
					System.out.println();
			else
				System.out.print(",");
		}
	}
	
	public static String PrintStringArray(int[] data)
	{
		StringBuilder result =  new StringBuilder();
		for(Integer i = 0; i<data.length; i++)
		{
			result.append(data[i]);
			if (i==data.length-1)
				result.append("\r\n");
		}
		
		return result.toString();
	}
}
