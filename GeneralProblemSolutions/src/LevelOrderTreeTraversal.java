
//LevelOrderTreeTraversal 

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */



class LevelOrderTreeTraversal {

	static int no=0;
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

		printLevelOrderTraversal(node);

	}



	public static void printLevelOrderTraversal(Tree node)
	{

		Stack<Integer> s=new Stack<Integer>();
		Queue<Tree> q=new LinkedList<Tree>();


		q.add(node);

		while(q.size()!=0)
		{
			node= q.poll();

			s.push(node.value);

			if(node.rightTree!=null) {
				q.add(node.rightTree);
			}

			if(node.leftTree!=null) {
				q.add(node.leftTree);
			}




		}

		while(!s.empty())
		{
			System.out.println(s.pop());

		}




	}





}


