package alg;

public class SerchAlgorithms {

	public static Boolean SimpleSearch(int[] data, int value)
	{
		Boolean result = false;
		for(int i = 1; i< data.length; i++)
		{
			if(data[i] == value)
				return true;
		}
		return result;
	}
	
	public static Integer BinarySearch(int[] data, int value, int p, int r)
	{
		Integer result = null;
		
		if(value < data[0] || value > data[r])
			return result;
		
		if (p == r && data[p] != value )
			return null;
		
		int q = (p+r)/2;
		if ((p+r)%2 != 0)
			q = (int) Math.floor((p+r)/2);
		
		if (data[q] == value)
			return q;
		
		if (data[q]>value) //left
			result = BinarySearch(data, value, p, q-1);
		else
			result = BinarySearch(data, value, q+1, r);
		
		return result;
	}
	
	public static int Search(int[] source, int p, int q, int r, int value) throws Exception
	{
		int n1 = q-p+1;
		int n2 = r-q;
		int[] left = new int[n1];
		int[] right = new int [n2];
		
		for(int i = 0; i<n1; i++)
		{
			left[i] = source[p+i];
		}
		
		for(int j = 0; j<n2; j++)
		{
			right[j] = source[q+j+1];
		}
		
		int i = 0;
		int j = 0;
		
		
		for(int k = p; k<=r; k++)
		{
			if(left[0] <= value && left[left.length-1]>=value)
			{
				if (left[i]!= value)
					i++;
				else
					return k + n2;	
			}
			else if(right[0] <= value && right[right.length-1]>=value)
			{
				if(right[j] != value)
					j++;
				else
					return k + n1;
			}
			else
			{
				throw new Exception("not found");
			}
		}
		
		throw new Exception("not found");
	}
}
