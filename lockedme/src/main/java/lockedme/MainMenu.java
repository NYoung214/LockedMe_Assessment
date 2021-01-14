package lockedme;

import java.util.Scanner;

public class MainMenu {
	
	public static Scanner input = new Scanner(System.in);
	public static final String FOLDER = "src/main/tmp/";
	
	public static void showMainMenu() {
		
		String choice;
		
		System.out.println("--------------------------"
				+ "\nApplication: LockedMe.com"
				+ "\nDeveloper: Nathaniel Young"
				+ "\n--------------------------");
		
		FileOperator.fillFiles();
		
		while(true) {
			System.out.println("------- Main Menu --------"
					+"\n1. Show Files"
					+"\n2. File Operations"
					+"\n3. Close Application"
					+"\n--------------------------");
			
			choice = input.nextLine();
			
			switch(choice) {
			case "1":
				FileOperator.showFiles();
				break;
			case "2":
				FileOperator.menu();
				break;
			case "3":
				System.out.println("Closing the application...");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid input. Please enter 1, 2, or 3.");
				break;
			}
		}

		
		
		
	}
	
	
}
