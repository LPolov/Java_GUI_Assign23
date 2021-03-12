package Data;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import Business.StudentRecord;

public class Saver {
	
	public static final File studentdsFile = new File("/Users/leontiipolovinko/eclipse-workspace/Assignment23/src/Data/Students.txt");
	
	public static void saveData(StudentRecord sr) {		
		try(RandomAccessFile raf = new RandomAccessFile(studentdsFile, "rw")) {
			
			raf.seek(studentdsFile.length());
			
			String id = sr.getID() + "";
			String program = sr.getProgram() + "";
			String semester = sr.getSemester() + "";
			String course = sr.getCourse() + "";
			
			while(id.length() < 3) {
				id += " ";
			}
			
			while(program.length() < 5) {
				program += " ";
			}
			
			while(course.length() < 10) {
				course += " ";
			}
			
			String newRecord = id + program + semester + course + "\n";
			
			raf.write(newRecord.getBytes());
			
		} catch(IOException e) {
			System.out.println(e.getStackTrace());
		}
	}
}
