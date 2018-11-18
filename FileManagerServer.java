import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.io.*;

public class FileManagerServer implements FileManager {

	private boolean com = true;
	private Priority[] c = new Priority[3];
	private String[] file = {"arquivo01.txt", "arquivo02.txt", "arquivo03.txt"};

	public FileManagerServer(int p) {
		if(p == 1){
			this.c[0] = new ReaderPriority();
			this.c[1] = new ReaderPriority();
			this.c[2] = new ReaderPriority();
		}
		else{
			this.c[0] = new NoPriority();
			this.c[1] = new NoPriority();
			this.c[2] = new NoPriority();
		}

	}

	private void sl(){
		try{
			Thread.sleep(1000);
		}
		catch(Exception err){
			System.out.println("ERRO NO SLEEP");
		}
	}

	public void write(int f, String text){
		System.out.println("entrando para escrita: "+Integer.toString(f)+" "+text+" no arquivo "+Integer.toString(f));
		this.c[f].writeLock();
		sl();
		write_file(file[f],text);
		System.out.println("saindo da escrita: "+Integer.toString(f)+" "+text+" no arquivo "+Integer.toString(f));
		this.c[f].writeUnlock();
	}

	public String read(int f, int line){
		System.out.println("entrando para leitura: "+Integer.toString(f)+" "+Integer.toString(line)+" no arquivo "+Integer.toString(f));
		this.c[f].readLock();
		sl();
		String r = read_file(file[f],line);
		System.out.println("saindo da leitura: "+Integer.toString(f)+" "+Integer.toString(line)+" no arquivo "+Integer.toString(f));
		this.c[f].readUnlock();
		return r;
	}

	public static void main(String args[]) {
		try {
			//inicializando
			FileManagerServer obj = new FileManagerServer(Integer.parseInt(args[0]));
			FileManager stub = (FileManager) UnicastRemoteObject.exportObject(obj,0);
			Registry registry = LocateRegistry.createRegistry(5035);
			//Registry registry = LocateRegistry.getRegistry(1099);
			registry.rebind("FileManager", stub);
			System.out.println("Servidor pronto!");
		} catch (Exception e) {
			System.err.println("Capturando exceção no Servidor: " + e.toString());
			e.printStackTrace();
		}
	}

	private void write_file(String file, String content){
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))){
			bw.write(content);
			bw.newLine();
			bw.flush();
		}
		catch(Exception err){
			System.out.println("ERROR");
		}
	}

	private String read_file(String file, int line){
		if (line<0){
			return null;
		}
		try(BufferedReader br = new BufferedReader(new FileReader(file))){
			for(int i = 1; i < line; i++) 
				br.readLine(); 
			return br.readLine();   //caso não exista retorna nulo
		}
		catch(Exception err){
			System.out.println("ERROR");
			return null;
		}
	}
}
