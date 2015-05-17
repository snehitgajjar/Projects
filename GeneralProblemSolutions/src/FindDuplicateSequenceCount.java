import java.util.Stack;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class FindDuplicateSequenceCount {
	public static void main(String[] args) {

		String str="YRGGRRGGRY";

		System.out.println(retrunMaximumColorSeq(str.toCharArray()));


	}

	public static int retrunMaximumColorSeq(char[] array) {

		Stack<Character> stack=new Stack<Character>();

		int i=0;
		int count=0;
		stack.push(array[i]);

		for(i=1;i<array.length;i++) {

			if(stack.empty()) {
				stack.push(array[i]);
			}
			else {

				char c = stack.peek();

				if(c==array[i]) {
					count++;
					stack.pop();
				}
				else {

					stack.push(array[i]);
				}

			}


		}

		return count;
	}


}

