package problems;

import java.util.ArrayList;
import java.util.Scanner;

public class SwapBinaryTreeNode {

	static ArrayList<Integer> nodes=null;
	static ArrayList<Integer> priorityList=null;


	public static void addNode(int index,Tree node)
	{
		//node=new Tree();
		if(index*2>=nodes.size()) {
			node.value=nodes.get(index);
			return;
		}
		/*	node.leftChild=new Tree(nodes.get(index*2));
		addNode(index*2,node.leftChild);
		node.rightChild=new Tree(nodes.get(index*2+1));
		addNode(index*2+1,node.rightChild);*/
		System.out.println(index+","+nodes.get(index));
		node.value=nodes.get(index);
		node.leftChild=new Tree();
		addNode(index*2,node.leftChild);
		node.rightChild=new Tree();
		addNode(index*2+1,node.rightChild);
	}

	public static void main(String args[])
	{
		Scanner in=new Scanner(System.in);

		int numberOfNodes=Integer.parseInt(in.nextLine());
		nodes=new ArrayList();

		nodes.add(-2);
		nodes.add(1);


		if(numberOfNodes <= 0) {
			System.out.println("null");
		}
		else
		{

			for(int i=1;i<=numberOfNodes;i++)
			{
				String tokens[]=(in.nextLine()).split(" ");

				nodes.add(Integer.parseInt(tokens[0]));
				nodes.add(Integer.parseInt(tokens[1]));

			}

		}
		priorityList=new ArrayList<Integer>();

		priorityList.add(-2);
		priorityList.add(1);

		Tree node=new Tree();
		addNode(1,node);
		//swapTree1(node,2);
		traverse(node,1);

		/*
		for(int i=1;i<nodes.size()+8;i++)
		{
			int index=priorityList.get(i);
			//if(index*2>nodes.size()) {
			//	break;
			//}
			if(index!=-1)
			{
				System.out.println(i);
				priorityList.add(nodes.get(index*2));
				priorityList.add(nodes.get(index*2+1));
			}
			else
			{
				priorityList.add(-1);
				priorityList.add(-1);
			}

		}
/*

		for(int i=1;i<priorityList.size();i++)
		{
			System.out.print(priorityList.get(i)+" ");
		}
		System.out.println("\ninorder traversal before......\n"+priorityList.size());
		//	traverse(priorityList,1);
		//int array[]={0,1,2,3,-1,4,-1,5,-1,-1,-1,-1,-1,-1,-1,-1};
		swapTree(2);
		System.out.println("\ninorder traversal......\n"+priorityList.size());
		traverse(priorityList,1);
		 */


	}

	public static void swapTree(int level)
	{
		int index=(int) Math.pow(2,level-1);

		for(int i=index;i<priorityList.size();i++)
		{
			if(i*2<priorityList.size())
			{
				int swapTemp=priorityList.get(i*2);
				priorityList.set(i*2, priorityList.get(i*2+1));
				priorityList.set(i*2+1, swapTemp);
			}

		}



	}

	public static void swapTree1(Tree node,int level)
	{
		level--;

		if(level==0)
		{
			Tree temp=node.leftChild;
			node.leftChild=node.rightChild;
			node.rightChild=temp;
			return;
		}

		swapTree1(node.leftChild,level);	
		swapTree1(node.rightChild,level);



	}


	public static void traverse(Tree node,int index)
	{
		if(node.value==-1)
		{

			return;

		}
		if(node.leftChild!=null) {
			traverse(node.leftChild, index*2);
		}
		System.out.print(node.value+" ");

		if(node.rightChild!=null) {
			traverse(node.rightChild,index*2+1);
		}


	}


}


class Tree
{
	Tree leftChild;
	int level;
	Tree rightChild;
	int value;

	public Tree()
	{}
	public Tree(int value)
	{
		this.value=value;
		this.level=level;
	}

}