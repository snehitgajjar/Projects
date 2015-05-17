import java.util.Comparator;
import java.util.PriorityQueue;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */


class MaxHeap implements Comparator<Integer> {

	@Override
	public int compare(Integer n, Integer m) {

		return (m - n);
	}

}


class Solution {
	public static void main(String[] args) {

		String url="kitten%20pic.jpg";
		int array[]={32,3,53,1,52,32,4,5};

		//  System.out.println(decodeUrl(url));

		PriorityQueue<Integer> pq=returnKMin(array,4);

		for(int num:pq) {
			System.out.print(num+" ");
		}

	}




	public static PriorityQueue<Integer> returnKMin(int[] array,int k) {

		Comparator<Integer> cmp=new MaxHeap();


		PriorityQueue<Integer> pq=new PriorityQueue<Integer>(k,cmp);


		for(int i=0;i<array.length;i++) {

			if(i<k) {
				pq.add(array[i]);
			} else {

				if(array[i] < pq.peek()) {
					pq.poll();
					pq.add(array[i]);
				}

			}
		}


		return pq;
	}





}

