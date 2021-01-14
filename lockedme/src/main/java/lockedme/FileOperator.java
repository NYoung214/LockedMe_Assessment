package lockedme;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileOperator {
	
	private static List<File> fileList = new ArrayList<>();
	private static List<String> nameList = new ArrayList<>();
	
	public static void showFiles() {
		Collections.sort(fileList);
		Collections.sort(nameList);
		printName();
	}
	
	public static void fillFiles() {
		File files = new File(MainMenu.FOLDER);
		if(files.list().length == 0) {
			System.out.println("Directory is empty.");
		}else {
			for(File file : files.listFiles()) {
				fileList.add(file);
				nameList.add(file.getName());
			}
		}

	}
	
	private static void printName() {
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
				searchFile();
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
	
	private static void searchFile() {
		System.out.println("Enter file name.");
		String query = MainMenu.input.nextLine();
		search(query);
	}
	
	private static void search(String q) {
		if(fileList.isEmpty()) {
			System.out.println("Directory is empty.");
		}else {
			if(nameList.contains(q)) {
				System.out.println("The File "+q+" has been found.");
			}else {
				System.out.println("The File "+q+" was NOT found.");
			}
		}
	}
	
	
	
}
