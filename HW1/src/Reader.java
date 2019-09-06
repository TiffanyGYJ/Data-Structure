import java.io.*;

public class Reader {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String csvFile= "/Users/gaoyijia/Documents/2017Data structure/workplaceforCS102/HW1/MyUniversityCourses.csv";	
		String line=null;
		try{
			FileReader fileReader = new FileReader(csvFile);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
			}
			bufferedReader.close();
		}
		catch(FileNotFoundException ex){
			System.out.println( "Unable to open file '" + csvFile + "'");
			ex.printStackTrace();
	}
		catch (IOException ex) {
			System.out.println( "Error reading file '" + csvFile + "'");
			ex.printStackTrace();
		}
	}
}
