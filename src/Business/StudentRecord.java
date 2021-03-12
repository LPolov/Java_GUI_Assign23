package Business;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class StudentRecord {
	private String program;
	private String semester;
	private String course;
	protected static int ID;
	
	public StudentRecord(String program, String semester, String course) {
		this.course = course;
		this.program = program;
		this.semester = semester;
		setID();
	}
	
	public String getProgram() {
		return program;
	}
	
	public String getSemester() {
		return semester;
	}
	
	public String getCourse() {
		return course;
	}
	
	public int getID() {
		return ID;
	}
	
	public void setProgram(String program) {
		this.program = program;
	}
	
	public void setSemester(String semester) {
		this.semester = semester;
	}
	
	public void setCourse(String course) {
		this.course = course;
	}
	
	public void setID() {
		final File studentdsFile = new File("/Users/leontiipolovinko/eclipse-workspace/Assignment23/src/Data/Students.txt");
		
		try(FileInputStream fis = new FileInputStream(studentdsFile);
				Scanner scanner = new Scanner(fis)) {
			ID = 1;
			while(scanner.hasNextLine()) {
				ID++;
				scanner.nextLine();
			}
			
		} catch(IOException e) {
			System.out.println(e.getStackTrace());
		}
	}

}
