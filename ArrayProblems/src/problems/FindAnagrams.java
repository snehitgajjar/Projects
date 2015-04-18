package problems;

public class FindAnagrams {

	public static void main(String args[])
	{
		System.out.println("Get ans: "+isAnagram("scholmaster","theclassromo"));
	}

public static boolean isAnagram(String s,String t)
{
	if(s.length()!=t.length()) return false;
	
	int record[]=new int[256];
	
	for(int i=0;i<s.length();i++)
	{
		int h=s.charAt(i);
		++record[h];
		
	}
	
	for(int i=0;i<t.length();i++)
	{
		int h=t.charAt(i);
		if(record[h]==0) return false;
		
		--record[h];
		
	}
	
		
	return true;
}


}