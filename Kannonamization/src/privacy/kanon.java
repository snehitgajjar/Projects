package privacy;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class kanon 
{
	static int l=0;
	static List<String> age1;
	static 	List<String> gender1;
	static   	List<String> work1;
	
	public static String genAge(int rowAge,int level)
	{
		int divisor=level*5,lb,ub;
		lb=rowAge/divisor;
		lb=lb*divisor;
		ub=lb+divisor;
		return "["+lb+"-"+ub+")";
	}
	static int op=1;
	public static int levels(int a,int g,int w) throws IOException 
	{
		l=0;
		System.out.println("Hii :"+op); op++;
		  	List<String> records = new ArrayList<String>();
		  	List<String> records1 = new ArrayList<String>();
		  	List<String> age = new ArrayList<String>();
		  	List<String> gender = new ArrayList<String>();
		  	age1 = new ArrayList<String>();
		  	gender1 = new ArrayList<String>();
		  	work1 = new ArrayList<String>();
		  	List<String> work = new ArrayList<String>();
		  
		  	//List<String> gender1 = new ArrayList<String>();
		
		    BufferedReader reader = new BufferedReader(new FileReader("adults.txt"));
		    BufferedReader reader1 = new BufferedReader(new FileReader("adults.txt"));
		    String line1 = null;
		   // genAge(32,3);
		    String line = null;
		/*    
		    while((line = reader.readLine())!=null)
		    {
			
			int i=0;
		    StringTokenizer st2 = new StringTokenizer(line,", ");
			String org = "";
			while (st2.hasMoreTokens()) 
				{
					String part = st2.nextToken();
					if(i==0 || i==1|| i==9)
					{
						org = org + part;
					}
				    i++;
				}
			records.add(org);
			}
		    
		    
		    
		    reader.close();
		    */
		    
		    
		    
		    
		    while((line1 = reader1.readLine())!=null)
		    {
				
				int j=0;
			//    StringTokenizer st1 = new StringTokenizer(line1,", ");
			  String tempString[]=line1.split(", ");  
				String org1 = "";
				
				
		/*		while (st1.hasMoreTokens()) 
					{
					
						String part1 = st1.nextToken();
			//			System.out.println(work.size()+" "+part1);
						int x=0;
						if(j==0)
						{
							age.add(part1);
						}
						else if(j==1)
						{
							 
							work.add(part1);
						}
						else if(j==9)
						{
							gender.add(part1);
						}
					    j++;
					    x++;
					} */
				
				age.add(tempString[0]);
				work.add(tempString[1]);
				gender.add(tempString[9]);
				l++;
			}
		    reader1.close();
	//	    System.out.println(work.size());
/*----------------------------Defining levels-----------------------------------*/		    
		    System.out.println("Hii 1:");
		    if(a==0)
		    {
		    	for(int f=0;f<l;f++)
		    	{
		    		age1.add(age.get(f));
		    	}
		    	//Collections.copy(age1,age);
		    }
		    else
		    {
		    	for(int f=0;f<l;f++)
			    {
			    	//String pb= genAge(Integer.parseInt(age.get(f)),a);
			    	age1.add(genAge(Integer.parseInt(age.get(f)),a));
			    }
		    }
		    
		    
		    
		    if(g==0)
		    {
		    	for(int f=0;f<l;f++)
		    	{
		    		gender1.add(gender.get(f));
		    	}
		    	//Collections.copy(age1,age);
		    }
		    else
		    {
		    	for(int f=0;f<l;f++)
			    {
			    	//String pb= genAge(Integer.parseInt(age.get(f)),a);
			    	gender1.add("*");
			    }
		    }
		    
		    
		    
		    if(w==0)
		    {
		    	//System.out.println(work.size());
		    	for(int f=0;f<l;f++)
		    	{
		    		work1.add(work.get(f));
		    	}
		    }
		    else if(w==1)
		    {
		    	for(int f=0;f<l;f++)
			    {
		    		if((work.get(f)).equals("Self-emp-not-inc") || (work.get(f)).equals("Self-emp-inc"))
		    		{
		    			work1.add("Self-emp");
		    		}
		    		else if((work.get(f)).equals("Federal-gov") || (work.get(f)).equals("State-gov") || (work.get(f)).equals("Local-gov"))
		    		{
		    			work1.add("Government");
		    		}
		    		else if((work.get(f)).equals("Private"))
		    		{
		    			work1.add("Private");
		    		}
		    		else
		    		{
		    			work1.add("Unemployed");
		    		}
			    }
		    }
		    else
		    {
		    	for(int f=0;f<l;f++)
		    	{
		    		work1.add("Work");
		    	}
		    }
		   // System.out.println(age1.size()+" "+work1.size()+" "+gender.size());
		    for(int h=0;h<l;h++)
		    {
		    	
		  //  	System.out.println("Hii :"+age1.get(h) + work1.get(h) + gender1.get(h));
		    	records1.add(age1.get(h) + work1.get(h) + gender1.get(h));
		    }
		    //System.out.println(records1);
		    
		    ArrayList<Integer> anArray =new ArrayList<Integer>();
		   // System.out.println(l);
		    String ttyl="";
		   int min=0;
		   int length=records1.size();
		    for(int j=0;j<length;j++)
		    {
		    	ttyl=records1.get(j);
		    	int n=1;
		    	for(int m=j+1;m<records1.size();)
		    	{
		    //		System.out.println(ttyl);
		    		if((records1.get(m)).equals(ttyl))
		    		{
		    			
		    			records1.remove(m);
		    			n++;
		    		}
		    		else
		    		{
		    			m++;
		    		}
		    		
		    	}
		    	length=records1.size();
		 //   	if(n==1)
		  //  	 System.out.println("daasfa "+records1.get(j));
		    	anArray.add(n);
		    }
		//    ------------------------------------------------
		   
		    
		    
		   
		    int small = anArray.get(0);
		    int index;
		    for (int i = 1; i < anArray.size(); i++)
		    {
		        if (anArray.get(i) < small)
		        {
		            small = anArray.get(i);
		            index = i;
		        }
		    }
		    return small;
	}   
		    
	public static void main(String[] arg) throws IOException
	{
	List<Double> records3 = new ArrayList<Double>();
	int a,g,w;
	System.out.println("Enter an int : ");
	int q;
	double pres,u,v;
	Scanner scanIn = new Scanner(System.in);
	double max=0.0;
	int maxA=0,maxG=0,maxW=0;
	q = scanIn.nextInt();
		    for(a=0;a<=6;a++)
		    {
		    	for(g=0;g<=1;g++)
		    	{
		    		for(w=0;w<=2;w++)
		    		{
		    			int temp=levels(a,g,w);
		    		//	System.out.println("afdji :"+temp);
		    			if(q<=temp)
		    			{
		    				u=((a+1)/7)+1+((w+1)/3);
		    				v=l*3;
		    				pres=1-(u/v);
		    				System.out.println("v is :"+ v + "u is :"+ u + "L is :"+ l+" "+pres);
		    				if(pres>max)
		    				{
		    					max=pres;
		    					maxA=a;maxG=g;maxW=w;
		    					
		    				}
		    				records3.add(pres);
		    			}
		    		}
		    	}
		    }
		    
		    System.out.println(maxA+" "+maxG+" "+maxW);
		    levels(maxA,maxG,maxW);
		    
		    
		    BufferedReader br=new BufferedReader(new FileReader(new File("adults.txt")));
		    PrintWriter pw=new PrintWriter(new FileWriter(new File("adults.csv")));
		    String line="";
		    int i=0;
		   while((line=br.readLine())!=null)
		   {
			  String temp[]=line.split(", ");
			  
			  pw.append(age1.get(i));
			 
			  
			  for(int i1=1;i1<temp.length;i1++)
			  {
				  pw.append(",");
				  
				  if(i1==1)
				  {
					  System.out.println(work1.get(i));
					  pw.append(work1.get(i)+",");
				  }
				  else if(i1==9)
				  {
					  pw.append(gender1.get(i));
				  }
				  else
				  {
					  pw.append(temp[i1]);
				  }
			  }
			  i++;
			  pw.append("\n");
		   }
		   pw.close();
		   br.close();
		    
		   
		    
	}
}