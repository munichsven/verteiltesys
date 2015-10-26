package verteilte.edu.hm.huber.schulz.controller;

import java.util.ArrayList;
import java.util.Random;

import verteilte.edu.hm.huber.schulz.model.MasterOfSeat;
import verteilte.edu.hm.huber.schulz.model.Philosoph;
import verteilte.edu.hm.huber.schulz.model.Seat;

/**
 * 
 * @author Zwen
 *
 */
public class Controller {

	private ArrayList<Seat> seatList;
	private ArrayList<MasterOfSeat> masterList;
	private ArrayList<Philosoph> philosphenList;
	private final int seat;
	private final int philosoph;
	private int hungryPeople;
	private Random random;
	private final int maxHungryPeople;
	
	public Controller(final int seat, final int philosoph, final int maxHungryPeople){
		this.seat = seat;
		this.philosoph = philosoph;
		this.maxHungryPeople = maxHungryPeople;
		random = new Random();
		hungryPeople = 0;
		
		masterList = new ArrayList<MasterOfSeat>();
		philosphenList = new ArrayList<Philosoph>();
		seatList = new ArrayList<Seat>();
		
		for(int i = 0 ; i < seat; i++){
			Seat newSeat = new Seat(i+1);
			MasterOfSeat master = new MasterOfSeat(newSeat, i+1);
			masterList.add(master);
			seatList.add(newSeat);
		}
		
		for(int i = 0; i < philosoph;i++){
			Philosoph phil = new Philosoph(randomHungry(), i+1);
			philosphenList.add(phil);
		}
	}
	
	/**
	 * Überprüft ob die Maximale Anzahl von hungrigen Philosophen erreicht ist, wenn nicht wird
	 * ein Random boolean zurück gegeben.
	 * @return hungry - gibt zurück ob der Philosoph hungrig ist oder nicht.
	 */
	private boolean randomHungry(){
		final boolean hungry;
		if(hungryPeople < maxHungryPeople ){
			hungry = random.nextBoolean();
			
			if(hungry){hungryPeople++;}
			
			return hungry;
		}
		else return false;
	}
	
	public ArrayList<Seat> getSeatList() {
		return seatList;
	}
	public void setSeatList(ArrayList<Seat> seatList) {
		this.seatList = seatList;
	}
	public ArrayList<MasterOfSeat> getMasterList() {
		return masterList;
	}
	public void setMasterList(ArrayList<MasterOfSeat> masterList) {
		this.masterList = masterList;
	}
	public ArrayList<Philosoph> getPhilosphenList() {
		return philosphenList;
	}
	public void setPhilosphenList(ArrayList<Philosoph> philosphenList) {
		this.philosphenList = philosphenList;
	}
}
