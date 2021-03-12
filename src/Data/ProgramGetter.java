package Data;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ProgramGetter {
	
	public static String[] getDataFromFile() {
		File programsFile = new File("/Users/leontiipolovinko/eclipse-workspace/Assignment23/src/Data/ProgramList.txt");
		String programs = "";
		try(FileReader fr = new FileReader(programsFile);
				Scanner scanner = new Scanner(fr)){
			while(scanner.hasNext()) {
				programs += scanner.next();
			}
		} catch (IOException e){
			System.out.println(e.getStackTrace());
		}
		
		String[] programsArr = programs.split("[\\s,]");
		
		return programsArr;
	}
}
