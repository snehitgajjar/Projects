package datastructure;

import java.util.ArrayList;

public class Graph {

	ArrayList<Vertex> vertexs=new ArrayList<Vertex>();
	
	public void addVertex(Object value)
	{
		Vertex v=new Vertex(value);
		vertexs.add(v);	
	}
	
	public void addEdge(Vertex source,Vertex destination)
	{
		for(int i=0;i<vertexs.size();i++)
		{
			if(vertexs.get(i).value==source.value)
			{
				vertexs.get(i).adjcencyList.addNode(destination);
			}
		}
	}
	
	
	public Vertex getVertex(Object value)
	{
		
		for(int i=0;i<vertexs.size();i++)
		{
			if(vertexs.get(i).value==value)
			{
				return vertexs.get(i);
			}
		}
		return null;
	}
	
	
	public static void main(String args[])
	{
		Graph g=new Graph();
		
		g.addVertex("A");
		g.addVertex("B");
		g.addVertex("C");
		
		
		
		g.addEdge(g.getVertex("A"), g.getVertex("B"));
		g.addEdge(g.getVertex("A"), g.getVertex("C"));
		g.addEdge(g.getVertex("B"), g.getVertex("C"));
		g.addEdge(g.getVertex("B"), g.getVertex("A"));
		g.addEdge(g.getVertex("C"), g.getVertex("A"));
		
		System.out.println("Vertex A: "+g.getVertex("A").value+"\n"+"It's adjencient list is as below: "+g.getVertex("A").adjcencyList.getSize());
		for(int i=0;i<g.getVertex("A").adjcencyList.getSize();i++)
		{
			System.out.println(g.getVertex("A").adjcencyList.getNode(i).value);
		}
		
		System.out.println("Vertex B: "+g.getVertex("B").value+"\n"+"It's adjencient list is as below: "+g.getVertex("B").adjcencyList.getSize());
		for(int i=0;i<g.getVertex("B").adjcencyList.getSize();i++)
		{
			System.out.println(g.getVertex("B").adjcencyList.getNode(i).value);
		}
		
		System.out.println("Vertex C: "+g.getVertex("C").value+"\n"+"It's adjencient list is as below: "+g.getVertex("C").adjcencyList.getSize());
		for(int i=0;i<g.getVertex("C").adjcencyList.getSize();i++)
		{
			System.out.println(g.getVertex("C").adjcencyList.getNode(i).value);
		}
		
		
	}
	
	
}

class Vertex
{
	Object value;
	LinkedList adjcencyList;
	
	public Vertex(Object value)
	{
		this.value=value;
		adjcencyList=new LinkedList();
	}
	
}