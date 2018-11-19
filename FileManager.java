import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public interface FileManager extends Remote {
	void write(int file, String text) throws RemoteException;
  	String read(int file, int line) throws RemoteException;
  	public final static String NAME = "FileManager";
}