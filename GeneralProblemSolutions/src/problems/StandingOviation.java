package problems;

import java.util.Scanner;

public class StandingOviation {



	public static void main(String args[])
	{
		Scanner in=new Scanner(System.in);

		int numberOfTestCases=Integer.parseInt(in.nextLine());

		for(int i=0;i<numberOfTestCases;i++) {
			System.out.println(retunSupporter(in.nextLine(),i+1));
		}




	}




	public static String retunSupporter(String cases,int caseNumber)
	{
		//System.out.println("1");
		int numberOfSupporter=0;
		int currentStandingAudience=0;
		String tokens[]=cases.split(" ");
		int sMax=Integer.parseInt(tokens[0]);
		String followingString=tokens[1];

		//&& followingString.charAt(i)!='0'
		for(int i=0;i<sMax+1;i++)
		{
			//System.out.println("2");

			if(i==0 && followingString.charAt(i)=='0')
			{
				currentStandingAudience++;
				numberOfSupporter++;
			}
			else if(currentStandingAudience>=i ) {
				currentStandingAudience+=Integer.parseInt(followingString.charAt(i)+"");
			}
			else
			{
				numberOfSupporter+= i-currentStandingAudience;
				currentStandingAudience+=i-currentStandingAudience+Integer.parseInt(followingString.charAt(i)+"");
			}
		}
		return "Case #"+caseNumber+": "+numberOfSupporter;

	}


}