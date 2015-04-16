package problems;

import java.math.BigInteger;

public class BitOperations {

	public static void main(String args[])
	{
		int n=Integer.parseInt("10000000000",2);
		int m=Integer.parseInt("10101",2);
		int i=2, j=6;
		
		
		
		int ans=updateBits(n, m, i, j);
		
		
	}
	
	
	public static int updateBits(int n,int m,int i,int j)
	{
		
		int max=~0;
		System.out.println("Max: "+max);
		int left=max-((1<<j)-1);
		System.out.println("(1<<j): "+(1<<j));
		System.out.println("(1<<j)-1: "+((1<<j)-1));
		System.out.println("max-(1<<j)-1: "+(max-((1<<j)-1)));
		int right=((1<<i)-1);
		System.out.println("(1<<i)-1: "+((1<<i)-1));
		int mask=left | right;
		System.out.println("left | right: "+(left | right));
		System.out.println("(n & mask)|(m<<i): "+((n & mask)|(m<<i)));
		return (n & mask)|(m<<i);
		
		
		
		
	}
	
}
