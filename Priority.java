import java.util.concurrent.*; 

public interface Priority {

	public void readLock();

	public void writeLock();

	public void readUnlock();

	public void writeUnlock();

}