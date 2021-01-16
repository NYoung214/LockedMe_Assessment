package lockedme;

import java.util.Scanner;

public class MainMenu {
	
	public static Scanner input = new Scanner(System.in);
	public static final String FOLDER = "src/main/tmp/";
	
	//Method to fill list and begin menu loop
	public static void startApp() {
		//fill list with names of files in directory
		FileOperator.fillFiles();
		showMainMenu();
	}
	
	public static void showMainMenu() {
		String choice;
		System.out.println("--------------------------"
				+ "\nApplication: LockedMe.com"
				+ "\nDeveloper: Nathaniel Young"
				+ "\n--------------------------");
		//start menu loop
		while(true) {
			System.out.println("------- Main Menu --------"
					+"\n1. Show Files"
					+"\n2. File Operations"
					+"\n3. Close Application"
					+"\n--------------------------");
			choice = input.nextLine();
			//check choice for input
			switch(choice) {
			case "1":
				//show the files in the list
				FileOperator.showFiles();
				break;
			case "2":
				//start loop for File Operations
				FileOperator.menu();
				break;
			case "3":
				//close connections and application
				System.out.println("Closing the application...");
				input.close();
				System.exit(0);
				break;
			default:
				//catch invalid inputs
				System.out.println("Invalid input. Please enter 1, 2, or 3.");
				break;
			}
		}
	}
}
