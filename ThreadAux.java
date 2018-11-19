import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.*;



public class ThreadAux implements Runnable {
    private int mode;
    private int id;
    public ThreadAux(int id,int m) {
        this.id = id;
        this.mode = m;
        new Thread(this).start();
    }
    
    @Override
    public void run() {
        if (Thread.currentThread().getId() == 1){
            return;
        }
        try{
            int n,line;
            Random r = new Random();
            // Registry registry = LocateRegistry.getRegistry(null);
            System.out.println("Thread OK");
            // Registry registry = LocateRegistry.getRegistry("52.26.6.52",1099);
            // System.out.println("get Registry");
            FileManager stub = (FileManager) Naming.lookup("rmi://52.26.6.52/"+FileManager.NAME);
            System.out.println("Passei pelo lookup");
            if (mode==1){
                n = r.nextInt(3);
                System.out.println("Antes do Write");
                stub.write(n, Integer.toString(id));
                System.out.println("Depois do Write");
            }
            else{
                n = r.nextInt(3);
                line = r.nextInt(10);
                System.out.println("Antes do Read");
                stub.read(n, line);
                System.out.println("Depois do Write");
            }
            System.out.println(Thread.currentThread().getId() + " executei");
            Thread.sleep(2000);
        }
        catch(Exception err){
            System.err.println("Capturando a exceção no Cliente: " + err.toString());
        }
    }
    
}