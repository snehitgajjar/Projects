
/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */




class ReverseEachWord{


	public static void main(String[] args) {


		System.out.println(reverseEachWord("How are you"));

	}

	public static String reverseEachWord(String str)
	{
		String tokens[] = str.split(" ");
		String result="";

		if(tokens.length>0) {
			result += reverseString(tokens[0]);
		}

		if(tokens.length>1)
		{
			for(int i=1;i<tokens.length;i++)
			{
				result += " "+reverseString(tokens[i]);

			}
		}

		return result;

	}

	public static String reverseString(String str)
	{
		char[] charArray=str.toCharArray();

		int front=0, end = charArray.length-1;


		while(front<=end)
		{
			char temp = charArray[end];
			charArray[end] = charArray[front];
			charArray[front]=temp;

			front++; end--;

		}

		return new String(charArray);

	}








}

