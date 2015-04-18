package problems;

import java.io.ObjectInputStream.GetField;

public class ReplaceSpace {

	
	public static void main(String args[])
	{
		System.out.println("Answer: "+getReplaced("What is this"));
		
	}
	
	
	
	
	
	
	public static String getReplaced(String s)
	{
		StringBuffer sb=new StringBuffer();
		
		for(int i=0;i<s.length();i++)
		{
			if(s.charAt(i)==' ')
			{
				sb.append("%20");
			}
			else
			{
				sb.append(s.charAt(i));
			}
		}
		
		return sb.toString();
	}

	
}

