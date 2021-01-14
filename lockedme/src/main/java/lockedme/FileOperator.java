package lockedme;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileOperator {
	
	private static List<File> fileList = new ArrayList<>();
	
	public static void showFiles() {
		Collections.sort(fileList);
		printFiles();
	}
	
	public static void fillFiles() {
		File files = new File(MainMenu.FOLDER);
		for(File file : files.listFiles()) {
			fileList.add(file);
		}
	}
	
	private static void printFiles() {
		for(File file : fileList) {
			System.out.println(file.getName());
		}	
	}
	
	public static void menu() {
		
		String choice;
		
		while(true) {
			System.out.println("------- File Operations --------"
					+"\n1. Add a file"
					+"\n2. Delete a file"
					+"\n3. Search for a file"
					+"\n4. Back to Main Menu"
					+"\n--------------------------");
			
			choice = MainMenu.input.nextLine();
			
			switch(choice) {
			case "1":
				//addFile();
				break;
			case "2":
				//deleteFile();
				break;
			case "3":
				//searchFile()
				break;
			case "4":
				MainMenu.showMainMenu();
				break;
			default:
				System.out.println("Invalid input. Please enter 1, 2, 3, or 4.");
				break;
			}
		}
	}
}
