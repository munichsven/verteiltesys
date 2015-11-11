package verteilte.edu.hm.huber.schulz.model;

import java.util.ArrayList;
import java.util.Arrays;

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
				crntCounts[i] = crntPhil.getEatCounter();
				i++;
			}
			Arrays.sort(crntCounts);
			minCount = crntCounts[0];
			for (Philosoph crntPhil : philList) {
				if (crntPhil.getEatCounter() > minCount + Constants.DIFFERENZ && !crntPhil.isBanned()) {
					crntPhil.interrupt();
					System.out.println("Phil " + crntPhil.getPhilosophsId() + " interrupted mit " + crntPhil.getEatCounter() + " / " + minCount);
				}
			}
			try {
				Thread.sleep(Constants.EAT_LENGTH);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
