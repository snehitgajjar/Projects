
/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */



class CheckBST {

	static int no=0;
	public static void main(String[] args) {

		Tree node=new Tree();

		node.setValue(15);
		node.leftTree=new Tree();
		node.rightTree=new Tree();

		node.leftTree.setValue(17);
		node.rightTree.setValue(25);

		node.leftTree.leftTree=new Tree();
		node.leftTree.rightTree=new Tree();

		node.leftTree.leftTree.setValue(5);
		node.leftTree.rightTree.setValue(13);

		node.rightTree.leftTree=new Tree();
		node.rightTree.rightTree=new Tree();

		node.rightTree.leftTree.setValue(17);
		node.rightTree.rightTree.setValue(30);

		System.out.println(isBST(node,Integer.MIN_VALUE,Integer.MAX_VALUE));

	}




	public static boolean isBST(Tree node,int min,int max)
	{

		if(node==null)
		{
			return true;
		}
		else if(min<node.value && node.value<max)
		{
			return isBST(node.leftTree,min,node.value) && isBST(node.rightTree,node.value,max);

		}
		else
		{
			return false;
		}




	}





}

