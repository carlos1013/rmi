import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
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
            Registry registry = LocateRegistry.getRegistry("54.184.234.170", 5035);
            System.out.println("Passei pelo registry");
            FileManager stub = (FileManager) registry.lookup("FileManager");
            //System.out.println("Passei pelo Stub");
            if (mode==1){
                n = r.nextInt(3);
                stub.write(n, Integer.toString(id));
            }
            else{
                n = r.nextInt(3);
                line = r.nextInt(10);
                stub.read(n, line);
            }
            System.out.println(Thread.currentThread().getId() + " executei");
            Thread.sleep(2000);
        }
        catch(Exception err){
            System.err.println("Capturando a exceção no Cliente: " + err.toString());
        }
    }
    
}