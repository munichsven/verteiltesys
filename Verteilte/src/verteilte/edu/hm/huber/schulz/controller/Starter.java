package verteilte.edu.hm.huber.schulz.controller;


public class Starter {
	
	public static void main(String args[]){
		
		/*
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Bitte geben sie die gewŁnschte Anzahl an Philosophe ein: ");
		int phil = scanner.nextInt();
		
		System.out.println("Bitte geben sie die gewŁnschte Anzahl an Sitzen ein: ");
		int seat = scanner.nextInt();
		
		System.out.println("Bitte geben sie die gewŁnschte Anzahl an hungrigen Philosophe ein: ");
		int hungry = scanner.nextInt();
		
		if(hungry > phil){
			hungry = phil;
		}
		*/
		
		Controller controller = new Controller(3, 5, 2);
	}

}
