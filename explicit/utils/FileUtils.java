package explicit.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

	public static ArrayList<String> getLines(String fileName) {
		ArrayList<String> lines = new ArrayList<String>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			String line = reader.readLine();
			while (line != null) {
				lines.add(line);
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}
	
	public static ArrayList<String> getLines(File f) {
		ArrayList<String> lines = new ArrayList<String>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(f));
			String line = reader.readLine();
			while (line != null) {
				lines.add(line);
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}
	
	public static void createFile(String fileName) {
		File usersFile = new File(fileName);
		if (!usersFile.exists()) {
			try {
				if (!usersFile.createNewFile()) {
					System.out.println("ERROR: Could not create " + fileName + " file");
				}
			} catch (IOException e) {
				System.out.println("ERROR: Could not create " + fileName + " file");
				e.printStackTrace();
			}
		}
	}
	
	public static void createFile(File usersFile) {
		if (!usersFile.exists()) {
			try {
				if (!usersFile.createNewFile()) {
					System.out.println("ERROR: Could not create " + usersFile.getAbsolutePath() + " file");
				}
			} catch (IOException e) {
				System.out.println("ERROR: Could not create " + usersFile.getAbsolutePath() + " file");
				e.printStackTrace();
			}
		}
	}
	
	public static void writeToFile(String fileName, List<String> lines) {
		File file = new File(fileName);
		try {
			if (!file.exists()) {
				file.createNewFile();
			}

			PrintWriter pw = new PrintWriter(file);
			for (String str : lines) {
				pw.println(str);
			}
			pw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void writeToFile(File file, List<String> lines) {
		try {
			if (!file.exists()) {
				file.createNewFile();
			}

			PrintWriter pw = new PrintWriter(file);
			for (String str : lines) {
				pw.println(str);
			}
			pw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
