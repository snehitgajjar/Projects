
/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */


class ReverseString {


	public static void main(String[] args) {


		System.out.println(reverseString("snehitb"));

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

