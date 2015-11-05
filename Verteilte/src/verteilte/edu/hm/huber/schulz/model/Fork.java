package verteilte.edu.hm.huber.schulz.model;

public class Fork {
	
	private final int id;
	private boolean used;

	public Fork(final int id){
		this.id = id;
		used = false;
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
}
