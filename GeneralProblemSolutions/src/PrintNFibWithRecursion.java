
/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */


class PrintNFibWithRecursion {

	static int no=0;
	public static void main(String[] args) {


		fibHelper(18);

	}

	public static void fibHelper(int n)
	{
		fibonacci(0,1,n);
	}



	public static void fibonacci(int a,int b,int n)
	{

		if (n==0) {
			return;
		}

		else {
			System.out.println( no);
			no = a + b;
			a = b;
			b = no;

			fibonacci(a, b,n-1);
		}


	}






}

