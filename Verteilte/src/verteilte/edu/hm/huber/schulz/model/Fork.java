package verteilte.edu.hm.huber.schulz.model;

import java.util.concurrent.Semaphore;

public class Fork {
	
	private final int id;
	private boolean used;
	private final Semaphore semaphore;

	public Fork(final int id){
		this.id = id;
		used = false;
		this.semaphore = new Semaphore(1);
	}
	
	public int getId() {
		return id;
	}
	
	public boolean isUsed(){
		return used;
	}
	
	public void setUsed(final boolean used){
		this.used = used;
	}
	
	public Semaphore getSemaphore() {
		return semaphore;
	}
}
