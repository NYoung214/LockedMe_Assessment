package lockedme;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
		if(files.list().length == 0) {
			System.out.println("Directory is empty.");
		}else {
			for(File file : files.listFiles()) {
				fileList.add(file);
			}
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
				addFile();
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
		switch(search(query)) {
		case 1:
			System.out.println("The File "+query+" has been found.");
			break;
		case 0:
			System.out.println("Directory is empty.");
			break;
		case -1:
			System.out.println("The File "+query+" was NOT found.");
			break;
		}
	}
	
	private static int search(String q) {
		if(fileList.isEmpty()) {
			return 0;
		}else {
			if(fileList.contains(new File(MainMenu.FOLDER + q))) {
				return 1;
			}
			return -1;
		}
		
	}
	
	private static void addFile() {
		//ask for directory to file
		//Use [ C:\Users\NecroNyancer\Documents\blank.txt ] for testing
		System.out.println("Insert file location:");
		String filePath = MainMenu.input.nextLine();
		try {
			Path fromPath = Paths.get(filePath);
			
			//check if location is valid
			if(fileCheck(fromPath) == -1) {
				//if not return to menu
				System.out.println(fromPath.getFileName()+" is not a valid file");
				return;				
			}
			//if yes then search to see if file already exist in directory	
			System.out.println(fromPath.getFileName()+" is valid");
			System.out.println("Searching for "+fromPath.getFileName()+" in directory");
			if(search(fromPath.getFileName().toString()) == 1){
				//if file already exists in directory return
				System.out.println("File already exists in directory");
				return;
			}else {
				//file does not exist in directory, add it
				try {
					String toPath = MainMenu.FOLDER + fromPath.getFileName();
					//copy file
					Files.copy(fromPath, Paths.get(toPath));
					//insert name into array list
					fileList.add(new File(toPath));
					System.out.println(fromPath.getFileName() + " has been successfully added");
				}catch(Exception IOException) {
					System.out.println(fromPath.getFileName()+" could not be added.");
					return;
				}
			}

					
			
			
		}catch(Exception InvalidPathException) {
			System.out.println(filePath+" is not a valid path");
			return;
		}
	}
	
	private static int fileCheck(Path path) {
		//check if location is valid and a file
		if(!Files.exists(path) || Files.isDirectory(path)) {
			//if not return to menu
			return -1;
		}
		return 1;
	}
	
	
	
}
