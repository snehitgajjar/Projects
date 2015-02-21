package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.StringTokenizer;

import javax.swing.plaf.multi.MultiButtonUI;

import model.Mutant;

import view.Grid;

public class Main {

	private static Mutant mutant=null;
	public static void main(String args[])
	{
		String line="";
		int count=1;
		try
		{
			
			/*
			 *  reads given input file from the path
			 */
			File file=new File("./inputFile");
			
			BufferedReader br=new BufferedReader(new FileReader(file));
			String xy=br.readLine();
			
			/*
			 * splits the first line by the token 'space' and then retrieves the right most coordinates.
			 */
			String tokens[]=xy.split(" ");
			int x=Integer.parseInt(tokens[0]);
			int y=Integer.parseInt(tokens[1]);
			
			
			//gets the instance of Grid class.
			Grid grid=Grid.getInstance();
			
			//sets the size of grid.
			grid.setGridSizeX(x);
			grid.setGridSizeY(y);
			
			
			/*
			 * this loop goes until the end of file.
			 */
			while((line=br.readLine())!=null && line.length()!=0)
			{
				
				
				
				char currentOrienatation=' ';
			
				if(count==1)
				{
					/*
					 * whenever the code in if body is executed then the current position and orientation
					 * is set by in the mutant class.
					 */
					String []st=line.split(" ");
					int x_m=Integer.parseInt(st[0]);
					
				
					int y_m=Integer.parseInt(st[1]);
					currentOrienatation=st[2].charAt(0);
					mutant=new Mutant(x_m,y_m,currentOrienatation);
					
					count--;
				}
				else
				{
				/*
				 * Below for loop go through the each command of right,left and forward.	
				 */
					for(int i=0;i<line.length();i++)
					{
						
						if(line.charAt(i)=='F') // if the letter is F then it calls goForward()
						{
							mutant.goForward();
							
						}
						else if(line.charAt(i)=='R') // if the letter is R then it calls turnRight()
						{
							
							mutant.turnRight();
							
						}
						else if(line.charAt(i)=='L') // if the letter is L then it calls turnLeft()
						{
							mutant.turnLeft();
							
						}
						
						if(mutant.isLost()) // if robot(mutant) is lost then loop breaks and go to another mutant.
						{
							break;
						}
					}
					String status=mutant.isLost() ? "Lost" : "";
					System.out.println(mutant.getCurrentLocationX() +" "+mutant.getCurrentLocationY()+" "+mutant.getCurrentOrientation() +" "+status);
					count++;
				}
				
			}
			br.close();
		}
		catch(Exception e)
		{
			System.out.println("Exception : "+ e);
		}
	}
}





