package verteilte.edu.hm.huber.schulz.model;

import java.util.ArrayList;

public class TableMaster extends Thread{
	
	private final ArrayList<Philosoph> philList;
	private int maxCount;
	private int minCount;
	
	public TableMaster(final ArrayList<Philosoph> philList){
		this.philList = philList;
		maxCount = 0;
		minCount = 0;
	}
	
	public void run(){
		while(true){
			for(int i = 0; i < philList.size(); i++){
				setMaxCounter(philList.get(i).getCounter());
				setMinCounter(philList.get(i).getCounter());
			}
		}
	}
	
	public void setMaxCounter(final int counter){
		if(getMaxCounter()>= counter){
			maxCount = counter;
		}
	}
	
	public int getMaxCounter(){
		return maxCount;
	}
	
	public void setMinCounter(final int counter){
		minCount = counter;
	}
	
	public int getMinCounter(){
		return minCount;
	}

}
