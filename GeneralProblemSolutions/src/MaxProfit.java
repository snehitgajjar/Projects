import java.util.HashMap;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */


class MaxProfit {

	static HashMap<Integer,Integer> ht=null;
	static int no=0;
	public static void main(String[] args) {

		Tree node=new Tree();

		node.setValue(15);
		node.leftTree=new Tree();
		node.rightTree=new Tree();

		node.leftTree.setValue(10);
		node.rightTree.setValue(25);

		node.leftTree.leftTree=new Tree();
		node.leftTree.rightTree=new Tree();

		node.leftTree.leftTree.setValue(5);
		node.leftTree.rightTree.setValue(13);

		node.rightTree.leftTree=new Tree();
		node.rightTree.rightTree=new Tree();

		node.rightTree.leftTree.setValue(17);
		node.rightTree.rightTree.setValue(30);

		//System.out.println(isBST(node,Integer.MIN_VALUE,Integer.MAX_VALUE));

		ht = new HashMap<Integer,Integer>();

		/* long starttime=System.nanoTime();
  System.out.println(fib(10));
    long endtime = System.nanoTime();
    System.out.println("Time taken: "+(endtime - starttime));
		 */
		int wt[]={15,17,25,55,17,23,27,0,28};
		int val[]= {25,20,15,19,100,23,27,0,28};

		int val2[]={5,4,3,2,1};
		System.out.println(maxProfit(val2));


	}




	public static int maxProfit(int[] array)
	{

		if(array.length<2)
		{
			System.out.println("Invalid array");
			return 0;
		}
		int min=array[0], maxProfit=array[1] - array[0];



		for(int i=0,j=0; i<array.length; i++)
		{

			if(j==0) {
				continue;
			}

			min=Math.min(min,array[i] );



			int potentialProfit= array[i] - min;

			maxProfit = Math.max(maxProfit,potentialProfit );

		}




		return maxProfit;



	}



}