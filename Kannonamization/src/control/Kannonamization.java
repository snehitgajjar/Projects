package control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.opencsv.CSVWriter;



public class Kannonamization {
	
	private static final ArrayList<String> String = null;
	private static ArrayList<String> qIdentifierMain,gender,age,workType;
	private static ArrayList<String> genderTemp,ageTemp,workTypeTemp;
	int min=1000000;
	
	
	
	/*
	 *  public Kannonamization() is a constructor which ask user for value of k and then
	 *  call getK to compute k of given data. if it is same it returns original data other-wise
	 *  creates different combinations
	 */
	
	public Kannonamization()
	{
		ArrayList<String> newStringList;
		Scanner scan=new Scanner(System.in);
		getQuassicolumn("./adults.txt");
		System.out.print("Enter the value of k: ");
		int k=scan.nextInt();
		
		int workLevel=3,genderLevel=2,ageLevel=7;
		int maxPreWL=0,maxPreGL=0,maxPreAL=0;
		int count=0,r=0;
		double max=0.0;
	
		if(getK(qIdentifierMain)==k)
		{
			writeCVSFile(maxPreAL, maxPreWL, maxPreGL);
		}
		else
		{
						
			
			for(int i=0;i<ageLevel;i++)
			{
				

				changeAge(i);
				for(int j=0;j<genderLevel;j++)
				{
					
					changeGender(j);
					for(int k1=0;k1<workLevel;k1++)
					{
						// Here all different level combinations are generated.
							changeWorkType(k1);
				
							
							newStringList=new ArrayList<String>();
							
							for(int q=0;q<genderTemp.size();q++)
							{
								newStringList.add(ageTemp.get(q)+" "+workTypeTemp.get(q)+" "+genderTemp.get(q));
							}
							int k2=getK(newStringList); //Computes k for given level combination
							
							if(k2>=k)
							{
								count++;
								double pre=getPrecision(i,j,k1); //Finds precision when computed k is equals or greater 
																 //than user given K
								if(max<pre) // Finds maximum precision
								{
									max=pre;
									maxPreAL=i;maxPreGL=j;maxPreWL=k1;
								}
							}
						
					}
					
				}
			}
			writeCVSFile(maxPreAL, maxPreWL, maxPreGL); //Write final data to new .csv file
		}
	}
	
	
	
	

	/*
	 *  Below method reads data from input file and stores quasi-identifiers in ArrayList.
	 */
	public void getQuassicolumn(String path)
	{
		qIdentifierMain=new ArrayList<String>();
		age=new ArrayList<String>();
		workType=new ArrayList<String>();
		gender=new ArrayList<String>();
		try
		{
			File file=new File(path);
			BufferedReader br=new BufferedReader(new FileReader(file));
			String line;
			while((line=br.readLine())!=null)
			{
				String lines[]=line.split(", ");
				age.add(lines[0]);
				workType.add(lines[1]);
				gender.add(lines[9]);
				
				
					qIdentifierMain.add(lines[0]+" "+lines[1]+" "+lines[9]);		
			}

		}
		catch(Exception e)
		{
			System.out.println("Exception occered during reading file : "+e);
		}
		
		
	}
	
	
	
	
	/*
	 *  Below method computes K from given data-set of quasi-identifiers
	 */
	public int getK(ArrayList<String> qIdentifier)
	{
		int min=100000000;
		List<String> list = qIdentifier;
		

		Set<String> unique = new HashSet<String>(list);
		for (String key : unique) {
			int count=Collections.frequency(list, key);
		   if(min>count)
		   {
			   min=count;
		   }
		}
		return min;
	}
	
	
	
	
	
	/*
	 *  Below method takes three integer which are levels of quasi-identifiers as input.
	 *  and computes precision as given in class-slides.
	 */
	
	public double getPrecision(int level1,int level2,int level3)
	{
		double numerator=((level1+1)/(double)7)+((level2+1)/(double)2)+((level3+1)/(double)3);
		double precision=1-(numerator/(age.size()*3));	
			
		return precision;
	}
	

	
	
	
	/*
	 *  Below function generates different age group based on given level and row age.
	 */
	
	public String generalizeAge(int rowAge,int level)
	{
		int divisor=level*5;
		int lowerBound= rowAge/divisor;
		lowerBound=lowerBound*divisor;
		
		int upperBound=lowerBound+divisor;
		String limit=new String("["+lowerBound+"-"+upperBound+")");
		
		return limit;
	}
	
	
	
	/*
	 *  Below function update ages as per given level
	 */
	
	public  void changeAge(int level)	{
		
		ageTemp=new ArrayList<String>();
			for(int i=0;i<age.size();i++)
			{
				
				if(level!=0)
				{
					Integer age1=Integer.parseInt(age.get(i).trim());
					
					String temp = generalizeAge(age1, level);
					ageTemp.add(temp);
				}
				else
				{
					ageTemp=age;
				}
			}
		
		
	}
	
	
	
	/*
	 *  Below function update genders as per given level
	 */
	public  void changeGender(int level)	{
		genderTemp=new ArrayList<String>();
		if(level!=0)
		{
			for(int i=0;i<gender.size();i++)
			{
				genderTemp.add("*");
			}
		}
		else
		{
			genderTemp=gender;
		}
	}
	
	
	/*
	 *  Below function update work-type as per given level
	 */
	
	public  void changeWorkType(int level)	
	{
	
		workTypeTemp=new ArrayList<String>();
		if(level==1)
		{
			for(int i=0;i<workType.size();i++)
			{
				if(workType.get(i).equals("Self-emp-inc") || workType.get(i).equals("Self-emp-not-inc"))
				{
					workTypeTemp.add("Self employed");
				}
				else if(workType.get(i).equals("Federal-gov") || workType.get(i).equals("State-gov") ||workType.get(i).equals("Local-gov"))
				{
					workTypeTemp.add("Government");
				}
				else if(workType.get(i).equals("Without-pay"))
				{
					workTypeTemp.add("Unemployed");
				}
				else
				{
					workTypeTemp.add(workType.get(i));
				}
			}
		}
		else if(level==2)
		{
			for(int i=0;i<workType.size();i++)
			{
			
					workTypeTemp.add("Work-class");
				
			}
		}
		else
		{
			workTypeTemp=workType;
		}
	}

	
	
	
	/*
	 *  Below function write final data into .csv file.
	 *  
	 */
	
	
	
	public void writeCVSFile(int ageLevel,int workLevel,int genderLevel)
	{
		changeAge(ageLevel);
		changeWorkType(workLevel);
		changeGender(genderLevel);
		try
		{
			String csv = "data.csv";
			
			
		      FileWriter writer =new FileWriter(csv,false);
		      BufferedReader br=new BufferedReader(new FileReader(new File("./adults.txt")));
		      String line="";
		      int i=0;
		      while((line=br.readLine())!=null)
		      {
		      //Create record
		      String [] record = line.split(", ");
		      record[0]=ageTemp.get(i);
		      record[1]=workTypeTemp.get(i);
		      record[9]=genderTemp.get(i);
		      //Write the record to file
		      writer.append(record[0]);
		      for(int j=1;j<record.length;j++)
		      {
		    	  writer.append(",");
		    	  writer.append(record[j]);
		      }
		        writer.append("\n");
		      
		        i++;
		      }
		    //close the writer and reader
		      writer.close();
		      br.close();
		}
		catch(Exception e)
		{
			System.out.println("Error occured while writing CVS file "+e);
		}
	}
	
	
	
	
	public static void main(String args[])
	{
		Kannonamization k=new Kannonamization(); // Create constructor
		System.out.println("Output file data.csv has been created successfully.");
	}
	
	
	
}







