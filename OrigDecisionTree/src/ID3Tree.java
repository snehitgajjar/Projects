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
	
	public static boolean majorityClass(ArrayList<Boolean[]> data) {
		int trueCount = 0;
		int falseCount = 0;
		
		for (Boolean[] row: data) {
			boolean label = row[row.length-1];

			if (label)
				trueCount++;
			else
				falseCount++;
		}

		return trueCount > falseCount;
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
	
	
	public static double entropy(ArrayList<Boolean[]> data) {
		
	
		
		double Qtrue = numTrue(data) / (double) data.size();
		double Qfalse = numFalse(data) / (double) data.size();
		
		double entropy = 0;
		
		if(data.size()==0) return 0;
		if (Qtrue != 0) {
			entropy += Qtrue * (Math.log10(Qtrue)/Math.log10(2));
		}
		
		if (Qfalse != 0) {
			entropy += Qfalse * (Math.log10(Qfalse)/Math.log10(2));
		}
		
		return -entropy;
	
	}
	
	public static double getEWithoutClass(ArrayList<Boolean[]> data,Map attributeMap,Double theta)
	{
		ArrayList<Boolean[]> trueData =new ArrayList<Boolean[]>();
		ArrayList<Boolean[]> falseData=new ArrayList<Boolean[]>();
		
		for(int i=0;i<data.size();i++)
		{
			Boolean checkTrueCondition=true;
			Boolean checkFalseCondition=true;
			
			Iterator it=attributeMap.entrySet().iterator();
			
			while(it.hasNext())
			{
				Map.Entry pair = (Map.Entry)it.next();
			//	System.out.println("Condition: "+(Integer) pair.getKey() );
				checkTrueCondition=checkTrueCondition && (data.get(i)[(Integer) pair.getKey()]==pair.getValue());
				checkFalseCondition=checkFalseCondition && (data.get(i)[(Integer) pair.getKey()]==pair.getValue());
			
			}
			
			
			
			if(checkTrueCondition)
			{
				trueData.add(data.get(i));
			}
			else if(checkFalseCondition)
			{
				falseData.add(data.get(i));
			}
		}
		
		
		double PE=(trueData.size()/data.size()) * theta+(falseData.size()/data.size())*(1.0-theta);
		
		
		return PE;
	}

	
	
	
	public static double getEPrimeWithoutClass(ArrayList<Boolean[]> data,Map<Integer,Boolean> attributeMap,double theta)
	{
		ArrayList<Boolean[]> trueData =new ArrayList<Boolean[]>();
		ArrayList<Boolean[]> falseData=new ArrayList<Boolean[]>();
		
		for(int i=0;i<data.size();i++)
		{
			Boolean checkTrueCondition=true;
			Boolean checkFalseCondition=true;
			
			Iterator it=attributeMap.entrySet().iterator();
			
			while(it.hasNext())
			{
				Map.Entry pair = (Map.Entry)it.next();
			//	System.out.println("Condition: "+(Integer) pair.getKey() );
				checkTrueCondition=checkTrueCondition && (data.get(i)[(Integer) pair.getKey()]==pair.getValue());
				checkFalseCondition=checkFalseCondition && (data.get(i)[(Integer) pair.getKey()]==pair.getValue());
			
			}
			
			
			
			if(checkTrueCondition)
			{
				trueData.add(data.get(i));
			}
			else if(checkFalseCondition)
			{
				falseData.add(data.get(i));
			}
		}
		
		
		double PEPrime=(falseData.size()/data.size()) * theta+(trueData.size()/data.size())*(1.0-theta);
		
		
		return PEPrime;
	}
	
	
	
	
	
	
	
	public static double getEPrimeWithClass(ArrayList<Boolean[]> data,Map<Integer,Boolean> attributeMap,double theta,boolean classType)
	{
		ArrayList<Boolean[]> trueData =new ArrayList<Boolean[]>();
		ArrayList<Boolean[]> falseData=new ArrayList<Boolean[]>();
		
		for(int i=0;i<data.size();i++)
		{
			Boolean checkTrueCondition=data.get(i)[3]==classType;
			Boolean checkFalseCondition=data.get(i)[3]=!classType;
			
			Iterator it=attributeMap.entrySet().iterator();
			
			while(it.hasNext())
			{
				Map.Entry pair = (Map.Entry)it.next();
			//	System.out.println("Condition: "+(Integer) pair.getKey() );
				checkTrueCondition=checkTrueCondition && (data.get(i)[(Integer) pair.getKey()]==pair.getValue());
				checkFalseCondition=checkFalseCondition && (data.get(i)[(Integer) pair.getKey()]==pair.getValue());
			
			}
			
			
			
			if(checkTrueCondition)
			{
				trueData.add(data.get(i));
			}
			else if(checkFalseCondition)
			{
				falseData.add(data.get(i));
			}
		}
		
		
		double PEPrime=(falseData.size()/data.size()) * theta+(trueData.size()/data.size())*(1.0-theta);
		
		
		return PEPrime;
	}
	
	
	
	

	public static double getEWithClass(ArrayList<Boolean[]> data,Map<Integer,Boolean> attributeMap,double theta,boolean classType)
	{
		ArrayList<Boolean[]> trueData =new ArrayList<Boolean[]>();
		ArrayList<Boolean[]> falseData=new ArrayList<Boolean[]>();
		
		for(int i=0;i<data.size();i++)
		{
			Boolean checkTrueCondition=data.get(i)[3]==classType;
			Boolean checkFalseCondition=data.get(i)[3]=!classType;
			
			Iterator it=attributeMap.entrySet().iterator();
			
			while(it.hasNext())
			{
				Map.Entry pair = (Map.Entry)it.next();
			//	System.out.println("Condition: "+(Integer) pair.getKey() );
				checkTrueCondition=checkTrueCondition && (data.get(i)[(Integer) pair.getKey()]==pair.getValue());
				checkFalseCondition=checkFalseCondition && (data.get(i)[(Integer) pair.getKey()]==pair.getValue());
			
			}
			
			
			
			if(checkTrueCondition)
			{
				trueData.add(data.get(i));
			}
			else if(checkFalseCondition)
			{
				falseData.add(data.get(i));
			}
		}
		
		
		double PEPrime=(trueData.size()/data.size()) * theta+(falseData.size()/data.size())*(1.0-theta);
		System.out.println("PEPrime: "+PEPrime);
		
		return PEPrime;
	}
	
	
	
	
	public static double findGain1(ArrayList<Boolean[]> data, int attribute,Map attributeMap,double theta) {
		
		double PE=getEWithoutClass(data, attributeMap,theta);
		double PEPrime=getEPrimeWithoutClass(data,attributeMap,theta);
		
		double S=(PE+PEPrime)*data.size();
		
		
		
		
		double PEtrue=getEWithClass(data, attributeMap, theta, true);
		double PEtrueprime=getEWithClass(data, attributeMap, theta, false);
		
		double trueRowSize=(PEtrue+PEtrueprime)*data.size();
		
		double PEfalse=getEWithClass(data, attributeMap, theta, true);
		double PEfalseprime=getEWithClass(data, attributeMap, theta, false);
		
		double falseRowSize=(PEfalse+PEfalseprime)*data.size();
		System.out.println("New true rows: "+trueRowSize+" New false rows: "+falseRowSize+" Total data: "+S);
		
		
		/*ArrayList<Boolean[]> trueRows = getTrueRows(data, attribute);
		ArrayList<Boolean[]> falseRows = getFalseRows(data, attribute);
		*/
		
		if(data.size()==0) return 0;
		
		
		return entropy(data) - 
				(trueRowSize / (int)S * entropy(data)) + 
				 (falseRowSize / (int)S * entropy(data));
		
	/*	return entropy(data) - 
				((trueRows.size() / (int)S * entropy(trueRows) + 
				 (falseRows.size() / (int)S * entropy(falseRows)); */
		
		
		
	}
	
public static double findGain(ArrayList<Boolean[]> data, int attribute,Map attributeMap) {
		
		/*ArrayList<Boolean[]> trueRows=getE(data, attributeMap);
		ArrayList<Boolean[]> falseRows=getEPrime(data,attributeMap);
		*/
	//	System.out.println("New true rows: "+trueRows.size()+" New false rows: "+falseRows.size()+" Total data: "+data.size());
		
		
		ArrayList<Boolean[]> trueRows = getTrueRows(data, attribute);
		ArrayList<Boolean[]> falseRows = getFalseRows(data, attribute);
		
		
		if(data.size()==0) return 0;
		
		return entropy(data) - 
				((trueRows.size() / (double) data.size()) * entropy(trueRows) + 
				 (falseRows.size() / (double) data.size()) * entropy(falseRows));
	}
	 
	public static int selectAttribute(ArrayList<Boolean[]> data, HashSet<Integer> attributes,Map attributeMap) {
		double maxGain = -Double.MIN_VALUE;
		int attribute = 0;
		
		
		
		for (Integer i: attributes) {
			double gain = findGain(data, i,attributeMap);
			if (gain > maxGain) {
				maxGain = gain;
				attribute = i;
			}
		}
		
		return attribute;
	}
	
	public static int selectAttribute1(ArrayList<Boolean[]> data, HashSet<Integer> attributes,Map attributeMap,double theta) {
		double maxGain = -Double.MIN_VALUE;
		int attribute = 0;
		
		
		
		for (Integer i: attributes) {
			double gain = findGain1(data, i,attributeMap,theta);
			if (gain > maxGain) {
				maxGain = gain;
				attribute = i;
			}
		}
		
		return attribute;
	}
	
	static Map<Integer,Boolean> attributeEdge=new HashMap<Integer,Boolean>();
	
	public static Node buildTree(ArrayList<Boolean[]> data, HashSet<Integer> attributes,Node parent,Boolean value) {
		Node node = new Node();
	
		/*if (allOneClass(data)) {
			node.label = majorityClass(data);
			
			return node;
		} */
		
		if (attributes.size() == 0) { // no more attributes to split on
			node.label = majorityClass(data);
			return node;
		}
		
		if(value!=null) 
		{
			System.out.println("Select Attribute For leaf nodes: ");
			node.parentEdgeType=value;// 1
			node.parentNode=parent;
			attributeEdge=getCondition(node);
			node.attribute = selectAttribute1(data, attributes,attributeEdge,theta);
		}
		else
		{
			System.out.println("Select Attribute For root node: ");
			node.attribute = selectAttribute(data, attributes,attributeEdge);
		}
		 //1
		
		attributes.remove(node.attribute);

	/*	ArrayList<Boolean[]> trueRows = getTrueRows(data, node.attribute);
		if (trueRows.size() == 0) { // remove this if
			node.trueChild = new Node();
			
		//	attributeEdge.put(node.attribute,true);
			
			node.trueChild.label = majorityClass(data);
		} else {*/
		//	attributeEdge.put(node.attribute,true);
			node.trueChild = buildTree(data, attributes,node,true);
			
			
		
		
	/*	ArrayList<Boolean[]> falseRows = getFalseRows(data, node.attribute); //remove
		if (falseRows.size() == 0) {  		 remove this if not else
			node.falseChild = new Node();
			
			node.falseChild.label = majorityClass(data);
		} else { */
		//	attributeEdge.put(node.attribute,false);
			node.falseChild = buildTree(data, attributes,node,false);
			
		
		
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
		
		ArrayList<Boolean[]> data = readFile("./training.txt",percentage);
		
		HashSet<Integer> attributes = new HashSet<Integer>();
		for (int i=0; i<data.get(0).length-1; i++) {
			attributes.add(i);
		}
		
		Node root = buildTree(data, attributes,null,null);
		
		traverseTree(root, 0);
		
		//Boolean[] record = {true, false, false};
		ArrayList<Boolean[]> testData=readFile("./test.txt",1);
		ArrayList testTrueClass=new ArrayList();
		double counter=0;
		for(int i=0;i<testData.size();i++)
		{
			System.out.println(Arrays.toString(testData.get(i)));
			System.out.println(classify(root,testData.get(i)));
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
