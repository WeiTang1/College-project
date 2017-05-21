package test;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s ="mips";
		String t ="pims";
		int lens= s.length();
		int lenT = t.length();
		int k =levenshteinDistance(s,lens,t,lenT);
		System.out.println(k);
		
		
	}
	public static int levenshteinDistance(String s, int lenS, String t, int lenT) { /* base case: empty strings */
		if (lenS == 0) return lenT;
		if (lenT == 0) return lenS;
		int match;
		/* test if last characters of the strings match */
		char d =s.charAt(lenS-1);
		char e = t.charAt(lenT - 1);
		if (d==e)
		match = 0;
		else
		match = 1;
		int a=levenshteinDistance(s, lenS - 1, t, lenT) + 1;
		int b=levenshteinDistance(s, lenS, t, lenT - 1) +1;
		int c=levenshteinDistance(s, lenS - 1, t, lenT - 1) + match;
		/* return minimum of delete char from s, delete char from t, and delete char from both */
		return min(a,b,c);
		}
	 
	 public static int min(int a, int b, int c) {
		    return Math.min(Math.min(a, b), c);
		}
}
