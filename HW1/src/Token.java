import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Token {
	public void Tokenization() throws IOException{
		// TODO Auto-generated method stub
		ArrayList<Course>List=new ArrayList<Course>();
		String csvFile= "/Users/gaoyijia/Documents/2017Data structure/workplaceforCS102/HW1/MyUniversityCourses.csv";	
		
		Scanner sc=new Scanner(new File(csvFile));
		sc.nextLine();
	while(sc.hasNextLine()){	
		String input = sc.useDelimiter("\n").next();
		StringTokenizer strTokens = new StringTokenizer(input,",");
				
		while (strTokens.hasMoreTokens()){
			String courseName= strTokens.nextToken();
			String courseID= strTokens.nextToken();
			int maxStu=Integer.parseInt(strTokens.nextToken());
			int currentStu=Integer.parseInt(strTokens.nextToken());
		//need to display string instead of references
			String listOfNames=strTokens.nextToken();
			String instructor=strTokens.nextToken();
			int sectionNum=Integer.parseInt(strTokens.nextToken());
			String location=strTokens.nextToken();
			
			//Course c=new Course(courseName,courseID,maxStu,currentStu,listOfNames,
				//				instructor,sectionNum,location);
			//List.add(c);
		}
		
		/*
		for(int i=0;i<courseList.size();i++){
			//prints out the people within the list
		courseList.get(i).print();
		}
		*/	
	}
	}
}
