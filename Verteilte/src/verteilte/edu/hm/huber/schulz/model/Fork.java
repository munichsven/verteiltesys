package verteilte.edu.hm.huber.schulz.model;

import java.util.concurrent.Semaphore;

public class Fork {
	
	private final int id;
	private final Semaphore semaphore;

	public Fork(final int id){
		this.id = id;
		this.semaphore = new Semaphore(1);
	}
	
	public int getId() {
		return id;
	}
	
	public Semaphore getSemaphore() {
		return semaphore;
	}
}
