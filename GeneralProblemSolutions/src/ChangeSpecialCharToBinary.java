
//ChangeSpecialCharToBinary {


/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */



class ChangeSpecialCharToBinary {

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

		replaceSignWithBinary("01?1???","",0);
	}




	public static void replaceSignWithBinary(String bitString, String currentString, int currentIndex)
	{
		int length= bitString.length();

		if(currentIndex== length)
		{
			System.out.println(currentString);
			return;

		}


		if(bitString.charAt(currentIndex)=='?')
		{
			replaceSignWithBinary(bitString, currentString+"0",currentIndex+1);
			replaceSignWithBinary(bitString, currentString+"1",currentIndex+1);
		}
		else
		{

			replaceSignWithBinary(bitString, currentString+bitString.charAt(currentIndex),currentIndex+1);
		}

	}




}


