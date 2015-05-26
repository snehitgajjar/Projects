
/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */




class MoveAllZeroToFront {
	public static void main(String[] args) {

		int[] array = {1,2,3,0,0,0,0,4,5};
		int arr[] = moveAllZeroToFront(array);

		for(int i : arr) {
			System.out.print(i +" ");
		}

	}


	public static int[] moveAllZeroToFront(int[] array) {

		int i,j;



		for( i=j=array.length - 1;i>=0 ; i--) {

			if(array[i]== 0 ) {
				continue;
			}


			array[j] = array[i];
			j--;

		}


		while(j>=0) {

			array[j] = 0;
			j--;
		}


		return array;
	}







}

