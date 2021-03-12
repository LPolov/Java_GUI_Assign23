package Data;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Displayer {
	
	public static String[] getCourses(String courses) {
		String[] coursesArr = new String[5];
		
		for (int i = 0; i < courses.length() - 1; ) {
			coursesArr[i / 2] = courses.substring(i, i + 2);
			i += 2;
		}
		return coursesArr;
	}
	
	public static String formatRecord(String record) {
		String formattedRecord = "";
		String[] data = getDataFromRecord(record);
		
		formattedRecord += "ID: " + data[0] + "\n";
		formattedRecord += "Program: " + data[1] + "\n";
		formattedRecord += "Semester: " + data[2] + "\n";
		formattedRecord += "Courses: ";
		String[] courses = getCourses(data[3]);
		
		for (int i = 0; i < courses.length - 1; i++) {
			if (courses[i] != null) {
				formattedRecord += courses[i] + ", ";
			}
		}
		if (courses[courses.length - 1] != null) {
			formattedRecord += courses[courses.length - 1];
		} else {
			formattedRecord = formattedRecord.substring(0, formattedRecord.length() - 2);
		}
		formattedRecord += "\n\n";
		return formattedRecord;
	}
	
	public static String[] getDataFromRecord(String record) {
		String[] data = new String[4];
		data[0] = record.substring(0, 3).trim();
		data[1] = record.substring(3, 8).trim();
		data[2] = record.substring(8, 9);
		data[3] = record.substring(9, 19).trim();
		
		return data;
	}
	
	public static ArrayList<String> getRecords(String program) {
		
		final File studentdsFile = new File("/Users/leontiipolovinko/eclipse-workspace/Assignment23/src/Data/Students.txt");
		String record = "";
		ArrayList<String> records = new ArrayList<>();
		
		try(FileInputStream fis = new FileInputStream(studentdsFile);
				Scanner scanner = new Scanner(fis)) {
			
			while(scanner.hasNextLine()) {
				record = scanner.nextLine();
				
				if (record.contains(program)) {
					records.add(record);
				}
			}
			
		} catch(IOException e) {
			System.out.println(e.getStackTrace());
		}
		
		return records;
	}
}
