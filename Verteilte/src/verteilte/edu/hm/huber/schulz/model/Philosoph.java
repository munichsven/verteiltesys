package verteilte.edu.hm.huber.schulz.model;

public class Philosoph extends Thread{
	
	private final int name;
	private int counter;
	private final boolean ishungry;
	
	public Philosoph(final boolean ishungry){
		this.ishungry = ishungry;
		counter = 0;
		name = setName();
	}
	
	public static int setName(){
		return 0;
	}
	
	/**
	 * Wenn der Philosoph gegessen hat wird der counter um 1 erhöht.
	 */
	public void eat(){
		counter++;
	}

	/**
	 * @return counter - gibt den Aktuellen Essenstand des Philosophen zurück.
	 */
	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	/**
	 * @return id - gibt den Namen bzw. die Id des Philosophen
	 */
	public int getPhilosophsId() {
		return name;
	}

	/**
	 * @return ishungry - gibt an Ob der Philosoph hungrig ist.
	 */
	public boolean isIshungry() {
		return ishungry;
	}

}
