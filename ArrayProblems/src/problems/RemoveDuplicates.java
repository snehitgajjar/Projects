package problems;

public class RemoveDuplicates {
	
	
	public static void main(String args[])
	{
		String str="a";
		System.out.println("String without duplicate: "+removeDuplicate(str));
		
		
	}
	
	




public static String removeDuplicate(String str)
{
	char strChar[]=str.toCharArray();
	
	if(strChar==null) return null;
	
	if(strChar.length<2) return str;
	
	boolean check[]=new boolean[256];
	for(int i=0;i<check.length;i++)
		check[i]=false;
	
	int point=1;
	check[strChar[0]]=true;
	
	for(int i=1;i<strChar.length;i++)
	{
		if(!check[strChar[i]])
		{
			strChar[point]=strChar[i];
			++point;
			check[strChar[i]]=true;
			
		}
		
	}
	strChar[point]='\0';
	
	str="";
	for(int i=0;i<point;i++)
		str=str+strChar[i];
	return str;
}




}