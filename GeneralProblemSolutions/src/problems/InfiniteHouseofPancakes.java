package problems;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class InfiniteHouseofPancakes {

	static Random rand=new Random();
	public static void main(String args[])
	{
		Scanner in=new Scanner(System.in);

		int totalCases=Integer.parseInt(in.nextLine());

		for(int i=0;i<totalCases;i++)
		{
			int numberOfPerson=Integer.parseInt(in.nextLine());
			String personString=in.nextLine();

			System.out.println(totalNumberOfMin(personString, numberOfPerson));
		}

	}

	public static int totalNumberOfMin(String persons,int numberOfPerson)
	{

		String tokens[]=persons.split(" ");
		int timeTaken=0;
		ArrayList<Integer> numberOfPancakes=new ArrayList<Integer>();
		ArrayList<Integer> nonEmpty=new ArrayList<Integer>();
		ArrayList<Integer> empty=new ArrayList<Integer>();
		ArrayList<Integer> dinerWithMoreThanOneCake=new ArrayList<Integer>();


		for(int i=0;i<tokens.length;i++)
		{
			numberOfPancakes.add(Integer.parseInt(tokens[i]));
			if(Integer.parseInt(tokens[i])>1) {
				dinerWithMoreThanOneCake.add(i);
			}
			nonEmpty.add(i);

		}



		while(nonEmpty.size()==0)
		{
			for(int i=0;i<numberOfPancakes.size();i++)
			{
				if(empty.size()==0)
				{
					numberOfPancakes.add(numberOfPancakes.get(i)-1);
					if(numberOfPancakes.get(i)==0)
					{
						empty.add(i);
						nonEmpty.remove(i);

					}

				}
				else if(dinerWithMoreThanOneCake.size()==0)
				{
					if(numberOfPancakes.get(i)!=0) {
						numberOfPancakes.add(numberOfPancakes.get(i)-1);
					}
				}
				else
				{

				}



			}
			timeTaken++;
			if(empty.size()>0 && dinerWithMoreThanOneCake.size()>0)
			{
				int randomSource=rand.nextInt(dinerWithMoreThanOneCake.size());
				numberOfPancakes.add(numberOfPancakes.get(randomSource)-1);

				int randomDest=rand.nextInt(empty.size());
				numberOfPancakes.add(numberOfPancakes.get(randomDest));
				empty.remove()



				timeTaken++;

			}


		}

		return timeTaken;
	}



}
