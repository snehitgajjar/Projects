
/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class FindKFromRotatedSortedArray {
	public static void main(String[] args) {

		int[] array = {2,3,4,5,1};

		System.out.println(supportFunction(array,1));


	}


	public static int supportFunction(int[] array,int n) {

		int find=findFromSortedRotated(array,n,0,array.length-1);

		return find;
	}


	public static int findFromSortedRotated(int[] array,int n,int start,int end) {

		int result=0;

		if(array==null || array.length==0) {

			return 0;
		}

		if(array.length==1 && n==array[0]) {
			return array.length;
		}

		int front=start,rear=end;


		int pivot = (rear - front)/2;

		int pivotElement=array[pivot];

		if(array[pivot]==n) {
			return pivot+1;
		}

		if(array[front]==n) {
			return front+1;
		} else if(array[rear]==n) {
			System.out.println("here1"+rear);
			return rear+1;
		}


		if(pivotElement>n && array[front] > array[rear] && array[front]<n) 
		{
			System.out.println("here");
			result=findFromSortedRotated(array,n,front,pivot-1);

		}
		else if(pivotElement>n && array[front] > array[rear] && array[front]>n) 
		{

			result = findFromSortedRotated(array,n,pivot+1,rear);

		}
		else if(pivotElement<n && array[front] > array[rear] && array[front]>n)
		{
			result = findFromSortedRotated(array,n,pivot+1,rear);

		}
		else if(pivotElement<n && array[front] > array[rear] && array[front]<n)
		{
			result = findFromSortedRotated(array,n,front,pivot - 1);

		}
		else if(pivotElement>n && array[front]< array[rear] && array[front]>n)
		{
			result = findFromSortedRotated(array,n,pivot + 1,rear);
		}
		else if(pivotElement>n && array[front]< array[rear] && array[front]<n)
		{
			result = findFromSortedRotated(array,n,front,pivot-1);
		}
		else if(pivotElement<n && array[front]< array[rear] && array[front]<n)
		{
			result = findFromSortedRotated(array,n,pivot + 1,rear);
		}
		else if(pivotElement<n && array[front]< array[rear] && array[front]>n)
		{
			result = findFromSortedRotated(array,n,front,pivot - 1);
		}



		return result;

	}



}

