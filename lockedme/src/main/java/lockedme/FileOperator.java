package lockedme;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileOperator {
	//List to hold all files
	private static List<File> fileList = new ArrayList<>();
	
	//method to sort files in the list
	public static void showFiles() {
		//sort list before printing
		if(!fileList.isEmpty()) {
			Collections.sort(fileList);
			printFiles();
		}else {
			System.out.println("EMPTY DIRECTORY");
		}

	}
	
	//method to copy file names to array
	public static void fillFiles() {
		File files = new File(MainMenu.FOLDER);
		//fill the list with the names of the files in directory
		for(File file : files.listFiles()) {
			fileList.add(file);
		}
	}
	
	//method to print files in list
	private static void printFiles() {
		for(File file : fileList) {
			System.out.println(file.getName());
		}	
	}
	
	//Method for main menu of File Operations
	public static void menu() {
		String choice;
		//Loop to process menu continuously
		while(true) {
			System.out.println("------- File Operations --------"
					+"\n1. Add a file"
					+"\n2. Delete a file"
					+"\n3. Search for a file"
					+"\n4. Back to Main Menu"
					+"\n--------------------------");
			choice = MainMenu.input.nextLine();
			switch(choice) {
			//Case 1 to add a file
			case "1":
				addFile();
				break;
			//Case 2 to delete a file
			case "2":
				deleteFile();
				break;
			//Case 3 to search for a file
			case "3":
				searchFile();
				break;
			//Case 4 to return to main menu
			case "4":
				MainMenu.showMainMenu();
				break;
			//Default case to catch invalid entries
			default:
				System.out.println("Invalid input. Please enter 1, 2, 3, or 4.");
				break;
			}
		}
	}
	
	//Method to search List for file name
	private static void searchFile() {
		if(fileList.isEmpty()) {
			System.out.println("EMPTY DIRECTORY");
			return;
		}
		//get name of file from user
		System.out.println("Enter file name (example: blank.txt)"
				+ "\nWARNING: CASE SENSITIVE!!!");
		String query = MainMenu.input.nextLine();
		//search for query and get a result
		switch(search(query)) {
		//Case 1, query was found
		case 1:
			System.out.println("FILE FOUND:  "+query);
			break;
		//Case 0, list is empty
		case 0:
			System.out.println("EMPTY DIRECTORY");
			break;
		//Case -1, query not found
		case -1:
			System.out.println("FILE NOT FOUND: "+query);
			break;
		}
	}
	
	//Method to return an int based on a file search
	private static int search(String q) {
		//Check if list is empty first
		if(fileList.isEmpty()) {
			return 0;
		}else {
			File file = new File(MainMenu.FOLDER + q);
			//if file list contains query then return positive
			if(fileList.contains(file)) {
				int index = fileList.indexOf(file);
				//check for case sensitivity
				if(q.equals(fileList.get(index).getName().toString())) {
					return 1;
				}
				//case did not match
				return -1;
			}
			//Otherwise return negative
			return -1;
		}
		
	}
	
	//Method to add a file to directory and list
	private static void addFile() {
		//ask for directory to file
		System.out.println("Insert file location (example: C:\\Users\\Name\\file.txt:)");
		String filePath = MainMenu.input.nextLine();
		try {
			//try to convert String into path object
				//if this fails catch error 
			Path fromPath = Paths.get(filePath);
			
			//check if location is valid
			if(fileCheck(fromPath) == -1) {
				//if not return to menu
				System.out.println("INVALID FILE: "+fromPath.getFileName());
				return;				
			}
			//if yes then search to see if file already exist in directory	
			System.out.println("SEARCHING FOR: "+fromPath.getFileName());
			if(search(fromPath.getFileName().toString()) == 1){
				//if file already exists in directory return
				System.out.println("FILE ALREADY EXISTS IN DIRECTORY");
				return;
			}else {
				//file does not exist in directory, add it
				try {
					String toPath = MainMenu.FOLDER + fromPath.getFileName();
					//copy file
					Files.copy(fromPath, Paths.get(toPath));
					//insert name into array list
					fileList.add(new File(toPath));
					System.out.println("FILE ADDED: "+fromPath.getFileName());
				}catch(Exception IOException) {
					//could not copy file to location
					System.out.println("FILE NOT ADDED: "+fromPath.getFileName());
					return;
				}
			}
		}catch(Exception InvalidPathException) {
			//String could not be converted to Path object
			System.out.println("INVALID PATH: "+filePath);
			return;
		}
	}
	
	//Method to check if file exists or is Directory
	private static int fileCheck(Path path) {
		//check if location is valid and a file
		if(!Files.exists(path) || Files.isDirectory(path)) {
			//if not return to menu
			return -1;
		}
		return 1;
	}
	
	//Method to delete a file from directory and list
	private static void deleteFile() {
		if(fileList.isEmpty()) {
			System.out.println("EMPTY DIRECTORY");
			return;
		}
		//ask for name of file
		System.out.println("Enter the file name (example: blank.txt):"
				+ "\nWARNING: CASE SENSITIVE!!!");
		String fileName = MainMenu.input.nextLine();
		//search directory for fileName
		if(search(fileName) == 1) {
			try {
				//delete the file if it exists and is not a directory
				Files.deleteIfExists(Paths.get(MainMenu.FOLDER + fileName));
				//remove file from list
				fileList.remove(new File(MainMenu.FOLDER + fileName));
				System.out.println("DELETED: "+fileName);
			}catch(Exception e){
				System.out.println("COULD NOT DELETE: "+fileName);
				return;
			}
		}else {
			System.out.println("COULD NOT FIND: "+fileName);
		}
	}
}
