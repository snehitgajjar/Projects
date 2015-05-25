
public class UnsetRightMostBit {
	static int no=0;
	public static void main(String[] args) {


		System.out.println(findRightMostSetBit(7));

	}



	public static int findRightMostSetBit(int n)
	{

		return n & (n - 1);
	}
}
