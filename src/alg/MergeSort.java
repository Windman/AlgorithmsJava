
package alg;

public class MergeSort {
	
	static int countMs = 0;
	static int countM = 0;
	
	public static void MergeSort(int[] source, int p, int r)
	{
		System.out.print("MergeSort [count = "+ countMs++ +", p = "+p+", r = "+r+", length = "+ source.length+",  "+ Helper.PrintStringArray(source));
		if(p<r)
		{
			int q;
			q = (p+r)/2;
			if ((p+r)%2 != 0)
				q = (int) Math.floor((p+r)/2);
			MergeSort(source, p,q);
			MergeSort(source, q+1,r);
			Merge(source, p, q, r);
		}
	}
	
	public static void Merge(int[] source, int p, int q, int r)
	{
		int n1 = q-p+1;
		int n2 = r-q;
		int[] left = new int[n1+1];
		int[] right = new int [n2+1];
		
		for(int i = 0; i<=n1; i++)
		{
			left[i] = source[p+i];
		}
		
		for(int j = 0; j<n2; j++)
		{
			right[j] = source[q+j+1];
		}
		
		left[n1] = 999999999;
		right[n2] = 999999999;
		
		int i = 0;
		int j = 0;
		
		for(int k = p; k<=r; k++)
		{
			if (left[i]<= right[j])
			{
				source[k] = left[i];
				i++;
			}
			else 
			{
				source[k] = right[j];
				j++;
			}
				
		}
		System.out.print("Merge [count = "+ countM++ +", p = "+p+", r = "+r+", q= "+q+", length = "+ source.length+",  "+ Helper.PrintStringArray(source));
	}
}
