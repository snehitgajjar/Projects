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
	
	
	static double theta=0.70;
	static ArrayList<Boolean[]> data=new ArrayList<Boolean[]>();
	
	public static ArrayList<Boolean[]> readFile(String filename,double percentage) throws Exception {
		
		ArrayList<Boolean[]> data = new ArrayList<Boolean[]>();
		
		File file = new File(filename);
		  LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(file));
		  lineNumberReader.skip(Long.MAX_VALUE);
		  int lines = lineNumberReader.getLineNumber();
		System.out.println("Line numbers: "+lines);
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
		System.out.println("Actual Line numbers: "+j);
		input.close();
		
		return data;
	}
	
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
	
	public static boolean majorityClass(ArrayList<Split> findParent) {
		double trueCount = 0;
		double falseCount = 0;
		for(int i=0;i<findParent.size();i++)
		{
			System.out.print("PATH: "+findParent.get(i).attribute+" & VALUE: "+findParent.get(i).direction);
		}
/*		for(int i=0;i<path.size();i++)
		{
			System.out.print("PATH: "+path.get(i).attribute+" & VALUE: "+path.get(i).direction);
		}*/
		
	/*	double PEtrue=PStarEWithClass(path,true);
		double PEtruePrime=PStarEPrimeWithClass(path,false);
		
		 trueCount=(PEtrue+PEtruePrime)*data.size();
		
		double PEfalse=PStarEWithClass(path,false);
		double PEfalseprime=PStarEPrimeWithClass(path,true);
		
		 falseCount=(PEfalse+PEfalseprime)*data.size(); */
		
		double PEtrue=getPStarEWithoutClass(findParent);
		double PEfalse=getPStarEPrimeWithoutClass(findParent);
		double S=PEWithoutClass(findParent)*data.size();
		
		
		
		
		double PStarEClass=PEWithClass(findParent,true);
	//	PEtruePrime=PStarEPrimeWithClass(findParent,false);
		
		double trueRowSize=(PStarEClass*data.size())/S;
		
		double falseRowSize=1-trueRowSize;
		
		 System.out.println("TRUE COUNT: "+trueRowSize+" FALSE COUNT: "+falseRowSize);
		return trueRowSize > falseRowSize;
	}
	
	public static ArrayList<Boolean[]> getTrueRows(ArrayList<Boolean[]> data, int attribute) {
		ArrayList<Boolean[]> trueRows = new ArrayList<Boolean[]>();
		
		
		for (Boolean[] row: data) {
			if (row[attribute]) {
				trueRows.add(row);
			}
		}
		
		return trueRows;
	}
	
	public static ArrayList<Boolean[]> getFalseRows(ArrayList<Boolean[]> data, int attribute) {
		ArrayList<Boolean[]> falseRows = new ArrayList<Boolean[]>();
		
		for (Boolean[] row: data) {
			if (!row[attribute]) {
				falseRows.add(row);
			}
		}
		
		return falseRows;
	}
	
	public static int numTrue(ArrayList<Boolean[]> data) {
		int count = 0;
		
		for (Boolean[] row: data) {
			if (row[row.length-1]) {
				count++;
			}
		}
		
		return count;
	}
	
	public static int numFalse(ArrayList<Boolean[]> data) {
		int count = 0;
		
		for (Boolean[] row: data) {
			if (!row[row.length-1]) {
				count++;
			}
		}
		
		return count;
	}
	
	/*
	 * The below function returns negative values of the entropy.
	 */
	
	
	public static double entropy(double Q) {
		
	
		
	//	double Qtrue = numTrue(data) / (double) data.size();
	//	double Qfalse = numFalse(data) / (double) data.size();
		
		double entropy = 0;
		
		if(data.size()==0) return 0;
		if (Q != 0) {
			entropy += Q * (Math.log10(Q)/Math.log10(2));
		}
		
		/*if (Qfalse != 0) {
			entropy += Qfalse * (Math.log10(Qfalse)/Math.log10(2));
		}*/
		
		return -entropy;
	
	}
	
	public static double getPStarEWithoutClass(ArrayList<Split> attributeMap)
	{
		ArrayList<Boolean[]> trueData =new ArrayList<Boolean[]>();
		//ArrayList<Boolean[]> falseData=new ArrayList<Boolean[]>();
		
		for(int i=0;i<data.size();i++)
		{
			Boolean checkTrueCondition=true;
		//	Boolean checkFalseCondition=true;
			
			int j=0;
			
			while(j<attributeMap.size())
			{
				
			//	System.out.println("Condition: "+(Integer) pair.getKey() );
				checkTrueCondition=checkTrueCondition && (data.get(i)[(Integer) attributeMap.get(j).attribute]==attributeMap.get(j).direction);
			//	checkFalseCondition=checkFalseCondition && (data.get(i)[(Integer) attributeMap.get(j).attribute]==!attributeMap.get(j).direction);
				j++;
			}
			
			
			
			if(checkTrueCondition)
			{
				trueData.add(data.get(i));
			}
			
			/*if(checkFalseCondition)
			{
				falseData.add(data.get(i));
			}*/
		}
		
		
		double PE=((double)trueData.size()/(double)data.size()) ;
		
		
		return PE;
	}

	
	
	
	public static double getPStarEPrimeWithoutClass(ArrayList<Split> attributeMap)
	{
	//	ArrayList<Boolean[]> trueData =new ArrayList<Boolean[]>();
		ArrayList<Boolean[]> falseData=new ArrayList<Boolean[]>();
		
		for(int i=0;i<data.size();i++)
		{
		//	Boolean checkTrueCondition=true;
			Boolean checkFalseCondition=true;
			
			int j=0;
			
			while(j<attributeMap.size())
			{
			
			//	System.out.println("Condition: "+(Integer) pair.getKey() );
			//	checkTrueCondition=checkTrueCondition && (data.get(i)[(Integer) attributeMap.get(j).attribute]==attributeMap.get(j).direction);
				checkFalseCondition=checkFalseCondition && (data.get(i)[(Integer) attributeMap.get(j).attribute]==!(attributeMap.get(j).direction));
				j++;
			}
			
			
			
		/*	if(checkTrueCondition)
			{
				trueData.add(data.get(i));
			}*/
			
			if(checkFalseCondition)
			{
				falseData.add(data.get(i));
			}
		}
		
		
		double PEPrime=((double)falseData.size()/(double)data.size());
		
		
		return PEPrime;
	}
	
	
	
	
	
	
	
	public static double getPStarEPrimeWithClass(ArrayList<Split> attributeMap,boolean classType)
	{
		ArrayList<Boolean[]> trueData =new ArrayList<Boolean[]>();
		ArrayList<Boolean[]> falseData=new ArrayList<Boolean[]>();
		
		for(int i=0;i<data.size();i++)
		{
			//Boolean checkTrueCondition=data.get(i)[3]==classType;
			Boolean checkFalseCondition=data.get(i)[3]==!classType;
			
			
			int j=0;
			
			while(j<attributeMap.size())
			{
			
			//	System.out.println("Condition: "+(Integer) pair.getKey() );
			//	checkTrueCondition=checkTrueCondition && (data.get(i)[(Integer) attributeMap.get(j).attribute]==attributeMap.get(j).direction);
				checkFalseCondition=checkFalseCondition && (data.get(i)[(Integer) attributeMap.get(j).attribute]==!(attributeMap.get(j).direction));
				j++;
			}
			
			
			
			/*if(checkTrueCondition)
			{
				trueData.add(data.get(i));
			}*/
			
			if(checkFalseCondition)
			{
				falseData.add(data.get(i));
			}
		}
		
		
		return ((double)falseData.size()/(double)data.size()) ;
		
		
		
	}
	
	
	
	

	public static double getPStarEWithClass(ArrayList<Split> attributeMap,boolean classType)
	{
		ArrayList<Boolean[]> trueData =new ArrayList<Boolean[]>();
		ArrayList<Boolean[]> falseData=new ArrayList<Boolean[]>();
		
		for(int i=0;i<data.size();i++)
		{
			Boolean checkTrueCondition=data.get(i)[3]==classType;
			//Boolean checkFalseCondition=data.get(i)[3]=!classType;
			
			int j=0;
			
			while(j<attributeMap.size())
			{
			
			//	System.out.println("Condition: "+(Integer) pair.getKey() );
				checkTrueCondition=checkTrueCondition && (data.get(i)[(Integer) attributeMap.get(j).attribute]==attributeMap.get(j).direction);
			//	checkFalseCondition=checkFalseCondition && (data.get(i)[(Integer) attributeMap.get(j).attribute]==!attributeMap.get(j).direction);
				j++;
			}
			
			
			
			if(checkTrueCondition)
			{
				trueData.add(data.get(i));
			}
			
		/*	if(checkFalseCondition)
			{
				falseData.add(data.get(i));
			} */
		}
		
	//	System.out.println("getEWithClass111: "+ ((double)trueData.size()/(double)data.size()));
		return ((double)trueData.size()/(double)data.size());
		
		
	}
	
	public static double PEWithoutClass(ArrayList<Split> attributeMap)
	{
		
		double pStarE=getPStarEWithoutClass(attributeMap);
		double pStarEPrime=getPStarEPrimeWithoutClass(attributeMap);
		double pE=(pStarE*theta+pStarEPrime*(1-theta))/(2*theta-1);
		return pE;
		
	}
	
	/*public static double PEPrimeWithoutClass(ArrayList<Split> attributeMap)
	{
		double pE=getEWithoutClass (attributeMap);
		double pEPrime=getEPrimeWithoutClass(attributeMap);
		double pStarEPrime=pEPrime*theta+pE*(1-theta);
		return pStarEPrime;
		
	}*/
	
	public static double PEWithClass(ArrayList<Split> attributeMap,boolean classType)
	{

		double pE=getPStarEPrimeWithClass(attributeMap,classType);
		double pEPrime=getPStarEPrimeWithClass(attributeMap,classType);
		
		double pStarEClass=(pE*theta+pEPrime*(1-theta))/(2*theta-1);
		return pStarEClass;
		
	}
	
/*	public static double PEPrimeWithClass(ArrayList<Split> attributeMap,boolean classType)
	{
		double pStarEPrimeClass=1-PEWithClass (attributeMap,classType);
		
		return pStarEPrimeClass;
		
	}*/


	
public static double findGain(ArrayList<Boolean[]> data, int attribute,ArrayList<Split> findParent) {
		
		/*ArrayList<Boolean[]> trueRows=getE(data, attributeMap);
		ArrayList<Boolean[]> falseRows=getEPrime(data,attributeMap);
		*/
	//	System.out.println("New true rows: "+trueRows.size()+" New false rows: "+falseRows.size()+" Total data: "+data.size());
	//System.out.println("PATH SIZE!!!!!!!!!!! : "+findParent.size());
	double falseRowSize=0.0;
	double trueRowSize=0.0,PEPrime=0.0,PStarE=0.0;
	double S=0.0,PStarEClass=0.0,PEtruePrime=0.0,PEfalse=0.0,PEfalseprime=0.0,PEtrue;
		if(findParent.size()==0)
		{
		/*	ArrayList<Boolean[]> trueRows = getTrueRows(data, attribute);
			ArrayList<Boolean[]> falseRows = getFalseRows(data, attribute);
			trueRowSize=trueRows.size();
			falseRowSize=falseRows.size();*/
			
			PEtrue=getPStarEWithoutClass(findParent);
			
			trueRowSize=(PEtrue)*data.size();
			
			falseRowSize=0;
			S=PEWithoutClass(findParent)*data.size();
			
			
		}
		else
		{
			PEtrue=getPStarEWithoutClass(findParent);
			PEfalse=getPStarEPrimeWithoutClass(findParent);
			S=PEWithoutClass(findParent)*data.size();
			
			
			
			
			PStarEClass=PEWithClass(findParent,true);
		//	PEtruePrime=PStarEPrimeWithClass(findParent,false);
			
			trueRowSize=(PStarEClass*data.size())/S;
			
			falseRowSize=1-trueRowSize;
		}	
		
		
		if(data.size()==0) return 0;
		System.out.println("New true rows: "+(int)trueRowSize+" New false rows: "+(int)falseRowSize+" Total data: "+(int)S);
		return entropy(S/data.size()) - 
				((trueRowSize) * entropy(trueRowSize*data.size()) + 
				 (falseRowSize) * entropy(falseRowSize*data.size()));
	}
	 
	public static int selectAttribute(HashSet<Integer> attributes,ArrayList<Split> parentPath) {
		double maxGain = -Double.MIN_VALUE;
		int attribute = 0;
		
		
		
		for (Integer i: attributes) {
			if(attributes.size()==1) attribute=i;
			double gain = findGain(data, i,parentPath);
			System.out.println("\nGain for "+i+" :"+gain+" MaxGain: "+maxGain);
			if (gain > maxGain) {
				
				maxGain = gain;
				attribute = i;
			}
			
		}
		
		return attribute;
	}
	
	static Map<Integer,Boolean> attributeEdge=new HashMap<Integer,Boolean>();
	
	static int yo=0;
	public static Node buildTree(ArrayList<Split> path, HashSet<Integer> attributes,Node parent,boolean value) {
		System.out.println("Buildtree call......"+(++yo));
		Node node = new Node();
	
		/*if (allOneClass(data)) {
			node.label = majorityClass(data);
			
			return node;
		} */
		
		if (attributes.size() == 0) { // no more attributes to split on
			node.label = majorityClass(path);
	//		System.out.println("In mejority class.........! ");
			return node;
		}
		
		ArrayList<Split> findParent=path;
		
				
		
			
			node.attribute = selectAttribute( attributes,findParent);
		
			
			
			System.out.println("\nSelected Attribute: "+ node.attribute +" and size of attributes: "+attributes.size());
		//	node.attribute = selectAttribute(attributes,findParent);
		
		
	/*	for(int i=0;i<findParent.size();i++)
		{
			System.out.print("PATH: "+findParent.get(i).attribute+" & VALUE: "+findParent.get(i).direction);
		}*/

	/*	ArrayList<Boolean[]> trueRows = getTrueRows(data, node.attribute);
		if (trueRows.size() == 0) { // remove this if
			node.trueChild = new Node();
			
		//	attributeEdge.put(node.attribute,true);
			
			node.trueChild.label = majorityClass(data);
		} else {*/
		//	attributeEdge.put(node.attribute,true);
		
	 
		attributes.remove(node.attribute);
		ArrayList<Split> truePath = (ArrayList<Split>) findParent.clone(); 
		truePath.add(new Split(node.attribute, true)); 
		node.trueChild = buildTree(truePath,attributes,node,true);
		
		ArrayList<Split> falsePath = (ArrayList<Split>) findParent.clone(); 
		falsePath.add(new Split(node.attribute, false)); 
		node.falseChild = buildTree(falsePath,attributes,node,false);
			
			
		
		
		return node;
	}

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
	
	public static void main(String[] args) throws Exception {
		
		Scanner keyboard=new Scanner(System.in);
		
		System.out.print("Enter what percentage of file you want to work on: ");
		double percentage=keyboard.nextDouble();
		
		 data = readFile("./randomizeddata.txt",percentage);
		
		HashSet<Integer> attributes = new HashSet<Integer>();
		for (int i=0; i<data.get(0).length-1; i++) {
			attributes.add(i);
		}
		ArrayList<Split> path=new ArrayList<Split>();
		Node root = buildTree(path, attributes,null,true);
		
		traverseTree(root, 0);
		
		//Boolean[] record = {true, false, false};
		ArrayList<Boolean[]> testData=readFile("./test.txt",1);
		ArrayList testTrueClass=new ArrayList();
		double counter=0;
		for(int i=0;i<testData.size();i++)
		{
	//		System.out.println(Arrays.toString(testData.get(i)));
	//		System.out.println(classify(root,testData.get(i)));
			if(testData.get(i)[3]==classify(root,testData.get(i)))
				counter++;
		
		}
		
		System.out.println("Tested true size: "+counter);
		System.out.println("Efficiency is: "+(counter/testData.size())*100);
		
		
		/*
		randomizeData("./training.txt", 0.70);
		*/
	}
	
	
	public static void randomizeData(String filename,double theta)
	{
		String line;
		PrintWriter pw = null;
		double randomNumber;
		try {
			BufferedReader br=new BufferedReader(new FileReader(new File(filename)));
		
		
		
			pw=new PrintWriter(new File("./randomizeddata.txt"));
		
			while((line=br.readLine())!=null)
			{
				randomNumber=Math.random();
				System.out.println("Random Number: "+randomNumber);
				String token[]=line.split(",");
			
				if(randomNumber<theta)
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
	
	
	
	public static Map getCondition(Node node)
	{
		Node iterNode=node;
		Map<Integer,Boolean> map=new HashMap<Integer,Boolean>();
		while(iterNode.parentNode!=null)
		{
		//	System.out.print("Parent node: "+iterNode.parentNode.attribute+" Edge Type: "+iterNode.parentEdgeType+"\t");
			map.put(iterNode.parentNode.attribute,iterNode.parentEdgeType );
			iterNode=iterNode.parentNode;
		}
		System.out.println();
		return map;
	}
	

}
