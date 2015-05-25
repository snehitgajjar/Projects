import java.util.HashMap;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */


class knapsack {

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
		int wt[]={2,3,4,5};
		int val[]= {3,7,2,9};

		System.out.println(knapsack(5,4,wt,val));


	}

	static int sum=1,level = 1;


	public static int knapsack(int W,int n, int wt[], int val[])
	{

		int[][] K=new int[n+1][W+1];


		for(int i=0;i<=n;i++)
		{
			for(int j=0;j<=W;j++)
			{

				if(i==0 || j==0)
				{
					K[i][j]=0;
				}
				else if(wt[i-1]<=j)
				{
					K[i][j]=Math.max(val[i -1] + K[i-1][j - wt[i - 1]], K[i -1][j]);
				}
				else
				{
					K[i][j]= K[i -1] [j];
				}


			}

		}

		return K[n][W];

	}



}

