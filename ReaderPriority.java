import java.util.concurrent.*; 

public class ReaderPriority implements Priority {

	Semaphore read = new Semaphore(1, true);
	Semaphore write = new Semaphore(1, true);
	Semaphore access = new Semaphore(1, true);
	Semaphore control = new Semaphore(1, true); // semaforo para controlar a variavel readers
	int qtd_leitores=0;

	public void readLock() {
		try{
			this.control.acquire();
			qtd_leitores++;
			if (qtd_leitores==1){
				this.access.acquire();
			}
			this.control.release();
			this.read.acquire();
		}
		catch(Exception err){
			System.out.println("ERROR");
		}		
	}

	public void writeLock() {
		try{
			this.write.acquire();
			this.access.acquire();
		}
		catch(Exception err){
			System.out.println("ERROR");
		}
	}

	public void readUnlock() {
		try{
			this.control.acquire();
			qtd_leitores--;
			this.read.release();
			if (qtd_leitores==0){
				this.access.release();
			}
			this.control.release();
		}
		catch(Exception err){
			System.out.println("ERROR");
		}
	}

	public void writeUnlock() {
		try{
			this.access.release();
			this.write.release();
		}
		catch(Exception err){
			System.out.println("ERROR");
		}
	}

}