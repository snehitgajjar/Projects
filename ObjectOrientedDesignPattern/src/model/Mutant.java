package model;

import view.Grid;




/*
 * Mutant Class is binded in the view module which handles how program will
 * interact with the customer and process accordingly. 
 * abstract class RobotHandler which defines template design pattern. RobotHandler
 * is inherited by the Mutant class which defines the body of the three methods 
 * turnLeft(), turnRight(), goForward().
 */
abstract class RobotHandler
{
	
	// abstract methods which will be implemented by subclasses.
	public abstract void turnLeft();
	public abstract void turnRight();
	public abstract void goForward();
}

public class Mutant extends RobotHandler {

	//instance variables.
	private int currentLocationX=0,currentLocationY=0; // store current location's x and y coordinates.
	private char currentOrientation; //stores current orientation 
	private boolean isLost=false; // state of mutant which indicates it is out of grid or not.
	

	Grid grid=Grid.getInstance();
	
	
	
	public Mutant(){} 
	
	/*
	 * Mutant(int,int,char) constructor which accepts threee parameter starting position and starting orientation.
	 */
	public Mutant(int x,int y,char currentOrientation)
	{
		setCurrentLocationX(x);
		setCurrentLocationY(y);
		setCurrentOrientation(currentOrientation);
	}
	
	/*
	 * getter and setter methods for the given instance variables.
	 */
	
	public boolean isLost() {
		return isLost;
	}

	public void setLost(boolean isLost) {
		this.isLost = isLost;
	}



	public char getCurrentOrientation() {
		return currentOrientation;
	}

	public void setCurrentOrientation(char currentOrientation) {
		this.currentOrientation = currentOrientation;
	}

	
	
	
	
	public int getCurrentLocationX() {
		return currentLocationX;
	}

	public void setCurrentLocationX(int currentLocationX) {
		this.currentLocationX = currentLocationX;
		
		
		/*
		 * if the current location point is out of the grid size on the X-axes direction  than the 
		 * scent is placed on that point which save other robot falling from that same point
		 */
		if(this.currentLocationX>grid.getGridSizeX())
		{
			grid.setGridScentX(this.currentLocationX);
			grid.setGridScentY(this.currentLocationY);
			setLost(true);
		}
		else if(this.currentLocationX<0)
		{
			grid.setGridScentX(this.currentLocationX);
			grid.setGridScentY(this.currentLocationY);
			setLost(true);
		}
	}

	public int getCurrentLocationY() {
		return currentLocationY;
		
		
	}

	public void setCurrentLocationY(int currentLocationY) {
		this.currentLocationY = currentLocationY;
		
		
		
		/*
		 * if the current location point is out of the grid size on the Y-axes direction  than the 
		 * scent is placed on that point which save other robot falling from that same point
		 */
		if(this.currentLocationY>grid.getGridSizeY())
		{
			
			grid.setGridScentY(this.currentLocationY);
			grid.setGridScentX(this.currentLocationX);
			setLost(true);
		}
		else if(this.currentLocationY<0)
		{
			grid.setGridScentY(this.currentLocationY);
			grid.setGridScentX(this.currentLocationX);
			setLost(true);
		}
	}
	
	
	/* 
	 * Motion methods which handles the left,right and forward motions of the mutant.
	 */
	
	public void turnLeft()
	{
		
		/*
		 * It makes mutant to turn its orientation one step left accordingly its current orientation.
		 */
		if(getCurrentOrientation()=='E')
		{
			setCurrentOrientation('N');
		}
		else if(getCurrentOrientation()=='N')
		{
			setCurrentOrientation('W');
		}
		else if(getCurrentOrientation()=='W')
		{
			setCurrentOrientation('S');
		}
		else if(getCurrentOrientation()=='S')
		{
			setCurrentOrientation('E');
		}
		
	}
	
	public void turnRight()
	{
		if(getCurrentOrientation()=='E')
		{
			setCurrentOrientation('S');
		}
		else if(getCurrentOrientation()=='S')
		{
			setCurrentOrientation('W');
		}
		else if(getCurrentOrientation()=='W')
		{
			setCurrentOrientation('N');
		}
		else if(getCurrentOrientation()=='N')
		{
			setCurrentOrientation('E');
		}
		
	}
	
	
	public void goForward()
	{
		if(getCurrentOrientation()=='E' && (!Grid.getGridScentX(getCurrentLocationX()+1) || !Grid.getGridScentY(getCurrentLocationY())))
		{
			
			setCurrentLocationX(getCurrentLocationX()+1);
			
		}
		else if (getCurrentOrientation()=='N' && (!Grid.getGridScentY(getCurrentLocationY()+1) || !Grid.getGridScentX(getCurrentLocationX())))
		{
			setCurrentLocationY(getCurrentLocationY()+1);
		}
		else if (getCurrentOrientation()=='W'&& (!Grid.getGridScentX(getCurrentLocationX()-1) || !Grid.getGridScentY(getCurrentLocationY())))
		{
			
			setCurrentLocationX(getCurrentLocationX()-1);
		}
		else if(getCurrentOrientation()=='S' && (!Grid.getGridScentY(getCurrentLocationY()-1) || !Grid.getGridScentX(getCurrentLocationX())))
		{
			
			setCurrentLocationY(getCurrentLocationY()-1);
		}
	}
	
	
}



