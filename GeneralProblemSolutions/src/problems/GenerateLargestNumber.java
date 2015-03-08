package problems;
import java.util.ArrayList;


public class GenerateLargestNumber {
	
	public static void main(String[] args)
	{
		int numbers[]={3,30,34,5,9};
		
		
		
	
		
		System.out.println(new GenerateLargestNumber().largesNumber(numbers));
	}
	
	
	public String largesNumber(int[] num)
	{
		
		ArrayList<Integer> myArray=new ArrayList<Integer>();
		int i=0,j=0;
	
		while(i<num.length)
		{
			char[] temp=(num[i]+"").toCharArray();
			
			for(int m=0;m<temp.length;m++,j++)
			{
				
				String s=temp[m]+"";
				System.out.println(s);
				myArray.add(Integer.parseInt(s));
			}
			i++;

		}
		int finalArray[]=new int[myArray.size()];
		System.out.println();
		for(int m=0;m<myArray.size();m++)
		{
			finalArray[m]=myArray.get(m);
			System.out.print(finalArray[m]);
			System.out.println();
		}
		quickSort(finalArray);
		
		String str="";
		for(int m=finalArray.length-1;m>-1;m--)
		{
			str=str+finalArray[m];
			
		}
		
		
		return str;
	}
	
	
	
		public void quickSort(int[] number)
		{
			quickSort(number, 0, number.length-1);
			
		}
		
		public void quickSort(int[] number, int start,int end)
		{
			int i=start,k=end;
			
			
			if(end-start>=1)
			{
				int pivot=number[start];
				while(k>i)
				{
					
					while(number[i]<=pivot && i<=end && k>i)
						i++;
					
					while(number[k]>pivot && k>=start && k>=i)
						k--;
					
					if(k>i)
						swap(number,i,k);
				}
					swap(number,start,k);
					quickSort(number,start, k-1);
					quickSort(number,k+1, end);
				
			}
			else
			{
				return;
			}
			
			
		}
	
		
		
		public void swap(int[] array,int a,int b)
		{
			int temp = array[a];           // store the first value in a temp
			array[a] = array[b];      // copy the value of the second into the first
			array[b] = temp;
			
		}
	
}
