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
}
