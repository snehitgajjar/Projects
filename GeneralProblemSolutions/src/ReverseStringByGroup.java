
//public class ReverseStringByGroup {


/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */



class ReverseStringByGroup {

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

		//System.out.println(isBST(node,Integer.MIN_VALUE,Integer.MAX_VALUE));

		//    System.out.println(compress("aa"));

		int[] array={1,2,3,4,5,6};
		array=reverseArrayByGroupSize(array, 3);

		for(int i:array)
		{
			System.out.print(i+" ");
		}
	}




	public static int[] reverseArrayByGroupSize(int[] charArray,int n)
	{
		//char[] charArray=str.toCharArray();

		if(n==0 || n==1) {
			return charArray;
		}


		int front=0, end = n-1;


		while(front<=end)
		{
			int temp = charArray[end];
			charArray[end] = charArray[front];
			charArray[front]=temp;

			front++; end--;

		}

		return charArray;
	}




}


