import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Program {
	static ArrayList<Person> myList=new ArrayList<Person>();
	static Community mainCommunity=new Community();	
	
public static void main(String[] args) {
		
		File community=new File(args[0]);
		File queries=new File(args[1]);
		
		readFirstFile(community);
		sortPerson(myList , mainCommunity , 0 , myList.size()-1);
	
	//Answer Queries
		//File queries=new File("/Users/gaoyijia/Documents/2017Data structure/workplaceforCS102/HW5/src/queriesfile.txt");
		try{
			Scanner sc2=new Scanner(new FileInputStream(queries));
			PrintWriter writer = new PrintWriter("outputQueries.txt");
			
			while(sc2.hasNext()){
				
				String readQueries=sc2.nextLine();
				String[] splited = readQueries.split("\\s+");
				
				if(splited[0].equals("NAME-OF")){
					String askSSN=splited[1];
					int getSSN=Integer.parseInt(askSSN);
					String NameSSN=mainCommunity.search(getSSN).pi.getName();
					writer.println(splited[0]+" "+splited[1]+":"+NameSSN);
					System.out.println(splited[0]+" "+splited[1]+":"+NameSSN);
				}
				
				if(splited[0].equals("FATHER-OF")){
					String askSSN=splited[1];
					int getSSN=Integer.parseInt(askSSN);
					int fatherSSN=mainCommunity.search(getSSN).pi.getFather();
					
					if(fatherSSN>11){
						System.out.println("No Such Person");
					}
					else{
						String fatherName=mainCommunity.search(fatherSSN).pi.getName();
						writer.println(splited[0]+" "+splited[1]+":"+fatherName);
						System.out.println(splited[0]+" "+splited[1]+":"+fatherName);
					}
				}
				
				if(splited[0].equals("MOTHER-OF")){
					String askSSN=splited[1];
					int getSSN=Integer.parseInt(askSSN);
					int motherSSN=mainCommunity.search(getSSN).pi.getMother();
					
					if(motherSSN>11){
						System.out.println("No Such Person");
					}
					else{
						String motherName=mainCommunity.search(motherSSN).pi.getName();
						writer.println(splited[0]+" "+splited[1]+":"+motherName);
						System.out.println(splited[0]+" "+splited[1]+":"+motherName);
					}
				}
				
				if(splited[0].equals("HALF-SIBLINGS-OF")){
					ArrayList<String> names=new ArrayList<String>();
					String askSSN=splited[1];
					int getSSN=Integer.parseInt(askSSN);
					int motherSSN=mainCommunity.search(getSSN).pi.getMother();
					int fatherSSN=mainCommunity.search(getSSN).pi.getFather();
					
					if(motherSSN>11||fatherSSN>11){
						System.out.println("No Such Person");
					}
					else{
						names=mainCommunity.findHalfSibling(names,motherSSN,fatherSSN);
						if(names.isEmpty()){
							writer.println(splited[0]+" "+splited[1]+":"+"UNAVAILABLE");
							System.out.println(splited[0]+" "+splited[1]+":"+"UNAVAILABLE");
						}
						else{
						StringBuffer print=new StringBuffer();
						for(int i=0;i<names.size();i++){
							if(i==names.size()-1){
								print.append(names.get(i));
							}else{
								print.append(names.get(i)+",");
							}
						}
						writer.println(splited[0]+" "+splited[1]+":"+print);
						System.out.println(splited[0]+" "+splited[1]+":"+print);
					}
					}
				}
				
				if(splited[0].equals("FULL-SIBLINGS-OF")){
					ArrayList<String> names=new ArrayList<String>(); 
					String askSSN=splited[1];
					int getSSN=Integer.parseInt(askSSN);
					int motherSSN=mainCommunity.search(getSSN).pi.getMother();
					int fatherSSN=mainCommunity.search(getSSN).pi.getFather();
					
					if(motherSSN>11||fatherSSN>11){
						System.out.println("No Such Person");
					}
					else{
						names=mainCommunity.findFullSibling(names,motherSSN,fatherSSN,getSSN);
						
						if(names.isEmpty()){
							writer.println(splited[0]+" "+splited[1]+":"+"UNAVAILABLE");
							System.out.println(splited[0]+" "+splited[1]+":"+"UNAVAILABLE");
						}
						else{
						StringBuffer print=new StringBuffer();
						for(int i=0;i<names.size();i++){
							if(i==names.size()-1){
								print.append(names.get(i));
							}else{
								print.append(names.get(i)+",");
							}
						}
						writer.println(splited[0]+" "+splited[1]+":"+print);
						System.out.println(splited[0]+" "+splited[1]+":"+print);
					}
					}
				}
					
				if(splited[0].equals("CHILDREN-OF")){
					ArrayList<String> names=new ArrayList<String>(); 
					String askSSN=splited[1];
					int getSSN=Integer.parseInt(askSSN);
					
					if(getSSN>11){
						System.out.println("No Such Person");
					}
					else{
						names=mainCommunity.findParent(names,getSSN);
						if(names.isEmpty()){
							writer.println(splited[0]+" "+splited[1]+":"+"UNAVAILABLE");
							System.out.println(splited[0]+" "+splited[1]+":"+"UNAVAILABLE");
						}
						else{
						StringBuffer print=new StringBuffer();
						for(int i=0;i<names.size();i++){
							if(i==names.size()-1){
								print.append(names.get(i));
							}else{
								print.append(names.get(i)+",");
							}
						}
						writer.println(splited[0]+" "+splited[1]+":"+print);
						System.out.println(splited[0]+" "+splited[1]+":"+print);
						}
					}
				}

				if(splited[0].equals("MUTUAL-FRIENDS-OF")){
					ArrayList<String> names=new ArrayList<String>();
					String askSSN=splited[1];
					int getSSN=Integer.parseInt(askSSN);
					
					if(getSSN>11){
						System.out.println("No Such Person");
					}
					else{
						names=mainCommunity.findMutualFriend(getSSN,names);
						if(names.isEmpty()){
							writer.println(splited[0]+" "+splited[1]+":"+"UNAVAILABLE");
							System.out.println(splited[0]+" "+splited[1]+":"+"UNAVAILABLE");
						}
						else{
						StringBuffer print=new StringBuffer();
						for(int i=0;i<names.size();i++){
							if(i==names.size()-1){
								print.append(names.get(i));
							}
							else{
							print.append(names.get(i)+",");
							}
						}
						writer.println(splited[0]+" "+splited[1]+":"+print);
						System.out.println(splited[0]+" "+splited[1]+":"+print);
					}
					}
				}
				
				if(splited[0].equals("INVERSE-FRIENDS-OF")){
					ArrayList<String> names=new ArrayList<String>();
					String askSSN=splited[1];
					int getSSN=Integer.parseInt(askSSN);
					
					if(getSSN>11){
						System.out.println("No Such Person");
					}
					else{
						names=mainCommunity.inverseFriend(names,getSSN);
						if(names.isEmpty()){
							writer.println(splited[0]+" "+splited[1]+":"+"UNAVAILABLE");
							System.out.println(splited[0]+" "+splited[1]+":"+"UNAVAILABLE");
						}
						else{
						StringBuffer print=new StringBuffer();
						for(int i=0;i<names.size();i++){
							if(i==names.size()-1){
								print.append(names.get(i));
							}
							else{
							print.append(names.get(i)+",");
							}
						}
						writer.println(splited[0]+" "+splited[1]+":"+print);
						System.out.println(splited[0]+" "+splited[1]+":"+print);
					}
					}
				}	
				
				if(readQueries.equals("WHO-HAS-MOST-MUTUAL-FRIENDS")){
					int[] mutualFriend=new int[mainCommunity.size];
					mutualFriend=mainCommunity.mostMutual();
					
					int max=mutualFriend[0];
					int maxSSN=0;
					for (int i = 0; i < mutualFriend.length; i++) {
					    if (mutualFriend[i] > max) {
					        max = mutualFriend[i];
					        maxSSN=i+1;
					    }
					}
					String names=mainCommunity.search(maxSSN).pi.getName();
					writer.println(readQueries+":"+names);
					System.out.println(readQueries+":"+names);
					}
				}
	
				writer.close();
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Error");
		}
	}
		
	
	public static void sortPerson(ArrayList<Person>sort, Community tree, int start, int end) {
		if(end-start==0){
			Node newPerson=new Node(sort.get(start));
			tree.insert(newPerson);		
		}
		else if(end-start>0){
			int mid=(start+end)/2;
			Node newPerson=new Node(sort.get(mid));
			tree.insert(newPerson);
			
			sortPerson(sort,tree,start,mid-1);
			sortPerson(sort,tree,mid+1,end);
		}
	}


	/*
	public static void loadCommunity() {
		for(Person p:myList){
			Node newPerson=new Node(p);
			mainCommunity.insert(newPerson);
			System.out.print("");
		}
		mainCommunity.preOrderTraversal();
		System.out.println();
		
	}
	 */

	public static ArrayList<Person> readFirstFile(File community){
		
		//File community=new File("/Users/gaoyijia/Documents/2017Data structure/workplaceforCS102/HW5/src/communityfile.txt");
		try{
			Scanner sc=new Scanner(new FileInputStream(community));
			while(sc.hasNext()){
				String check=sc.nextLine();
				if(!check.trim().isEmpty()){
					String[] info=new String[6];
					int delimiter2=check.indexOf(":");
					info[0]=check.substring(delimiter2+2);
					
					for(int i=1;i<info.length;i++){
						String temp=sc.nextLine();
						int delimiter=temp.indexOf(":");
						info[i]=temp.substring(delimiter+2);
					}
					Person p=new Person(info[0],info[1],info[2],info[3],info[4],info[5]);
					myList.add(p);	
				}
			}
					
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("read community file problem");
		}
		return myList;
	}
	
	
	public static void print(ArrayList<Person> mylist){
		for(Person p:mylist){
			p.print();
		}
	}
	
}
