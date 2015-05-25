
//FindNdecimalDivision

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */



class FindNdecimalDivision {

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

		System.out.println(findNDecimalNumber(6,3,3));

	}




	public static String findNDecimalNumber(int num, int denom, int N)
	{
		StringBuilder sb=new StringBuilder();


		if(N==0) {
			return sb.append(num/denom).toString();
		}


		sb.append(num/denom);
		sb.append(".");

		num=num%denom * 10;
		while(N!=0)
		{
			sb.append(num/denom);
			num= num% denom * 10;

			N--;

		}


		return sb.toString();

	}




}

