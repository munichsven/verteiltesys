package verteilte.edu.hm.huber.schulz.controller;

import java.util.ArrayList;
import java.util.Random;

import verteilte.edu.hm.huber.schulz.model.Fork;
import verteilte.edu.hm.huber.schulz.model.Philosoph;
import verteilte.edu.hm.huber.schulz.model.Seat;
import verteilte.edu.hm.huber.schulz.model.TableMaster;

/**
 * 
 * @author Zwen
 *
 */
public class Controller {

	private ArrayList<Seat> seatList;
	private ArrayList<Philosoph> philosphenList;
	private ArrayList<Fork> forkList;
	private final int seatCount;
	private final int philosophCount;
	private int hungryPeople;
	private Random random;
	private final int maxHungryPeople;

	public Controller(final int seat, final int philosoph, final int maxHungryPeople) {
		this.seatCount = seat;
		this.philosophCount = philosoph;
		this.maxHungryPeople = maxHungryPeople;
		random = new Random();
		hungryPeople = 0;

		philosphenList = new ArrayList<Philosoph>();
		seatList = new ArrayList<Seat>();
		forkList = new ArrayList<Fork>();

		for(int i = 0; i < seatCount; i++ ){
			Fork newFork = new Fork(i + 1);
			forkList.add(newFork);
		}
		for (int i = 0; i < seatCount; i++) {
			int j = i + 1;
			if(j == forkList.size()){
				j = 0;
			}
			Seat newSeat = new Seat(i + 1, forkList.get(i), forkList.get(j));
			seatList.add(newSeat);
		}

		for (int i = 0; i < philosophCount; i++) {
			Philosoph phil = new Philosoph(randomHungry(), i + 1, seatList);
			philosphenList.add(phil);
			phil.start();
		}
		
	//	TableMaster master = new TableMaster(philosphenList);
	//	master.start();
	}

	/**
	 * Überprüft ob die Maximale Anzahl von hungrigen Philosophen erreicht ist,
	 * wenn nicht wird ein Random boolean zurück gegeben.
	 * 
	 * @return hungry - gibt zurück ob der Philosoph hungrig ist oder nicht.
	 */
	private boolean randomHungry() {
		final boolean hungry;
		if (hungryPeople < maxHungryPeople) {
			hungry = random.nextBoolean();

			if (hungry) {
				hungryPeople++;
			}

			return hungry;
		} else
			return false;
	}

	public ArrayList<Seat> getSeatList() {
		return seatList;
	}

	public void setSeatList(ArrayList<Seat> seatList) {
		this.seatList = seatList;
	}

	public ArrayList<Philosoph> getPhilosphenList() {
		return philosphenList;
	}

	public void setPhilosphenList(ArrayList<Philosoph> philosphenList) {
		this.philosphenList = philosphenList;
	}
}