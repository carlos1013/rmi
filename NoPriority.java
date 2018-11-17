import java.util.concurrent.*; 

public class NoPriority implements Priority {

	Semaphore access = new Semaphore(1, true);

	public void readLock() {
		try{
			this.access.acquire();
		}
		catch(Exception err){
			System.out.println("ERROR");
		}
	}

	public void writeLock() {
		try{
			this.access.acquire();
		}
		catch(Exception err){
			System.out.println("ERROR");
		}
	}

	public void readUnlock() {
		this.access.release();
	}

	public void writeUnlock() {
		this.access.release();
	}

}