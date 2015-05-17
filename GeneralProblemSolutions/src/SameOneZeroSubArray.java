
/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class SameOneZeroSubArray {
	public static void main(String[] args) {

		int matrix[]={1,1,1,1,1,0,0,0,0,0,0,0,1};


		equalZeroOneSubArray(matrix);

	}

	public static void equalZeroOneSubArray(int[] array) {

		int number_of_ones=0,number_of_zeros=0;

		if(array.length==0) {
			return;
		}

		for (int element : array) {

			if(element==0) {
				number_of_zeros++;
			}
			else {
				number_of_ones++;
			}

		}


		int i=0, j=array.length-1;

		while(i<j){

			int diff=number_of_ones - number_of_zeros;

			if(diff==0) {
				break;
			} else if(diff>0){

				if(array[i]==1) {
					i++;
					number_of_ones -= 1;
				} else if(array[j]==1) {
					j--;
					number_of_ones -= 1;
				}
				else {
					i++;
					number_of_ones -= 1;
				}

			} else if(array[i]==0) {
				i++;
				number_of_zeros -= 1;

			} else if(array[j]==0) {
				j--;
				number_of_zeros -= 1;
			} else {
				j--;
				number_of_ones -= 1;
			}

		}


		while(i<=j) {

			System.out.print(array[i]);
			i++;
		}


	}


}

