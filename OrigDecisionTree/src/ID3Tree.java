import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;


public class ID3Tree {
	
	public static ArrayList<Boolean[]> readFile(String filename) throws Exception {
		
		ArrayList<Boolean[]> data = new ArrayList<Boolean[]>();
		
		Scanner input = new Scanner(new File(filename));
		
		while (input.hasNext()) {
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
		}
		
		input.close();
		
		return data;
	}
	
	public static boolean allOneClass(ArrayList<Boolean[]> data) {
		
		boolean allOneClass = true;
		Boolean seenTrue = null;
		Boolean seenFalse = null;
		int i=0;
		System.out.println("Main ");
		for (Boolean[] row: data) {
			i++;
			boolean label = row[row.length-1];
			System.out.println(label+" "+i);
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
		
		if (Qtrue != 0) {
			entropy += Qtrue * Math.log(Qtrue);
		}
		
		if (Qfalse != 0) {
			entropy += Qfalse * Math.log(Qfalse);
		}
		
		return -entropy;
	
	}
	
	public static double findGain(ArrayList<Boolean[]> data, int attribute) {
		
		ArrayList<Boolean[]> trueRows = getTrueRows(data, attribute);
		ArrayList<Boolean[]> falseRows = getFalseRows(data, attribute);
		
		return entropy(data) - 
				((trueRows.size() / (double) data.size()) * entropy(trueRows) + 
				 (falseRows.size() / (double) data.size()) * entropy(falseRows));
	}
	 
	public static int selectAttribute(ArrayList<Boolean[]> data, HashSet<Integer> attributes) {
		double maxGain = -Double.MIN_VALUE;
		int attribute = -1;
		
		for (Integer i: attributes) {
			double gain = findGain(data, i);
			if (gain > maxGain) {
				maxGain = gain;
				attribute = i;
			}
		}
		
		return attribute;
	}
	
	public static Node buildTree(ArrayList<Boolean[]> data, HashSet<Integer> attributes) {
		Node node = new Node();
		
		if (allOneClass(data)) {
			node.label = majorityClass(data);
			
			return node;
		}
		
		if (attributes.size() == 0) { // no more attributes to split on
			node.label = majorityClass(data);
			return node;
		}
		
		node.attribute = selectAttribute(data, attributes);
		attributes.remove(node.attribute);

		ArrayList<Boolean[]> trueRows = getTrueRows(data, node.attribute);
		if (trueRows.size() == 0) {
			node.trueChild = new Node();
			node.trueChild.label = majorityClass(data);
			System.out.println("In true child If");
		} else {
			System.out.println("In true child");
			node.trueChild = buildTree(trueRows, attributes);
			
		}
		
		ArrayList<Boolean[]> falseRows = getFalseRows(data, node.attribute);
		if (falseRows.size() == 0) {
			node.falseChild = new Node();
			node.falseChild.label = majorityClass(data);
			System.out.println("In true child If");
		} else {
			System.out.println("In false child");
			node.falseChild = buildTree(falseRows, attributes);
			
		}
		
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
		
		ArrayList<Boolean[]> data = readFile("./training.txt");
		
		HashSet<Integer> attributes = new HashSet<Integer>();
		for (int i=0; i<data.get(0).length-1; i++) {
			attributes.add(i);
		}
		
		Node root = buildTree(data, attributes);
		
		traverseTree(root, 0);
		
		Boolean[] record = {true, false, false};
		System.out.println(Arrays.toString(record));
		System.out.println(classify(root, record));
	}

}
