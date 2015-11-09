package verteilte.edu.hm.huber.schulz.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class TableMaster extends Thread {

	private ArrayList<Philosoph> philList;
	private int minCount;

	public TableMaster(final ArrayList<Philosoph> philList) {
		this.philList = philList;
		minCount = 0;
	}

	public void run() {
		while (true) {
			int[] crntCounts = new int[philList.size()];
			int i = 0;
			for (Philosoph crntPhil : philList) {
				crntCounts[i] = crntPhil.getCounter();
				i++;
			}
			Arrays.sort(crntCounts);
			minCount = crntCounts[0];
			for (Philosoph crntPhil : philList) {
				if (crntPhil.getCounter() > minCount + Constants.DIFFERENZ) {
					//crntPhil.interrupt();
					System.out.println("Phil " + crntPhil.getId() + " interrupted mit " + crntPhil.getCounter() + " / " + minCount);
				}
			}
		}
	}

	public void setMinCounter(final int counter) {
		minCount = counter;
	}

	public int getMinCounter() {
		return minCount;
	}

}
