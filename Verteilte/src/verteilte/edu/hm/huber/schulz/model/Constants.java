package verteilte.edu.hm.huber.schulz.model;

import verteilte.edu.hm.huber.schulz.controller.Controller;

public class Constants {

	public static final long EAT_LENGTH = 1;
	
	public static final long SLEEP_LENGTH = 10;
	
	public static final long MEDITATE_LENGTH = 5;
	
	public static final int DIFFERENZ = 20;
	
	public static final int TRIES_TO_GET_FORK = 5;

	public static final long TIME_TO_GET_RIGHT_FORK = EAT_LENGTH/10;

	public static final long TIME_UNTIL_NEW_FORKTRY = EAT_LENGTH/2;

	public static final int EAT_MAX_HUNGRY = 10;

	public static final int EAT_MAX_NORMAL = 3;

	public static final long BAN_FACTOR = 15;
	
}
