package verteilte.edu.hm.huber.schulz.model;

import java.util.ArrayList;
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
			// Wenn der Philosph 5 mal gegessen hat wird er schlafen gelegt
			// if(getCounter() == eatMax){
			// regenerate();
			// }
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
			this.sleep(0);
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
		final int startIndex = random.nextInt(seatList.size());
		int index = startIndex;

		while (!seatFound) {
			crntSeat = seatList.get(index);
			// System.out.println("Philosoph: " + this.getPhilosophsId()
			// + " frägt an: Platz " + crntSeat.getId());

			seatFound = crntSeat.getSemaphore().tryAcquire();

			// Wenn der Index gleich der Größe ist wird er auf 0 gesetzt um
			// wieder beim Anfang anzufangen
			if (index == seatList.size() - 1) {
				index = 0;
			} else {
				index++;
			}
			// Schwellwert, sodass nach n*2 Probiervorängen geblockt wird
			// Phils anschließend in SEGMENTIERTE Warteliste z.B. n/2 die dann
			// echt gleichzeitig
			// notified werden können
			// TODO
		}
		// versuchen die Gabeln aufzunehmen
		getForks(crntSeat);
		// TODO
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		crntSeat.getLeft().getSemaphore().release();
		crntSeat.getRight().getSemaphore().release();
		crntSeat.getSemaphore().release();
		counter++; // nach erfolgreichem Essvorgang
		System.out.println("Philosoph " + this.getPhilosophsId()
				+ " hat an Platz " + crntSeat.getId() + " zum insg. " + counter
				+ ". mal gegessen.");
	}

	private boolean getForks(final Seat seat) {
		Fork left = seat.getLeft();
		Fork right = seat.getRight();
		boolean hasLeft = false;
		boolean hasRight = false;
		boolean hasBoth = false;
		int tries = 0;

		while (!hasBoth) {
			while (!hasLeft) {
				try {
					// TODO Essenszeit aus Konstante holen
					hasLeft = left.getSemaphore().tryAcquire(
							Constants.EAT_LENGTH / 10, TimeUnit.MILLISECONDS);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			while (!hasRight && tries <= 5) {
				try {
					// TODO Essenszeit aus Konstante holen
					hasRight = right.getSemaphore().tryAcquire(
							Constants.EAT_LENGTH / 10, TimeUnit.MILLISECONDS);
					tries++;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			hasBoth = hasRight;
			if(!hasBoth){
				left.getSemaphore().release();
				try {
					Thread.sleep(Constants.EAT_LENGTH/20);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return hasBoth;
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