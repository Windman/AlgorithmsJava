package alg;

public class InsertSort {

	public static void Sort(int[] data)
	{
		for(int j = 1; j<data.length; j++)
		{
			int key = data[j];
			int i = j-1;
			while(i>=0 && data[i]> key)
			{
				data[i+1] = data[i];
				i--;
			}
			data[i+1] = key;
		}
	}
	
	public static void AdvancedSort(int[] data)
	{
		/*for(int j = 1; j<data.length; j++)
		{
			int key = data[j];
			int i = j-1;
			Integer index = SerchAlgorithms.BinarySearch(data, key, 0, i);
			if (index == null && data[i]> key)
			{
				data[i+1] = data[i];
				data[i] = key;
			}
			else if (index != null)
			{
				data[index] = key;
				
			}
			
		}*/
	}
}
