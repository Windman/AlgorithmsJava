package alg;

public class SherlockAndWatson {

	public static void main(String[] args) {
		int[] data = new int[]{1,2,3};
		System.out.println(SolvePuzzle(data));

	}

	public static String SolvePuzzle(int[] data) {
        String result = "NO";
        int m = data.length/2 + 1;
        int e = data.length;
        int s = 0;
        m--;
        while((e - s) > 0) {
            int sumRight = Summ(data, m, e);
            int sumLeft = Summ(data, s, m-1);
            System.err.println("start: "+m+" end: "+e+" sumRight: " + Summ(data, m, e));
            System.err.println("start: "+s+" end: "+m+" sumLeft: " + Summ(data, s, m-1));
            if (data[m] == sumRight || data[m] == sumLeft)
                  return "YES";
            else if(data[m] > sumRight && data[m] > sumLeft)
                return "NO";
            else if (data[m] > sumRight) {
                m--;
            }
            else if (data[m] < s) {
                m++;
            }
        }
        
        return result;
    } 
    
    public static int Summ(int[] data, int startIdx, int endIdx) {
        int summ = 0;
        
        for(int i = startIdx; i < endIdx; i++) {
            summ += data[i];
        }
        
        return summ;
    }
}
