package verteilte.edu.hm.huber.schulz.controller;

import java.util.ArrayList;

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
	private final int hungryPeople;
	
	public Controller(final int seat, final int philosoph, final int hungryPeople){
		this.seat = seat;
		this.philosoph = philosoph;
		this.hungryPeople = hungryPeople;
		
		masterList = new ArrayList<MasterOfSeat>();
		philosphenList = new ArrayList<Philosoph>();
		seatList = new ArrayList<Seat>();
		
		for(int i = 0 ; i < seat; i++){
			Seat newSeat = new Seat(i+1);
			MasterOfSeat master = new MasterOfSeat(newSeat);
			masterList.add(master);
			seatList.add(newSeat);
		}
		
		for(int i = 0; i < philosoph;i++){
			Philosoph phil = new Philosoph(false, i+1);
			philosphenList.add(phil);
		}
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
