
import java.util.HashMap;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */




class FindPalindromWordFromArrays {
	public static void main(String[] args) {

		String[] str={"bat", "tab", "cat","snehit","tihens","mom","mom"}; 
		findPalindromWordFromArrays(str);

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


	public static void findPalindromWordFromArrays(String[] str) {

		HashMap<String,String> hm = new HashMap<String,String> ();


		for (String element : str) {

			if(hm.containsKey(element)) {

				System.out.println(element+" "+hm.get(element));
			} else {

				hm.put(reverseString(element),element);
			}

		}





	}







}

