
//FindRightMostSetBit 


/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */



class FindRightMostSetBit {

	static int no=0;
	public static void main(String[] args) {


		System.out.println(findRightMostSetBit(10));

	}



	public static int findRightMostSetBit(int n)
	{
		int neg=-1*n;
		int t=n & -n;
		return ((int)(Math.log(t)/Math.log(2)) +1 );
	}





}


