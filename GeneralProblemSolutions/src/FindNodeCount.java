

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */




public class FindNodeCount {

	static int count=0;
	public static void main(String[] args) {

		Tree node=new Tree();

		node.setValue(9);
		node.leftTree=new Tree();
		node.rightTree=new Tree();

		node.leftTree.setValue(6);
		node.rightTree.setValue(15);

		node.leftTree.leftTree=new Tree();
		node.leftTree.rightTree=new Tree();

		node.leftTree.leftTree.setValue(4);
		node.leftTree.rightTree.setValue(7);

		node.rightTree.leftTree=new Tree();
		node.rightTree.rightTree=new Tree();

		node.rightTree.leftTree.setValue(9);
		node.rightTree.rightTree.setValue(20);

		findCount(node,21,25);

		System.out.println(count);

	}




	public static void findCount(Tree node,int min,int max)
	{

		if(node!=null) {
			System.out.println(node.value);
		}
		if(node==null)
		{
			return;
		}

		if(node.value<min)
		{
			findCount(node.rightTree,min,max);
		} 
		else if(node.value>max)
		{
			findCount(node.leftTree,min,max);
		}
		else if(node.value>min && node.value<max)
		{        
			count +=1;   
			findCount(node.leftTree,min,max);
			findCount(node.rightTree,min,max);

		}
		else 
		{
			return;
		}  


		return;
	}








}


