package alg;

public class Heap {

	public static void HeapSort(int[] data)
	{
		int d = data.length-1;
		BuildMaxHeap(data);
		for(int i  = data.length-1; i>0; i--)
		{
			int cup = data[0];
			data[0] = data[i];
			data[i] = cup;
			d--;
			
			MaxHeap(data, 1, d);
		}
	}
	
	public static void BuildMaxHeap(int[]data)
	{
		int length = data.length;
		if (data.length %2 == 1)
			 length--;
		for(int i  = length/2; i>0; i --)
		{
			MaxHeap(data, i, data.length);
		}
	}
	
	public static void MaxHeap(int[] data, int index, int heapsize){
		int i = index -1;
		int l = Left(index)-1;
		int r = Right(index)-1;
		int heap_size = heapsize;
		int largest = 0;
		
		if (l <= heap_size && data[l]>data[i])
			largest = l;
		else
			largest = i;
		if(r <= heap_size && data[r] > data[largest])
			largest = r;
		
		if(largest != i)
		{
			int key = data[i];
			data[i] = data[largest];
			data[largest] = key;
			
			MaxHeap(data, largest+1, heap_size);
		}
		
	}
	
	public static int Parent(int i){
		if(i % 2 == 0)
			return i/2;
		return (i-1)/2;
	}
	
	public static int Left(int i){
		return 2*i;
	}
	
	public static int Right(int i){
		return 2*i+1;
	}
}
