package verteilte.edu.hm.huber.schulz.model;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Philosoph extends Thread {

	private final int name;
	private int counter;
	private int eatCounter;
	private final boolean hungry;
	private final int eatMax;
	private final ArrayList<Seat> seatList;
	private final Random random;
	private boolean banned = false;

	public Philosoph(final boolean hungry, final int name,
			final ArrayList<Seat> seatList) {
		this.hungry = hungry;
		counter = 0;
		eatCounter = 0;
		this.name = name;
		if (isHungry())
			eatMax = Constants.EAT_MAX_HUNGRY;
		else
			eatMax = Constants.EAT_MAX_NORMAL;
		this.seatList = seatList;
		random = new Random();
	}

	public void run() {
		while (true) {
			if (banned) {
				System.out.println(this.name + " interrupted");
				banFromTable();
				while (banned) {
					banFromTable();
				}
			} else {
				meditate();
				eat();
				if (counter == eatMax) {
					regenerate();
					counter = 0;
				}
			}
		}
	}

	/**
	 * Philosoph schläft.
	 */
	public void regenerate() {
		threadBreak(Constants.SLEEP_LENGTH);
	}

	/**
	 * Philosoph meditiert.
	 */
	public void meditate() {
		threadBreak(Constants.MEDITATE_LENGTH);
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
			seatFound = crntSeat.getSemaphore().tryAcquire();

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
		getForks(crntSeat);
		threadBreak(Constants.EAT_LENGTH);
		crntSeat.getLeft().getSemaphore().release();
		crntSeat.getRight().getSemaphore().release();
		crntSeat.getSemaphore().release();
		counter++;
		eatCounter++;
		System.out.println("Philosoph " + getPhilosophsId() + " hat an Platz "
				+ crntSeat.getId() + " zum insg. " + eatCounter
				+ ". mal gegessen.  :" + isHungry());
	}

	/**
	 * @return counter - gibt den Aktuellen Essenstand des Philosophen zurück.
	 */
	public int getEatCounter() {
		return eatCounter;
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

	public boolean isBanned() {
		return banned;
	}

	public void setBanned(boolean banned) {
		this.banned = banned;
	}

	private void banFromTable() {
		try {
			Thread.sleep(Constants.EAT_LENGTH * Constants.BAN_FACTOR);
		} catch (InterruptedException e) {
		}
	}

	private boolean getForks(final Seat seat) {
		Fork left = seat.getLeft();
		Fork right = seat.getRight();
		boolean hasLeft = false;
		boolean hasRight = false;
		boolean hasBoth = false;

		while (!hasBoth) {
			int tries = 0;
			while (!hasLeft) {
				try {
					hasLeft = left.getSemaphore().tryAcquire(1,
							TimeUnit.MILLISECONDS);
				} catch (InterruptedException e) {
				}
			}
			while (!hasRight && tries <= Constants.TRIES_TO_GET_FORK) {
				try {
					hasRight = right.getSemaphore().tryAcquire(
							Constants.TIME_TO_GET_RIGHT_FORK,
							TimeUnit.MILLISECONDS);
					tries++;
				} catch (InterruptedException e) {
					left.getSemaphore().release(); //damit man anderen nach einer Verbannung nicht die gabel wegsperrt
				}
			}
			hasBoth = hasRight;
			if (!hasBoth) {
				left.getSemaphore().release();
				try {
					Thread.sleep(Constants.TIME_UNTIL_NEW_FORKTRY);
				} catch (InterruptedException e) {
				}
			}
		}
		return hasBoth;
	}

	private void threadBreak(final long timeToBreak) {
		try {
			Thread.sleep(timeToBreak);
		} catch (InterruptedException e) {
		}
	}

}