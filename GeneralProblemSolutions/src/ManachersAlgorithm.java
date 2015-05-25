
//ManachersAlgorithm 

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class ManachersAlgorithm {
	public static void main(String[] args) {

		String str="babcbabcbaccba";
		String str1="abaabacccccbbccccc";

		System.out.println(findLongestPelindrom(str1));


	}

	public static String preProcessString(String input)
	{
		String str="^";

		for(int i=0;i<input.length();i++)
		{

			str =str+"#" + input.charAt(i);

		}
		str+="#$";

		return str;
	}


	public static String findLongestPelindrom(String input) 
	{

		String T=preProcessString(input);
		System.out.println(T+" "+T.length());
		int n=T.length();

		int[] P=new int[n];

		int C=0, R=0;

		for(int i=1;i<n-1;i++)
		{
			int i_mirror = 2*C - i;

			P[i]=(R>i) ? (Math.min(R - i, P[i_mirror])) : 0;


			while(T.charAt(i + 1 + P[i]) == T.charAt(i - 1 - P[i]))
			{
				P[i]=P[i] + 1;
			}


			if(i + P[i] > R)
			{
				C = i;
				R = i+P[i];

			}
		}

		for (int element : P) {
			System.out.print(element);
		}


		int maxLen=0,centerIndex=0;

		for(int i=1;i<n-1;i++)
		{
			if(P[i]>maxLen)
			{
				maxLen=P[i];
				centerIndex=i;

			}
		}

		System.out.println("Params: "+maxLen+","+centerIndex);

		if(maxLen%2==0)
		{
			return input.substring((centerIndex - 1)/2 - maxLen/2 ,(centerIndex -1)/2 + (maxLen/2));
		}
		else
		{
			return input.substring((centerIndex - 1 - maxLen)/2 ,(centerIndex -1)/2 + (maxLen/2)+1);

		}
	}



}

