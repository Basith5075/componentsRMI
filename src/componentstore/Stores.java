package componentstore;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Stores extends Remote{
	
	public Object getEveStoreObject() throws RemoteException;
}
