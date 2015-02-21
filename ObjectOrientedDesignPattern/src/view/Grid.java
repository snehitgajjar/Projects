package view;

import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;


public class Grid {
	
	static Grid grid;

	/*
	 * Instance variables.
	 */
	private static int gridSizeX,gridSizeY; //sets the grid size.
	private static Hashtable<Integer,Boolean> gridScentX=new Hashtable<Integer,Boolean>(); //  x coordinate of scent point
	private static Hashtable<Integer,Boolean> gridScentY=new Hashtable<Integer,Boolean>(); //  y coordinate of scent point

	
	/*
	 * Singleton Pattern.
	 * 
	 * getInstance() method implements getInstance() method.  
	 * This method returns saved object of this class itself when instance is not null and if it is null
	 * it create a new object and return that object and stores that object.
	 * 
	 * So during execution only one object of Grid is created.
	 */
	
	public static Grid getInstance()
	{
		if(grid!=null)
		{
			return grid;
		}
		else
		{
			grid=new Grid();
			return grid;
		}
		
	}
	
	
	// getter and setter method for instace variables.
	
	public static boolean getGridScentX(int key) {
		
		/*
		 * returns true if that x coordinate has scent set.
		 */
		if(gridScentX.get(key)==null)
		{
			return false;
		}
		else
		{
			return gridScentX.get(key);
		}
		
	
	}

	public static void setGridScentX(int key)
	{
		//set scent true if mutant is out of the grid in x-direction.
		gridScentX.put(key,true);
		
	}
	
	
	public static boolean getGridScentY(int key) {
		
		/*
		 * returns true if that x coordinate has scent set.
		 */
		if(gridScentY.get(key)==null)
		{
			return false;
		}
		else
		{
			return gridScentY.get(key);
		}
	
	}

	public static void setGridScentY(int key)
	{
		//set scent true if mutant is out of the grid in x-direction.
		gridScentY.put(key,true);
		
	}
	
	
	// below methods sets and returns the value of gridSizeX and gridSizeY.
	
	public int getGridSizeX() {
		
		return gridSizeX;
	}

	
	public void setGridSizeX(int x) {
		gridSizeX=x;
	}
	
	public int getGridSizeY() {
		
		return gridSizeY;
	}

	
	public void setGridSizeY(int y) {
		gridSizeY=y;
	}
	
	
	
	
	
	
	
}
