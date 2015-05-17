
/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class SameSumPartition {
	public static void main(String[] args) {

		int matrix[]={5,5};


		System.out.println(canBePartitioned(matrix));

	}

	public static boolean canBePartitioned(int[] array) {

		int sum=0,rsum=0;

		if(array.length%2==1) {
			return false;
		}

		for (int element : array) {
			sum += element;
		}


		for (int element : array) {

			rsum += element;

			if(rsum ==(sum - rsum)) {

				return true;
			}


		}

		return false;
	}


}

