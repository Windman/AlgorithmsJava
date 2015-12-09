package alg;

public class Quick {

	public static void Partition(int[] data, int p, int r)
	{
		int rIndex = r-1;
		int pIndex = p-1;
		int x = data[rIndex];
		int i = pIndex-1;
		for (int j = pIndex; j < rIndex-1; j++)
		{
			if(data[j] <= x)
				{
					i++;
					int cup = data[i];
					data[i] = data[j];
					data[j] = cup;
				}
		}
		
		int cup = data[i+1];
		data[i+1] = data[rIndex];
		data[rIndex] = cup;
	}
}
