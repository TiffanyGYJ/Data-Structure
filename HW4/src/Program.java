import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class Program {
	static int serviceTime;

	public static void main(String[] args) {
		//take two file input
		File customer=new File(args[0]);
		File queries=new File(args[1]);
		
		//load customers into an ArrayList 
		ArrayList<Node> array = readFirstFile(customer);
		
		//change time format
		int storeOpen = (int) (Math.pow(60, 2) * 9);
		int storeClose = (int) (Math.pow(60, 2) * 17);
		ArrayList<Integer> startServiceTime = new ArrayList<>();
		Node currentNode = array.get(0);
		int time = currentNode.getTime();
		int maxQueueSize = 0;
		
		//construct a queue
		QueueLinkedList queue = new QueueLinkedList();
		
		int largeNumber = 1000000000;
		int nextEnQueue;
		int nextDeQueue;
		boolean hasOpened = false;
		while(time < storeClose){
			// Find time for next dequeue
			if(!queue.isEmpty()){
				if( startServiceTime.size() != 0 ){
					nextDeQueue = startServiceTime.get(startServiceTime.size() - 1) + serviceTime;
				}else{
					nextDeQueue = largeNumber;
				}
			}else{
				nextDeQueue = largeNumber;
			}
			
			// Find time for next enqueue
			if(currentNode == null){
				nextEnQueue = largeNumber;
			}else{
				nextEnQueue = currentNode.getTime();
			}
			
			if(nextEnQueue < nextDeQueue & (hasOpened | nextEnQueue < storeOpen) & nextEnQueue < storeClose){
				//EnQueue
				time = nextEnQueue;
				if(queue.getSize()==0 & time > storeOpen){
					startServiceTime.add(time);
					//System.out.println(time);
				}
				queue.enqueue(currentNode);
				maxQueueSize = Math.max(maxQueueSize, queue.getSize());
				if(currentNode.getID()==array.size()){
					currentNode = null;
				}else{
					currentNode = array.get(currentNode.getID());
				}
			} else if(nextDeQueue < nextEnQueue & (hasOpened | nextDeQueue < storeOpen) & nextDeQueue < storeClose){
				//Dequeue
				time = nextDeQueue;
				queue.dequeue();
				if(queue.getSize()!=0){
					startServiceTime.add(time);
					//System.out.println(time);
				}
			} else if( !hasOpened ){
				//storeOpen
				hasOpened = true;
				time = storeOpen;
				if(queue.getSize()!=0){
					startServiceTime.add(time);
					//System.out.println(time);
				}
			} else{
				//storeClose
				time = storeClose;
			}
		}
		
		// Waiting time
		ArrayList<Integer> waitingTime = new ArrayList<>();
		for(int i = 0; i<startServiceTime.size(); i++){
			waitingTime.add(startServiceTime.get(i) - array.get(i).getTime());
		}
		for(int i = startServiceTime.size(); i < array.size(); i++){
			waitingTime.add(Math.max(storeClose - array.get(i).getTime(), 0));
		}
		
		// How many people get served
		int servedPeople = startServiceTime.size();
		
		// Break.
		ArrayList<Integer> breakTime=new ArrayList<>();
		if(startServiceTime.get(0)>storeOpen){
			breakTime.add(startServiceTime.get(0)-storeOpen);
		}
		for( int i=1; i<startServiceTime.size(); i++ ){
			if( (startServiceTime.get(i)-serviceTime) - startServiceTime.get(i-1) >0){
				breakTime.add(startServiceTime.get(i)-serviceTime-startServiceTime.get(i-1));
			}
		}
		int longestBreak=Collections.max(breakTime);
		
		int totalIdleTime=0;
		for( int i=0; i<breakTime.size(); i++){
			totalIdleTime+=breakTime.get(i);
		}
		
		maxQueueSize -= 1;
		
		
		
		//Answer Queries
		
		//File queries=new File("/Users/gaoyijia/Documents/2017Data structure/workplaceforCS102/HW4/src/queriesfile.txt");
			
		try{
			Scanner sc2=new Scanner(new FileInputStream(queries));
			PrintWriter writer = new PrintWriter("outputQueries.txt");
			
			while(sc2.hasNext()){
				String readQueries=sc2.nextLine();
				
				if(readQueries.equals("NUMBER-OF-CUSTOMERS-SERVED")){
					writer.println(readQueries+": "+startServiceTime.size());
					System.out.println(readQueries+": "+startServiceTime.size());
				}
				if(readQueries.equals("LONGEST-BREAK-LENGTH")){
					writer.println(readQueries+": "+longestBreak);
					System.out.println(readQueries+": "+longestBreak);
				}
				if(readQueries.equals("TOTAL-IDLE-TIME")){
					writer.println(readQueries+": "+totalIdleTime);
					System.out.println(readQueries+": "+totalIdleTime);
				}
				if(readQueries.equals("MAXIMUM-NUMBER-OF-PEOPLE-IN-QUEUE-AT-ANY-TIME")){
					writer.println(readQueries+": "+maxQueueSize);
					System.out.println(readQueries+": "+maxQueueSize);
				}
				if(readQueries.substring(0, readQueries.length()-1).equals("WAITING-TIME-OF ")){
					String askId1=readQueries.substring(readQueries.length()-1);
					int getID1=Integer.parseInt(askId1);
					int waitingTimeID1=waitingTime.get(getID1-1);
					writer.println(readQueries+": "+waitingTimeID1);
					System.out.println(readQueries+": "+waitingTimeID1);
				}
				writer.close();
			}
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("read queries file problem");
		}
	}
	
	public static ArrayList<Node> readFirstFile(File customer){
		ArrayList<Node> myList=new ArrayList<Node>();
		
		//File file=new File("/Users/gaoyijia/Documents/2017Data structure/workplaceforCS102/HW4/src/customersfile.txt");
		try{
			Scanner sc=new Scanner(new FileInputStream(customer));
			serviceTime = sc.nextInt();
			
			sc.nextLine();
			
			int timePrevious = 0;
			int noon = (int) (Math.pow(60, 2) * 12);
			
			while(sc.hasNext()){
					
				String check=sc.nextLine();
				if(check.trim().isEmpty()){
					String temp=sc.nextLine(); 
					int delimiter=temp.indexOf(":");
					String idString=temp.substring(delimiter+3);
					
					//System.out.println(id);
					int id=Integer.parseInt(idString);
				
					String time=sc.nextLine();
					time = time.split(":( )+")[1];
					String[] timeSplit = time.split(":");
					int timeInt = 0;
					for(int i = 2; i>=0; i--){
						timeInt += Math.pow(60, 2-i) * Integer.parseInt(timeSplit[i]);
					}
					if(timeInt < timePrevious){
						timeInt += noon;
					}
					timePrevious = timeInt;
					
					Node node = new Node(id, timeInt);
				
					myList.add(node);
				}
			}
					
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("read customer file problem");
		}
		return myList;
	}
	
}

