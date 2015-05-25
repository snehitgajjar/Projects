
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
//TreeTraversalWithoutRecursion

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Tree {
	public int value;

	Tree leftTree;
	Tree rightTree;

	public void setValue(int value)
	{
		this.value=value;
	}
}
public class TreeTraversalWithoutRecursion {
	public static void main(String[] args) {

		Tree node=new Tree();

		node.setValue(1);
		node.leftTree=new Tree();
		node.rightTree=new Tree();

		node.leftTree.setValue(2);
		node.rightTree.setValue(3);

		node.leftTree.leftTree=new Tree();
		node.leftTree.rightTree=new Tree();

		node.leftTree.leftTree.setValue(4);
		node.leftTree.rightTree.setValue(5);

		node.rightTree.leftTree=new Tree();
		node.rightTree.rightTree=new Tree();

		node.rightTree.leftTree.setValue(6);
		node.rightTree.rightTree.setValue(7);

		Queue<Integer> q=new LinkedList<Integer>();
		traversalWithoutRecursion(node);

	}



	public static void traversalWithoutRecursion(Tree node)
	{
		Stack<Tree> s=new Stack<Tree>();


		s.push(node);

		while(true)
		{
			while(node!=null)
			{
				s.push(node);
				node=node.leftTree;
			}

			if(s.empty()) {
				return;
			}

			node=s.pop();

			System.out.print(node.value+" ");

			node=node.rightTree;




		}


	}


}

