
/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class RemoveSubString {
	public static void main(String[] args) {

		String url="kitten%20pic.jpg";


		System.out.println(decodeUrl(url));
		
			

	}





	public static String decodeUrl(String url) {

		char[] result=new char[url.length()];

		if(url==null || url.length()==0) {
			return new String(result);
		}

		int i=0,j=0;

		for(j=0;j<url.length();)
		{
			// System.out.println(new String(result));
			if(url.charAt(j)=='%') 
			{

				if(url.charAt(j+1)=='2') 
				{
					if(url.charAt(j+2)=='0') 
					{
						result[i]=' ';
						i++;
						j =j + 3;
					}

				} 
				else if(url.charAt(j+1)=='3') 
				{

					if(url.charAt(j+2)=='A') 
					{
						result[i]='?';
						i++;
						j = j + 3; 

					}
					else if(url.charAt(j+2)=='D') 
					{
						result[i]='.';
						i++;
						j = j + 3; 

					}

				}


			}
			else {

				result[i]= url.charAt(j);
				i++;j++;
			}


		}


		String s = new String(result);

		return s;

	}


}

