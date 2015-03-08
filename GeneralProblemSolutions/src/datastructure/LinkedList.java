package datastructure;

public class LinkedList {

	private Node head,tail;
	private int size=0;
	
	
	
	public void addNode(Vertex value)
	{
		try
		{
		if(head==null)
		{
			Node node=new Node(value);
			node.next=null;
			head=node;
			
			tail=head;
		}
		else
		{
			Node node=new Node(value);
			node.next=null;
			tail.next=node;
			tail=node;
		}
		size++;
		}catch(Exception e){System.out.println("Exception: "+e);}
	}
	
	public Vertex getNode(int index)
	{
		if(head==null)
			return null;
		Node tmp=head;
		int i=1;
			
		
		while(i<=index)
		{
			tmp=tmp.next;
			i++;
		}
		
		return tmp.value;
	}
	
	public int getSize()
	{
		return size;
	}
	
}


