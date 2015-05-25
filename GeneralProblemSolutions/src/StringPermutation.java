
/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */




class StringPermutation {

	static int no=0;
	public static void main(String[] args) {


		permutation("","ABCD");

	}




	private static void permutation(String prefix, String str){
		int n = str.length();
		if (n == 0) {
			System.out.println(prefix);
		} else {
			for (int i = 0; i < n; i++){
				//   System.out.println("Hi: "+str.substring(0,i));
				permutation(prefix + str.charAt(i), 
						str.substring(0, i) + str.substring(i+1));
			}
		}
	}




}

