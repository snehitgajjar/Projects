package datastructure;

public class Stack {
	NodeStack top=null;
	
	public void push(Object value)
	{
		
		if(top!=null)
		{
			NodeStack temp=new NodeStack(value);
			temp.next=top;
			top=temp;
		}
		else
		{
			top=new NodeStack(value);
			
			
		}
		
	}
	
	
	public Object pop()
	{
		Object value=top.value;
		top=top.next;
		
		return value;
	}
	
	
	
	
}


class NodeStack
{
	Object value;
	NodeStack next;
	
	public NodeStack(Object value)
	{
		this.value=value;
	}
	
}
