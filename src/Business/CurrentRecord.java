package Business;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import Data.Displayer;

public class CurrentRecord{
	
	private static String currentRecord;
	private final File studentdsFile = new File("/Users/leontiipolovinko/eclipse-workspace/Assignment23/src/Data/Students.txt");
	
	public String getFirstRecord() {
		String record = "";
		
		try(RandomAccessFile ras = new RandomAccessFile(studentdsFile, "r")) {
			
			if (studentdsFile.length() > 0 ){
				ras.seek(0);
				byte[] recordBytes = new byte[19];
				ras.read(recordBytes);
				String id = "";
				String program = "";
				String course = "";
				String semester = "";
				
				for (int i = 0; i < recordBytes.length; i++) {
					if (i >= 0 && i < 3) {
						id += (char)recordBytes[i];
						System.out.print(id);
					}
					System.out.println();
					if (i >= 3 && i < 8) {
						program  += (char)recordBytes[i];
						System.out.print(program);
					}
					
					if (i >= 8 && i < 9) {
						semester += (char)recordBytes[i];
					}
					
					if (i >= 9 && i < 19) {
						course += (char)recordBytes[i];
					}
					
				}
				
				currentRecord = id + program + semester + course;
			} else return null;
			
		} catch(IOException e) {
			System.out.println(e.getStackTrace());
		}
		return currentRecord;
	}
	
	public String getLastRecord() {
		String record = "";
		
try(RandomAccessFile raf = new RandomAccessFile(studentdsFile, "r")) {
			
	if (studentdsFile.length() > 0 ) {
		
		raf.seek(studentdsFile.length() - 20);
		byte[] recordBytes = new byte[19];
		raf.read(recordBytes);
		String id = "";
		String program = "";
		String course = "";
		String semester = "";
		
		for (int i = 0; i < recordBytes.length; i++) {
			
			if (i >= 0 && i < 3) {
				id += (char)recordBytes[i];
			}
			if (i >= 3 && i < 8) {
				program  += (char)recordBytes[i];
			}
			
			if (i >= 8 && i < 9) {
				semester += (char)recordBytes[i];
			}
			
			if (i >= 9 && i < 19) {
				course += (char)recordBytes[i];
			}
			
		}
		
		currentRecord = id + program + semester + course;
	} else return null;
				
		} catch(IOException e) {
			System.out.println(e.getStackTrace());
		}
		return currentRecord;
	}
	
	public String getNextRecord() {
		if (currentRecord != null) {
			
			try(RandomAccessFile raf = new RandomAccessFile(studentdsFile, "r")) {
				
				String record = null;
				String[] data = Displayer.getDataFromRecord(currentRecord);
				int currId = Integer.parseInt(data[0]);
				
				if (currId * 20 < raf.length()) {
					
					raf.seek(currId * 20);
					byte[] recordBytes = new byte[19];
					raf.read(recordBytes);
					String id = "";
					String program = "";
					String course = "";
					String semester = "";
					
					for (int i = 0; i < recordBytes.length; i++) {
						
						if (i >= 0 && i < 3) {
							id += (char)recordBytes[i];
						}
						if (i >= 3 && i < 8) {
							program  += (char)recordBytes[i];
						}
						
						if (i >= 8 && i < 9) {
							semester += (char)recordBytes[i];
						}
						
						if (i >= 9 && i < 19) {
							course += (char)recordBytes[i];
						}
						
					}
					
					currentRecord = id + program + semester + course;
					return currentRecord;
				} else return null;
				
			} catch(IOException e) {
				System.out.println(e.getStackTrace());
			}
			return null;
		} else return null;
	}
	
	public String getPreviousRecord() {
		if (currentRecord != null) {
			
			
			try(RandomAccessFile raf = new RandomAccessFile(studentdsFile, "r")) {
				
				String record = null;
				String[] data = Displayer.getDataFromRecord(currentRecord);
				int currId = Integer.parseInt(data[0]);
				
				if (currId * 20 - 40 >= 0) {
					
					raf.seek(currId * 20 - 40);
					byte[] recordBytes = new byte[19];
					raf.read(recordBytes);
					String id = "";
					String program = "";
					String course = "";
					String semester = "";
					
					for (int i = 0; i < recordBytes.length; i++) {
						
						if (i >= 0 && i < 3) {
							id += (char)recordBytes[i];
						}
						if (i >= 3 && i < 8) {
							program  += (char)recordBytes[i];
						}
						
						if (i >= 8 && i < 9) {
							semester += (char)recordBytes[i];
						}
						
						if (i >= 9 && i < 19) {
							course += (char)recordBytes[i];
						}
						
					}
					
					currentRecord = id + program + semester + course;
					return currentRecord;
				} else return null;
				
			} catch(IOException e) {
				System.out.println(e.getStackTrace());
			}
			return null;
		} else return null;
	}
	
	public boolean updateRecord(String[] newData) {
		
		if (currentRecord != null) {
			
			try(RandomAccessFile raf = new RandomAccessFile(studentdsFile, "rw")) {
				
				String record = null;
				String[] data = Displayer.getDataFromRecord(currentRecord);
				int currId = Integer.parseInt(data[0]);
				
				raf.seek(currId * 20 - 20);
				byte[] recordBytes = new byte[19];
				
				String id = data[0];
				String program = newData[0];
				String semester = newData[1];
				String course = newData[2];
				
				while(id.length() < 3) {
					id += " ";
				}
				
				while(program.length() < 5) {
					program += " ";
				}
				
				while(course.length() < 10) {
					course += " ";
				}
				
				currentRecord = id + program + semester + course + "\n";
				
				raf.write(currentRecord.getBytes());
				return true;
			} catch(IOException e) {
				System.out.println(e.getStackTrace());
				return false;
			}
			
		} else return false; 
	}
	
	public void writeToFile(ArrayList<String> recordsList) {
		
		try(FileOutputStream fos = new FileOutputStream(studentdsFile);
				PrintStream ps = new PrintStream(fos)) {
			
			for (int i = 0; i < recordsList.size(); i++) {
				ps.println(recordsList.get(i));
			}
		} catch(IOException e) {
			System.out.println(e.getStackTrace());
		}
	}
}
