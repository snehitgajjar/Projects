

//public class IsCompleteEqualNodeBT {


/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */



class IsCompleteEqualNodeBT {

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

		int[] array={1,2,3,4,5,6,2};
		//System.out.println(findFirstDuplicate(array));

		boolean b=isSame(node);
		System.out.println("Result"+ b );
		System.out.println("leftSum: "+ sum +", rightSum: "+level);
		System.out.println( b && (sum==(Math.pow(2,level-1)) -1));

	}

	static int sum=1,level = 1;

	public static boolean isSame(Tree node)
	{






		if(node.leftTree!=null && node.rightTree!=null)
		{
			sum +=2;
			level += 1;
			return true && isSame(node.leftTree) && isSame(node.rightTree);
		}
		else if(node.leftTree==null && node.rightTree==null)
		{
			//    System.out.println("hi: "+node.value);
			return true;
		}
		else
		{

			return false;
		}


	}



}

