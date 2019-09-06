import java.io.*;
import java.util.*;

/*
 * Please enter your option as "Sort" or "Get Similar";
 * Enter the year as "10 12" etc.
 * Enter sorting method as "merge sort", "bubble sort", "plain quick sort", "improved quick sort"
 * Enter the state name as "New York"
 */

public class Program {
	static ArrayList<State> stateList = new ArrayList<State>();
	public static void main(String[] args) {

		try {
			File courseFile = new File("src/ds.csv");
			FileReader fileReader = new FileReader(courseFile);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
		
			// use bufferedreader to read the file line by line
			String line;
			bufferedReader.readLine();
			while ((line = bufferedReader.readLine()) != null) {
				try {
					// separate each line into segments with commas 
					StringTokenizer nextLine = new StringTokenizer(line, ",");
					// input each segment
					String stateName = nextLine.nextToken();
					int census = Integer.parseInt(nextLine.nextToken());
					int basis = Integer.parseInt(nextLine.nextToken());
					int popu10 = Integer.parseInt(nextLine.nextToken());
					int popu11 = Integer.parseInt(nextLine.nextToken());
					int popu12 = Integer.parseInt(nextLine.nextToken());
					int popu13 = Integer.parseInt(nextLine.nextToken());
					int popu14 = Integer.parseInt(nextLine.nextToken());
					int popu15 = Integer.parseInt(nextLine.nextToken());
					int popu16 = Integer.parseInt(nextLine.nextToken());
					State newState = new State(stateName, census, basis, popu10, popu11, popu12, popu13, popu14, popu15, popu16);
					stateList.add(newState);
				}
				
				catch (NumberFormatException e) {}
				catch (NoSuchElementException e) {}
			}		
		bufferedReader.close();
		}
		
		catch (IOException ex) {
			System.out.println("Something is wrong");
		}
		
		System.out.println("Please enter your option");
		Scanner sc4=new Scanner(System.in);
		String option=sc4.nextLine();
		if(option.equals("Sort")){
			sort();
		}
		if(option.equals("Get Similar")){
			similar();
		}
	}
	
	public static void similar() {
		/*
		 * I understand "for a given year" as the increase percentage compared to the year before it
		 */	
		Scanner sc3=new Scanner(System.in);
		System.out.println("Please enter the state you would like to see");
		String instate="."+sc3.nextLine();
		State target=null;
		
		System.out.println("Please enter the year");
		int year=sc3.nextInt();
		for(State aState:stateList){
			aState.setincrPer(year-1, year);
		}
		Sorter sorter=new Sorter();
		sorter.improvedQuickSort(stateList, 0, stateList.size()-1);
		
		for(State find:stateList){
			if((find.stateName).equals(instate)){
				target=find;
				break;
			}
		}

		int index=stateList.indexOf(target);
		
		double input=stateList.get(index).getincrPer();
		double previous=stateList.get(index-1).getincrPer();
		double next=stateList.get(index+1).getincrPer();

		if((previous-input)>(input-next)){
			System.out.println("The most similar state in "+year+" is "+stateList.get(index+1).stateName);
		}
		else{
			System.out.println("The most similar state in "+year+ " is "+stateList.get(index-1).stateName);
		}
		
	}

	public static void sort() {
		Scanner sc=new Scanner(System.in);
		System.out.println("Please enter the start year and end year");
		int startYear=sc.nextInt();
		int endYear=sc.nextInt();
		for(State aState:stateList){
			aState.setincrPer(startYear, endYear);
		}
		
		
		Sorter sorter = new Sorter();
		Scanner sc2=new Scanner(System.in);
		System.out.println("Please enter the sorting method");
		String discard = sc2.nextLine();
		if(discard.equals("merge sort")){
			long startTime=System.nanoTime();
			stateList=sorter.mergeSort(stateList);
			long endTime=System.nanoTime();
			long totalTime=endTime-startTime;
			System.out.println(totalTime);
		}
		if(discard.equals("bubble sort")){
			long startTime=System.nanoTime();
			sorter.BubbleSort(stateList);
			long endTime=System.nanoTime();
			long totalTime=endTime-startTime;
			System.out.println(totalTime);
		}
		if(discard.equals("plain quick sort")){
			long startTime=System.nanoTime();
			sorter.quickSort(stateList, 0, stateList.size()-1);
			long endTime=System.nanoTime();
			long totalTime=endTime-startTime;
			System.out.println(totalTime);
		}
		if(discard.equals("improved quick sort")){
			long startTime=System.nanoTime();
			sorter.improvedQuickSort(stateList, 0, stateList.size()-1);
			long endTime=System.nanoTime();
			long totalTime=endTime-startTime;
			System.out.println(totalTime);
		}
		
		
		/*
		for (State aState: stateList) {
			aState.print();
		}
		*/
		
	}
}

