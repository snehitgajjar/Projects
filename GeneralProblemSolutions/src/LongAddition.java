
/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */




class LongAddition {
	public static void main(String[] args) {

		System.out.println(longAddition("12","32"));

	}

	public static String longAddition(String num1,String num2)
	{
		int len_diff=0;

		int flag=1;
		String large=num1, small=num2;

		len_diff=num1.length() - num2.length();

		if(num1.length() <num2.length())
		{
			large=num2; small=num1;
			len_diff=num2.length() - num1.length();
		}


		String result="";
		int carry=0;


		for(int i=0;i<small.length();i++)
		{
			int val = Integer.parseInt(num1.charAt(num1.length()-1-i)+"") + Integer.parseInt(num2.charAt(num2.length()-1-i)+"") + carry;

			carry=val / 10;

			result=val%10 + result;
		}


		for(int i=0;i<len_diff;i++)
		{
			result= large.charAt(len_diff-i-1) + result;
		}

		return result;

	}









}

