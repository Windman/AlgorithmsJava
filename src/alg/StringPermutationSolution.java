package alg;

public class StringPermutationSolution {
	
	public static void main(String[] args){
		int encoding = 256;
		
		//TODO Find spaces
		String[] s = new String[] {
				  "a bcdefgthf a b c b dkj h   k jasabba   ab" 
				 
		};
		
		countAnagrams(s);
	}
	
	static void countAnagrams(String[] arr) {
        for(int j = 0; j < arr.length; j++) {
        	
            String B = SplitB(arr[j]);
            String A = SplitA(arr[j], B);
            printResults(A, B);
        }
    }
	
	static String SplitB(String s) {
		int len = s.length() - 1;
		for (int i = len; i > 0; i--) {
			if(s.charAt(i) == ' ') {
				return s.substring(i, s.length());
			}
		}
		return "";
	}
	
	static String SplitA(String s, String B) {
		
		return s.substring(0, s.length()-B.length());
	}
	
	static void printResults(String A, String B ){
        int encoding = 256;
        
        int numberOfAnagrams = 0;
        StringBuilder sb = new StringBuilder();
        
        if (isAnagram(A, B, encoding))
        {
			sb.append(0);
			numberOfAnagrams++;
        }
		else {
            int searchLen = B.length(); 
            for(int i = 0; i <= A.length(); i++){
                if ((i + searchLen) <= A.length())
                {
                    String a = A.substring(i, i + searchLen);
                    if(isAnagram(a, B, 256)) {
                        numberOfAnagrams++;
                        sb.append(i + " ");
                    }
                }
            }
		}
        
		System.out.println(numberOfAnagrams+" " + sb.toString());
    }
	
	static boolean isAnagram(String s1, String s2, int encodingBytes) {
        if (s1.length() != s2.length()) {
            return false;
        }
        
        int[] charCode = new int[encodingBytes];
                
        for (int i = 0; i < s1.length(); i++){
        	int c = (int)s1.charAt(i);
            charCode[c] = charCode[c] + 1;
        }
        
        for (int j = 0; j < s2.length(); j++) {
            int s2CharCode = (int)s2.charAt(j);
        	int c = charCode[s2CharCode];
            c--;
            if (c < 0)
                return false;
        }
        
        return true;
    }
	
}



