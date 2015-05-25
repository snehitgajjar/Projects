
/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */



class ATOI {

	static int no=0;
	public static void main(String[] args) {


		System.out.println(atoi("-1234"));

	}



	public static int atoi(String input)
	{

		int flag=1, num=0;

		int i=0;

		if(input.charAt(i)=='-')
		{
			flag= -1;
			i++;
		}

		while(i<input.length())
		{
			num= num *10;

			num = num + (input.charAt(i) - '0');  

			i++;
		}

		return flag*num;



	}





}

