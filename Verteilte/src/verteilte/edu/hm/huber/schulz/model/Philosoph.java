package verteilte.edu.hm.huber.schulz.model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Philosoph extends Thread {

	private final int name;
	private int counter;
	private final boolean hungry;
	private final int eatMax;
	private final ArrayList<Seat> seatList;
	private final Random random;

	public Philosoph(final boolean hungry, final int name,
			final ArrayList<Seat> seatList) {
		this.hungry = hungry;
		this.counter = 0;
		this.name = name;
		if (this.isHungry())
			eatMax = 10;
		else
			eatMax = 3;
		this.seatList = seatList;
		this.random = new Random();
	}

	public void run() {
		while (true) {
			meditate();
			eat();
			//regenerate();
		}
	}

	/**
	 * Setzt den Philosophen zum schlafen für eine Sekunde
	 */
	public void regenerate() {
		try {
			this.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println("Fehler schlafen: " + this.getPhilosophsId()
					+ "Thread");
			e.printStackTrace();
		}
	}

	/**
	 * Setzt den Philosophen zum medidieren für zwei Sekunden
	 */
	public void meditate() {
		try {
			this.sleep(2000);
		} catch (InterruptedException e) {
			System.out.println("Fehler meditieren: " + this.getPhilosophsId()
					+ "Thread");
			e.printStackTrace();
		}
	}

	/**
	 * Wenn der Philosoph gegessen hat wird der counter um 1 erhöht.
	 */
	public void eat() {
		boolean seatFound = false;
		Seat crntSeat = null;
		while (!seatFound) {
			int i = this.random.nextInt(seatList.size());
			crntSeat = seatList.get(i);
			System.out.println("Philosoph: " + this.getPhilosophsId()
					+ " frägt an: Platz " + crntSeat.getId());
			seatFound = crntSeat.getSemaphore().tryAcquire();
			//Schwellwert, sodass nach n*2 Probiervorängen geblockt wird
			//Phils anschließend in SEGMENTIERTE Warteliste z.B. n/2 die dann echt gleichzeitig
			//notified werden können
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// versuchen die Gabeln aufzunehmen
		// TODO
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		crntSeat.getSemaphore().release();
		counter++; // nach erfolgreichem Essvorgang
		System.out.println("Philosoph " + this.getPhilosophsId()
				+ " hat an Platz " + crntSeat.getId() + " zum insg. " + counter
				+ ". mal gegessen.");
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

}