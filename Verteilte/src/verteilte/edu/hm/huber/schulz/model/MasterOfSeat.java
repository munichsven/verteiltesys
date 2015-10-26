package verteilte.edu.hm.huber.schulz.model;

import java.util.Observable;

public class MasterOfSeat extends Observable {

	private final Seat seat;
	
	private final int id;

	// der Philosoph der gerade auf dem Platz sitzt
	private Philosoph currentGuest;

	public MasterOfSeat(final Seat seat, final int id) {
		this.seat = seat;
		this.id = id;
	}
	
	// Zuweisung des ersten Anfragenden Gastes an den freien Platz
	public void reserveSeat(Philosoph philosoph) {
		this.currentGuest = philosoph;
		this.seat.setReserved(true);
	}
	
	//Freigabe des Platzes nachdem der Gast mit dem Essen fertig ist
	public void releaseSeat(){
		this.seat.setReserved(false);
		//Gäste über freien Platz informieren
		setChanged();
		notifyObservers(this.seat.getId()); //teilt allen observern die id des aktuell freigewordenen Tisches mit
	}

	public Philosoph getCurrentGuest() {
		return currentGuest;
	}

	public void setCurrentGuest(Philosoph currentGuest) {
		this.currentGuest = currentGuest;
	}

	public Seat getSeat() {
		return seat;
	}

	public int getId() {
		return id;
	}
	
	

}
