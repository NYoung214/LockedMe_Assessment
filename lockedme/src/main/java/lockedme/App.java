package lockedme;

public class App {

	public static void main(String[] args) {
		showWelcomeScreen();
		showMainMenu();

	}
	
	private static void showWelcomeScreen() {
		System.out.println("--------------------------");
		System.out.println("Application: LockedMe.com");
		System.out.println("Developer: Nathaniel Young");
		System.out.println("--------------------------");
		
	}
	
	private static void showMainMenu() {
		System.out.println("------- Main Menu --------");
		System.out.println("1. Show Files");
		System.out.println("2. File Operations");
		System.out.println("1. Close Application");
		System.out.println("--------------------------");
	}

}
