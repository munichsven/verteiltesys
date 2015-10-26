package verteilte.edu.hm.huber.schulz.model;

import java.util.Observable;
import java.util.Observer;

public class Philosoph extends Thread implements Observer{
	
	private final int name;
	private int counter;
	private final boolean hungry;
	private final int eatMax;
	
	public Philosoph(final boolean hungry, final int name){
		this.hungry = hungry;
		counter = 0;
		this.name = name;
		if(this.isHungry())eatMax = 10;
		else eatMax = 3;
	}
	
	public void run(){
		while(true){
			
			for(int i = 0; i < eatMax; i++){
				meditate();
				eat();
			}

			regenerate();
		}
	}
	
	/**
	 * Setzt den Philosophen zum schlafen für eine Sekunde
	 */
	public void regenerate(){
		try {
			this.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println("Fehler schlafen: "+this.getPhilosophsId() + "Thread");
			e.printStackTrace();
		}
	}
	
	/**
	 * Setzt den Philosophen zum medidieren für zwei Sekunden
	 */
	public void meditate(){
		try {
			this.sleep(2000);
		} catch (InterruptedException e) {
			System.out.println("Fehler meditieren: "+this.getPhilosophsId() + "Thread");
			e.printStackTrace();
		}
	}
	
	/**
	 * Wenn der Philosoph gegessen hat wird der counter um 1 erhöht.
	 */
	public void eat(){
		
		
		
		
		counter++; //nach erfolgreichem Essvorgang
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
	public boolean isHungry() {
		return hungry;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
