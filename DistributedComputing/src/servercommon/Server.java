	package servercommon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import common.Artist;
import common.Filter;
import common.Predicate;

public class Server implements Filter{

	@Override
	public Artist[] filter(Predicate pred) throws RemoteException {
		// TODO Auto-generated method stub
		
		try
		{
			File file =new File("./table1.in");
			BufferedReader br=new BufferedReader(new FileReader(file));
			String line;
			String resultArtist[]={};
			Artist artist;
			int i=0;
			while((line=br.readLine())!=null)
			{
				String table1[]=line.split("<SEP>");
				artist=new Artist();
				artist.id=table1[0];
				artist.location=table1[2];
				artist.name=table1[1];
				for(int j=3;j<table1.length;j++)
				artist.songs[j]=table1[j];
				
				boolean bool=pred.match(artist);
				
				if(bool==true)
				{
					resultArtist[i]=table1[1];
				}
				
			}
			
		}
		catch(Exception e)
		{
			System.out.println("Error occured in server :"+e);
		}
		
		
		return null;
	}
	
	
	
	public static void main(String args[]) 
	{
		try
		{
		Server obj = new Server();
		Filter stub = (Filter) UnicastRemoteObject.exportObject(obj, 0);
		Registry reg = LocateRegistry.createRegistry(2001);
		// Registry registry = LocateRegistry.getRegistry();
		  //  registry.bind("Hello", stub);
		reg.rebind("CtrlServ", stub);
		    System.err.println("Server ready");
		}
		catch(Exception e)
		{
			System.out.println("Error in starting registery."+e);
		}
	}

}
