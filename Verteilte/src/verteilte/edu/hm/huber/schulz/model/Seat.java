package verteilte.edu.hm.huber.schulz.model;

public class Seat {

	private final int id;

	//gibt an, ob Platz besetzt ist
	private boolean inUse;

	public Seat(final int id) {
		this.id = id;
		this.inUse = false;
	}

	public boolean isInUse() {
		return inUse;
	}

	public void setInUse(boolean inUse) {
		this.inUse = inUse;
	}

	public int getId() {
		return id;
	}
}
