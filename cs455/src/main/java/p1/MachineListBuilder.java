package p1;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
public class MachineListBuilder {
	public static void cleanMachineList(){
		String current = null;
		try {
			current = new java.io.File( "." ).getCanonicalPath();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			parseHelper(current+"/src/main/java/p1/machine_list");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String[] parse() {
		String current = null;
		try {
			current = new java.io.File( "." ).getCanonicalPath();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String[] outputString = null;
		try {
			outputString = parseHelper(current+"/src/main/java/p1/machine_list");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return outputString;
	}
	
	public static String[] parseHelper(String fileName) throws FileNotFoundException{
		//System.out.println("Parsing: "+fileName);
		Scanner myReader = new Scanner(new File(fileName));
		String buildString = "";
		int outputArraySize = 0;
		while(myReader.hasNextLine() == true) {
			String[] machineInfo = myReader.nextLine().split("\t", 2);
			buildString+=machineInfo[0]+"\n";
			outputArraySize++;
		}
		buildString = buildString.substring(0, buildString.length()-1);
		myReader.close();
		PrintWriter myWriter = new PrintWriter(new File(fileName));
		myWriter.write(buildString);
		myWriter.close();
		return buildString.split("\n",outputArraySize);
	}
}
