package problems;

import java.util.Date;
import java.util.Stack;


public class TowerOfHanoi {

	
	
	public static void main(String args[])
	{
		int n=5;
		
		Tower[] towers=new Tower[n];
		Date d=new Date();
		long t1=d.getTime();
		System.out.println("\nExecution time: "+(t1));
		for(int i=0;i<3;i++) towers[i]=new Tower(i);
		for(int i=n-1;i>=0;i--) towers[0].add(i);
		towers[0].moveDisks(n, towers[2],towers[1]);
		Date dd=new Date();
		long t2=dd.getTime();
		
		
		System.out.println("\nExecution time: "+(t2));
		System.out.println("\nExecution time: "+(double)(t2-t1)/1000);
		
		
	}
	
	
	
	
	
	
}

class  Tower
{
	private Stack<Integer> disks;
	private int index;
	/**
	 * @param i
	 */
	
	public Tower(int i)
	{
		disks = new Stack<Integer>();
		index=i;
		
		
	}
	
	public int index()
	{
		return index;
	}
	
	public void add(int d)
	{
		if(!disks.isEmpty()&& disks.peek()<=d)
		{
			System.out.println("Error placing disk"+d);
		}
		else
		{
			disks.push(d);
			
		
		}
	}
	
	
	public void moveTopTo(Tower t)
	{
		int top=disks.pop();
		t.add(top);
		
		System.out.println("Move disk "+top+" from "+index()+" to "+t.index());
		
	}
	
	
	public void print()
	{
		System.out.println("Contents of the tower "+index());
		for(int i=disks.size();i>=0;i--)
		{
			System.out.println(" "+ disks.get(i));
		}
		
	}
	
	public void moveDisks(int n,Tower destination,Tower buffer)
	{
		if(n>0)
		{
			moveDisks(n-1, buffer, destination);
			moveTopTo(destination);
			buffer.moveDisks(n-1, destination, this);
		}
		
	}
}
