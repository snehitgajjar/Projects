
//public class PrintTreeWithAnyNumberOfNodes {

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class TreeWithNNodes{

	int value;
	ArrayList<TreeWithNNodes> nodeList =new ArrayList<TreeWithNNodes> ();

}


class PrintTreeWithAnyNumberOfNodes {
	public static void main(String[] args) {

		TreeWithNNodes node =new TreeWithNNodes();

		TreeWithNNodes node01 =new TreeWithNNodes();
		TreeWithNNodes node02 =new TreeWithNNodes();
		TreeWithNNodes node03 =new TreeWithNNodes();

		TreeWithNNodes node011 =new TreeWithNNodes();
		TreeWithNNodes node012 =new TreeWithNNodes();

		TreeWithNNodes node021 =new TreeWithNNodes();

		TreeWithNNodes node031 =new TreeWithNNodes();

		node.value=1;
		node01.value=2;
		node02.value=3;
		node03.value=4;

		node011.value=5;
		node012.value=6;
		node021.value=7;
		node031.value=8;

		node.nodeList.add(node01);
		node.nodeList.add(node02);
		node.nodeList.add(node03);

		node01.nodeList.add(node011);
		node01.nodeList.add(node012);
		node02.nodeList.add(node021);
		node03.nodeList.add(node031);



		printTree(node);
	}


	public static void printTree(TreeWithNNodes node) {

		Queue<TreeWithNNodes> q = new LinkedList<TreeWithNNodes> ();

		q.add(node);


		while(!q.isEmpty()) {

			node = q.poll();

			System.out.print(node.value+" ");
			for(int i=0; i<node.nodeList.size() ; i++) {

				q.add(node.nodeList.get(i));
			}
		}


	}






}


