package verteilte.edu.hm.huber.schulz.model;

import java.util.concurrent.Semaphore;

public class Seat {

	private final int id;

	// gibt an, ob Platz besetzt ist, d.h. aber nicht, dass sie schon isst
	private boolean reserved;

	// ob die Person auf dem Platz sitzt UND auch isst
	private boolean inUse;

	private final Semaphore semaphore;
	private final Fork right;
	private final Fork left;

	public Seat(final int id, final Fork right, final Fork left) {
		this.id = id;
		this.inUse = false;
		this.reserved = false;
		this.right = right;
		this.left = left;
		this.semaphore = new Semaphore(1);
	}

	public boolean isInUse() {
		return inUse;
	}

	public void setInUse(boolean inUse) {
		this.inUse = inUse;
	}

	public boolean isReserved() {
		return reserved;
	}

	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}

	public int getId() {
		return id;
	}

	public Semaphore getSemaphore() {
		return semaphore;
	}

	public Fork getRight() {
		return right;
	}

	public Fork getLeft() {
		return left;
	}
}