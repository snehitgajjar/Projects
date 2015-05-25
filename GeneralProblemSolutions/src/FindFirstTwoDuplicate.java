
//public class FindFirstTwoDuplicate 


/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class FindFirstTwoDuplicate {

	static int no=0;
	public static void main(String[] args) {


		//System.out.println(isBST(node,Integer.MIN_VALUE,Integer.MAX_VALUE));

		int[] array={1,2,3,4,5,6,2};
		System.out.println(findFirstDuplicate(array));


		//postorderTraversal(node);

	}


	public static int findFirstDuplicate(int[] array)
	{

		int currentIndex=0;



		for(int i=0;i<array.length;i++)
		{
			currentIndex = Math.abs(array[i]);

			if(currentIndex<0)
			{
				return currentIndex;
			}
			else
			{
				array[currentIndex] *= -1;

			}
		}

		return currentIndex;
	}



}



