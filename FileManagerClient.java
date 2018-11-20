import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.*;

public class FileManagerClient {
	private FileManagerClient() {}
	public static void main(String[] args) {
		Random r = new Random();
		int n, line;
		try {
			//inicializando 
			/*Registry registry = LocateRegistry.getRegistry(null);
			FileManager stub = (FileManager) registry.lookup("FileManager");*/
			ThreadAux c1 = new ThreadAux(Integer.parseInt(args[0]),r.nextInt(2));
			ThreadAux c2 = new ThreadAux(Integer.parseInt(args[0]),r.nextInt(2));
			ThreadAux c3 = new ThreadAux(Integer.parseInt(args[0]),r.nextInt(2));
			ThreadAux c4 = new ThreadAux(Integer.parseInt(args[0]),r.nextInt(2));
			ThreadAux c5 = new ThreadAux(Integer.parseInt(args[0]),r.nextInt(2));
			ThreadAux c6 = new ThreadAux(Integer.parseInt(args[0]),r.nextInt(2));
			ThreadAux c7 = new ThreadAux(Integer.parseInt(args[0]),r.nextInt(2));
			ThreadAux c8 = new ThreadAux(Integer.parseInt(args[0]),r.nextInt(2));
			ThreadAux c9 = new ThreadAux(Integer.parseInt(args[0]),r.nextInt(2));
			ThreadAux c10 = new ThreadAux(Integer.parseInt(args[0]),r.nextInt(2));

			/*
			n = r.nextInt(3);
			stub.write(n, args[0]);

			n = r.nextInt(3);
			line = r.nextInt(10);
			System.out.println(stub.read(n, line));*/
		} catch (Exception e) {
			System.err.println("Capturando a exceção no Cliente: " + e.toString());
			e.printStackTrace();
		}
	}
}
