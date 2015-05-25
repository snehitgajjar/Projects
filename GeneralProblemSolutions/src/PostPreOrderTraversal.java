
//public class PostPreOrderTraversal {


/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */




class PostPreOrderTraversal {

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

		//    System.out.println(compress("aa"));

		//int[] array={1,2,3,4,5,6};
		postorderTraversal(node);

	}




	public static void preorderTraversal(Tree node)
	{
		//char[] charArray=str.toCharArray();
		if(node==null) {
			return;
		}

		System.out.println(node.value);

		if(node.leftTree!=null)
		{
			preorderTraversal(node.leftTree);
		}

		if(node.rightTree!=null)
		{
			preorderTraversal(node.rightTree);
		}



	}

	public static void postorderTraversal(Tree node)
	{
		//char[] charArray=str.toCharArray();
		if(node==null) {
			return;
		}



		if(node.leftTree!=null)
		{
			postorderTraversal(node.leftTree);
		}

		if(node.rightTree!=null)
		{
			postorderTraversal(node.rightTree);
		}
		System.out.println(node.value);


	}



}

