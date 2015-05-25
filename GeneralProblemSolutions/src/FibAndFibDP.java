
//public class FibAndFibDP {

import java.util.HashMap;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */



class FibAndFibDP {

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

		double starttime=System.nanoTime();
		System.out.println(fib(50));
		double endtime = System.nanoTime();
		System.out.println("Time taken: "+(endtime - starttime)/1000000000);



		starttime=System.nanoTime();
		System.out.println(fibDP(50));
		endtime = System.nanoTime();
		System.out.println("Time taken for DP: "+(endtime - starttime)/1000000000);

	}

	static int sum=1,level = 1;


	public static int fib(int n)
	{

		if(n<=2)
		{
			return 1;
		}
		else
		{
			return fib(n-1) + fib(n-2);
		}


	}


	public static int fibDP(int n)
	{
		if(ht.containsKey(n)) {
			return ht.get(n);
		}
		if(n<=2)
		{
			return 1;
		}
		else
		{
			int f=fib(n-1) + fib(n-2);
			ht.put(n,f);
			return f;
		}


	}



}


