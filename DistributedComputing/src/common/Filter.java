package common;
import java.rmi.Remote;
import java.rmi.RemoteException;
public interface Filter extends Remote 
{
	public Artist[] filter(Predicate pred) throws RemoteException;
}