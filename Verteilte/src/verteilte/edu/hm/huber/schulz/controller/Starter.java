package verteilte.edu.hm.huber.schulz.controller;


public class Starter {
	
	public static void main(String args[]) throws InterruptedException{
		
		/*
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Bitte geben sie die gewünschte Anzahl an Philosophe ein: ");
		int phil = scanner.nextInt();
		
		System.out.println("Bitte geben sie die gewünschte Anzahl an Sitzen ein: ");
		int seat = scanner.nextInt();
		
		System.out.println("Bitte geben sie die gewünschte Anzahl an hungrigen Philosophe ein: ");
		int hungry = scanner.nextInt();
		
		if(hungry > phil){
			hungry = phil;
		}
		*/
		
		Controller controller = new Controller(5, 5, 3);
		Thread.sleep(60000);
		System.exit(0);
	}

}
