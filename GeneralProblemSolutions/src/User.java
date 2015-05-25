import java.util.ArrayList;
import java.util.Scanner;


class Employee {

	String name;
	int id;
	String address;
	String gender;


	public void setName(String name)
	{
		this.name=name;

	}

	public void setId(int id)
	{
		this.id=id;
	}

	public void setAddress(String address)
	{
		this.address=address;
	}

	public void setGender(String gender)
	{
		this.gender=gender;
	}



	public String getName()
	{
		return name;
	}

	public int getId()
	{
		return id;
	}

	public String getAddress()
	{
		return address;
	}

	public String getGender()
	{
		return gender;
	}



}


public class User
{
	public static void main(String[] args)
	{
		ArrayList<Employee> list=new ArrayList<Employee>();
		String answer="no";
		Scanner scan=new Scanner(System.in);
		do
		{
			Employee emp=new Employee();
			System.out.println("Enter name: ");
			emp.setName(scan.nextLine());
			System.out.println("Enter id: ");
			emp.setId(Integer.parseInt(scan.nextLine()));
			System.out.println("Enter address: ");
			emp.setAddress(scan.nextLine());
			System.out.println("Enter gender: ");
			emp.setGender(scan.nextLine());

			System.out.println("Want to add more? ");
			answer=scan.nextLine();

			list.add(emp);

		}while(answer.equals("yes"));


		for(int i=0;i<list.size();i++)
		{
			Employee emp1=list.get(i);

			System.out.println("Employee No : "+(i+1));

			System.out.println("Name: "+emp1.getName());
			System.out.println("Id: "+emp1.getId());
			System.out.println("Address: "+emp1.getAddress());
			System.out.println("Gender: "+emp1.getGender());


		}


	}


}




