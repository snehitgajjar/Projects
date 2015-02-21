package common;

import java.io.Serializable;
public class Artist implements Serializable 
{
	public String id, name, location;
	public String[] songs;
	public String toString () 
	{
		StringBuilder out = new StringBuilder(String.format("%s<SEP >%s<SEP >%s", id , name , location ));
		for (String s : songs) 
		{
			out.append("<SEP >"); out.append(s);	
		}
		return out.toString(); 

	}
}
