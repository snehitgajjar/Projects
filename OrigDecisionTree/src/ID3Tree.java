import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class ID3Tree {
	
	
	static double theta=0.40; // global value of theta
	static ArrayList<Boolean[]> data=new ArrayList<Boolean[]>(); //Global dataset
	
	/**
	 * @param filename,percentage	Here String parameter take filename and double takes percentage for which we
	 * want to read file.
	 * @return ArrayList<Boolean[]> Below method returns ArrayList of boolean as it reads binary data in file.
	 */
	
	public static ArrayList<Boolean[]> readFile(String filename,double percentage) throws Exception {
		
		ArrayList<Boolean[]> data = new ArrayList<Boolean[]>();
		
		File file = new File(filename);
		LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(file));
		lineNumberReader.skip(Long.MAX_VALUE);
		int lines = lineNumberReader.getLineNumber();
		Scanner input = new Scanner(new File(filename));
		
		int j=0;
		
		while (j<lines*percentage) {
			String line = input.nextLine();
			String[] tokens = line.split("[,]");
			
			Boolean[] record = new Boolean[tokens.length];
			for (int i=0; i<tokens.length; i++) {
				if (tokens[i].trim().equals("1")) {
					record[i] = true;
				} else {
					record[i] = false;
				}
			}
			data.add(record);
			j++;
		}
		input.close();
		
		return data;
	}
	
	/*
	 *	allOneClass() method is not being used by program. 
	 * 
	 */
	
	public static boolean allOneClass(ArrayList<Boolean[]> data) {
		
		boolean allOneClass = true;
		Boolean seenTrue = null;
		Boolean seenFalse = null;
		int i=0;
		for (Boolean[] row: data) {
			i++;
			boolean label = row[row.length-1];
			if (label) {
				if (seenTrue == null) {
					seenTrue = true;
					
					if (seenFalse != null && seenFalse) {
						allOneClass = false;
						
						break;
					}
				}
			} else {
				if (seenFalse == null) {
					seenFalse = true;
					
					if (seenTrue != null && seenTrue) {
						allOneClass = false;
						
						break;
					}
				}
			}
		}
		
		return allOneClass;
	}
	
	/**
	 * 
	 * @param path ArrayList<Split> represent current path from root. 
	 * @return boolean it returns true or false based on true number of rows> false number of rows
	 * 
	 */
	
	public static boolean majorityClass(ArrayList<Split> path) {
		double trueCount = 0;
		double falseCount = 0;
		
		double S=PEWithoutClass(path)*data.size();	// finds data set S
		double PStarEClass=PEWithClass(path,true);	
		
		double trueRowSize=(PStarEClass*data.size())/S; //find true rows
		
		double falseRowSize=1-trueRowSize;	//find false rows
		
		return trueRowSize > falseRowSize;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////
	//	/*
	// 	*  Below four methods are not used by program.
	// 	*/
	//public static ArrayList<Boolean[]> getTrueRows(ArrayList<Boolean[]> data, int attribute) {
	//	ArrayList<Boolean[]> trueRows = new ArrayList<Boolean[]>();
	//	
	//	
	//	for (Boolean[] row: data) {
	//		if (row[attribute]) {
	//			trueRows.add(row);
	//		}
	//	}
	//	
	//	return trueRows;
	//}
	//
	//public static ArrayList<Boolean[]> getFalseRows(ArrayList<Boolean[]> data, int attribute) {
	//	ArrayList<Boolean[]> falseRows = new ArrayList<Boolean[]>();
	//	
	//	for (Boolean[] row: data) {
	//		if (!row[attribute]) {
	//			falseRows.add(row);
	//		}
	//	}
	//	
	//	return falseRows;
	//}
	//
	//public static int numTrue(ArrayList<Boolean[]> data) {
	//	int count = 0;
	//	
	//	for (Boolean[] row: data) {
	//		if (row[row.length-1]) {
	//			count++;
	//		}
	//	}
	//	
	//	return count;
	//}
	//
	//public static int numFalse(ArrayList<Boolean[]> data) {
	//	int count = 0;
	//	
	//	for (Boolean[] row: data) {
	//		if (!row[row.length-1]) {
	//			count++;
	//		}
	//	}
	//	
	//	return count;
	//}
	//////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	/*
	 * Below method finds P*(E) without class
	 */
	
	public static double getPStarEWithoutClass(ArrayList<Split> path)
	{
		ArrayList<Boolean[]> trueData =new ArrayList<Boolean[]>();
		
		for(int i=0;i<data.size();i++)
		{
			Boolean checkTrueCondition=true;
			
			int j=0;
			
			/*
			 * While loop iterates through all the path and its direction and && it with boolean
			 * values to check all the conditions are satisfied or not
			 */
			
			while(j<path.size())
			{
				checkTrueCondition=checkTrueCondition && (data.get(i)[(Integer) path.get(j).attribute]==path.get(j).direction);
				j++;
			}
					
			if(checkTrueCondition)
			{
				trueData.add(data.get(i));
			}
			
		}
	
		double PE=((double)trueData.size()/(double)data.size()) ;
		
		
		return PE;
	}

	/*
	 * Below method finds p*(EPrime) without class
	 */
	
	public static double getPStarEPrimeWithoutClass(ArrayList<Split> path)
	{
		ArrayList<Boolean[]> falseData=new ArrayList<Boolean[]>();
		
		for(int i=0;i<data.size();i++)
		{
			Boolean checkFalseCondition=true;
			
			int j=0;
			
			/*
			 * While loop iterates through all the path and its direction and && it with boolean
			 * values to check all the conditions are satisfied or not
			 */
			
			while(j<path.size())
			{		
				checkFalseCondition=checkFalseCondition && (data.get(i)[(Integer) path.get(j).attribute]==!(path.get(j).direction));
				j++;
			}
		
			if(checkFalseCondition)
			{
				falseData.add(data.get(i));
			}
			
		}
		
		double PEPrime=((double)falseData.size()/(double)data.size());
		
		return PEPrime;
	}
	
	/*
	 * Below method finds p*(EPrime) with class and it also takes classType = true or false as parameter
	 */
	
	public static double getPStarEPrimeWithClass(ArrayList<Split> path,boolean classType)
	{
		ArrayList<Boolean[]> falseData=new ArrayList<Boolean[]>();
		
		for(int i=0;i<data.size();i++)
		{
			Boolean checkFalseCondition=data.get(i)[3]==!classType;
		
			int j=0;
			
			/*
			 * While loop iterates through all the path and its direction and && it with boolean
			 * values to check all the conditions are satisfied or not
			 */
			
			while(j<path.size())
			{
				checkFalseCondition=checkFalseCondition && (data.get(i)[(Integer) path.get(j).attribute]==!(path.get(j).direction));
				j++;
			}
			
			if(checkFalseCondition)
			{
				falseData.add(data.get(i));
			}
		}
	
		return ((double)falseData.size()/(double)data.size()) ;
	
	}
	
	/*
	 * Below method finds p*(EP) with class and it also takes classType = true or false as parameter
	 */
	
	public static double getPStarEWithClass(ArrayList<Split> path,boolean classType)
	{
		ArrayList<Boolean[]> trueData =new ArrayList<Boolean[]>();
		
		for(int i=0;i<data.size();i++)
		{
			Boolean checkTrueCondition=data.get(i)[3]==classType;
			
			int j=0;
			
			/*
			 * While loop iterates through all the path and its direction and && it with boolean
			 * values to check all the conditions are satisfied or not
			 */
			while(j<path.size())
			{
				checkTrueCondition=checkTrueCondition && (data.get(i)[(Integer) path.get(j).attribute]==path.get(j).direction);
				j++;
			}
			
			if(checkTrueCondition)
			{
				trueData.add(data.get(i));
			}
			
		}
		
		return ((double)trueData.size()/(double)data.size());
	
	}
	
	/*
	 * Below method finds P(E) without class
	 */
	 
	public static double PEWithoutClass(ArrayList<Split> path)
	{
		
		double pStarE=getPStarEWithoutClass(path); //finds P*(E)
		double pStarEPrime=getPStarEPrimeWithoutClass(path);// finds P*(EPrime)
		double pE=(pStarE*theta+pStarEPrime*(1-theta))/(2*theta-1); // finds P(E)
		return pE;
		
	}
	
	/*
	 * Below method finds P(E) with class and it also takes classType = true or false as parameter
	 */
	
	public static double PEWithClass(ArrayList<Split> path,boolean classType)
	{

		double pStarEClass=getPStarEPrimeWithClass(path,classType); //P*(E) with class
		double pStarEPrimeClass=getPStarEPrimeWithClass(path,classType); // P*(EPrime) with class
		
		double PE=(pStarEClass*theta+pStarEPrimeClass*(1-theta))/(2*theta-1);  //computes P(E) with class
		return PE;
		
	}
	
	/**
	 * @param Qtrue,Qfalse Here Qtrue and Qfalse represent Sv in gain function.
	 * @return double Returns double value of entropy.
	 * The below function returns negative values of the entropy.
	 */
	
	public static double entropy(double Qtrue,double Qfalse) 
	{
			
			double entropy = 0;
			
			if(data.size()==0) return 0;	// returns zero if data.size is zero
			
			if (Qtrue != 0) {
				entropy += Qtrue * (Math.log10(Qtrue)/Math.log10(2));
			}
			if (Qfalse != 0) {
				entropy += Qfalse * (Math.log10(Qfalse)/Math.log10(2));
			}
			
			return -entropy;
		
	}
	
	/**
	 * 
	 * @param data	Takes original data as input
	 * @param attribute	Takes one attribute as per passed from selectAttribute()
	 * @param path	List of node and path values from root to current node
	 * @return Returns gain in double value
	 */
	
	/*
	 * The below function returns negative values of the entropy.
	 */
	 
	public static double findGain(ArrayList<Boolean[]> data, int attribute,ArrayList<Split> path) {
		
	double falseRowSize=0.0;
	double trueRowSize=0.0,PEPrime=0.0,PStarE=0.0;
	double S=0.0,PStarEClass=0.0,PEtruePrime=0.0,PEfalse=0.0,PEfalseprime=0.0,PEtrue;
	
	
		if(path.size()==0)
		{
			S=PEWithoutClass(path)*data.size();
		
			path.add(new Split(attribute,true));
			
			PEtrue=getPStarEWithoutClass(path);
			PEfalse=getPStarEPrimeWithoutClass(path);
			
			path.remove(path.size()-1);
			
			trueRowSize=(PEtrue)*data.size()/S;
			falseRowSize=0;		
		}
		else
		{
			PEtrue=getPStarEWithoutClass(path);
			PEfalse=getPStarEPrimeWithoutClass(path);
			S=PEWithoutClass(path)*data.size();
			
			
			
			path.add(new Split(attribute,true));
			PStarEClass=PEWithClass(path,true);
			path.remove(path.size()-1);
			
			trueRowSize=(PStarEClass*data.size())/S;
			falseRowSize=1-trueRowSize;
		}	
		
		
		if(data.size()==0) return 0;
		
		return entropy(PEtrue,PEfalse) - 
				((trueRowSize) * entropy(trueRowSize,falseRowSize) + 
				 (falseRowSize) * entropy(falseRowSize,trueRowSize));
	}
	 
	/**
	 * 
	 * @param attributes Takes list of attributes as per passed from buildTree()
	 * @param path List of node and path values from root to current node
	 * @return returns maximum gain from remaining attributes
	 *  
	 */
	
	public static int selectAttribute(HashSet<Integer> attributes,ArrayList<Split> path) 
	{
		double maxGain = -Double.MIN_VALUE;
		int attribute = 0;
		
		for (Integer i: attributes) 
		{
			if(attributes.size()==1) attribute=i;

			double gain = findGain(data, i,path);
			if (gain > maxGain) 
			{
				
				maxGain = gain;
				attribute = i;
			}
			
		}
		
		return attribute;
	}
	
	/**
	 * 
	 * @param path List of node and path values from root to current node
	 * @param attributes Takes list of attributes
	 * @return Node with Label or node from splitting of attribute
	 * 
	 */
	
	public static Node buildTree(ArrayList<Split> path, HashSet<Integer> attributes) 
	{
		Node node = new Node();
		
		if (attributes.size() == 0) { // no more attributes to split on
			node.label = majorityClass(path);
			return node;
		}
		
		ArrayList<Split> findParent=path;
		
		node.attribute = selectAttribute( attributes,findParent);		
		attributes.remove(node.attribute);
		
		ArrayList<Split> truePath = (ArrayList<Split>) findParent.clone(); // Splits on true edge
		truePath.add(new Split(node.attribute, true)); 
		node.trueChild = buildTree(truePath,attributes);
		
		ArrayList<Split> falsePath = (ArrayList<Split>) findParent.clone(); // Splits on false edge
		falsePath.add(new Split(node.attribute, false)); 
		node.falseChild = buildTree(falsePath,attributes);
			
		return node;
	}

	/**
	 * 
	 * @param node takes root node as input
	 * @param row	Takes a single data row as input
	 * @return return true if class is 1 and o otherwise
	 */
	
	public static boolean classify(Node node, Boolean[] row) {
		if (node.label != null) {
			return node.label;
		} else {
			boolean direction = row[node.attribute];
			if (direction) {
				return classify(node.trueChild, row);
			} else {
				return classify(node.falseChild, row);
			}
		}
	}
	
	/**
	 * 
	 * @param node	takes root node as input
	 * @param tab	Tabular count
	 * 
	 */
	
	/*
	 *  This method prints tree on the command prompt
	 */
	
	public static void traverseTree(Node node, int tab) {
		if (node.label != null) {
			for (int i=0; i<tab; i++) {
				System.out.print("\t");
			}
			System.out.println(node.label);
		} else {
			for (int i=0; i<tab; i++) {
				System.out.print("\t");
			}
			System.out.println("split on " + node.attribute);
			traverseTree(node.trueChild, tab+1);
			traverseTree(node.falseChild, tab+1);
		}
	}

	/**
	 * 
	 * @param inputFilename Takes input file name 
	 * @param outputFileName	takes output file name
	 * @param theta also take theta as input
	 *
	 */
		
	/*
	 * 	Below method randomized data base on the theta value and random number generated
	 * 	between 0.0 to 1.0
	 */
	
	public static void randomizeData(String inputFilename,String outputFileName,double theta)
	{
		String line;
		PrintWriter pw = null;
		double randomNumber;
		try {
			BufferedReader br=new BufferedReader(new FileReader(new File(inputFilename))); // read file

			pw=new PrintWriter(new File(outputFileName)); 	// Create buffere to write into output file
		
			while((line=br.readLine())!=null)
			{
				randomNumber=Math.random();	// create random number
				String token[]=line.split(",");
			
				if(randomNumber<theta)	//Checks condition to flip value of given row
				{
					String str="";
					for(int i=0;i<token.length;i++)
					{
						if(i==3 && token[i].equals("0"))
						{
							str=str+1+"";
						}
						else if(i==3 && token[i].equals("1"))
						{
							str=str+0+"";
						}
						else if(token[i].equals("0"))
						{
							str=str+1+",";
						}
						else
						{
							str=str+0+",";
						}
					}
					
					pw.write(str+"\n");
				}
				else
				{
						pw.write(line+"\n");
					
				}
			
			}
			pw.close();
		
		} 
		catch (Exception e) 
		{
			pw.close();
			e.printStackTrace();
		}	
		
	}

	/*
	 *  main() method interact with user to get value of theta and percentage of file wants to read
	 *  it also calls buildtree method and than runs test data on build tree and outputs decision tree
	 *  and efficiency of decision tree.
	 */
	
	public static void main(String[] args) throws Exception {
		
		Scanner keyboard=new Scanner(System.in);
		
		System.out.print("Enter value of theta: ");
		theta=keyboard.nextDouble();
		
		randomizeData("./training_large.txt","training_rand.txt",theta);
		
		System.out.print("Enter what percentage of file you want to work on: ");
		double percentage=keyboard.nextDouble();
		
		data = readFile("./training_rand.txt",percentage);
		 
		HashSet<Integer> attributes = new HashSet<Integer>();
		
		for (int i=0; i<data.get(0).length-1; i++) {
			attributes.add(i);
		}
		
		ArrayList<Split> path=new ArrayList<Split>();
		
		Node root = buildTree(path, attributes);
		
		traverseTree(root, 0);
		
		randomizeData("./test.txt","test_rand.txt",theta);
		ArrayList<Boolean[]> testData=readFile("test_rand.txt",theta);
		
		ArrayList testTrueClass=new ArrayList();
	
		double counter=0;
		
		for(int i=0;i<testData.size();i++)
		{
			if(testData.get(i)[3]==classify(root,testData.get(i))) counter++;	
		}
		
		System.out.println("Efficiency is: "+(counter/testData.size())*100);	
		
	}
	
}
