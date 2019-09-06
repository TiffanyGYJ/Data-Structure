import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.Serializable;
/*
 * List of names: students in course, course registered for students when logged in
 * change list of names as String; 
 */


public class Start implements java.io.Serializable{
	static ArrayList<Course> courseList=new ArrayList<Course>();
	static ArrayList<Student> studentList=new ArrayList<Student>();
	static Scanner scanner=new Scanner(System.in);
	static Scanner sc=new Scanner(System.in).useDelimiter("[,\n]");
	private static final long serialVersionUID = 1L;
	
	
	public static void main(String[] args) throws IOException {	

		
		// TODO Auto-generated method stub	
		File f=new File("courseList.ser");
		
		if(!f.exists()){
		fillCourseList();
		}else{
		DeSerilizationCourseList();
		DeSerilizationStudentList();
		}
		
		System.out.println("Please enter your identity as Admin or Student");
		String identity=scanner.nextLine();
		if((identity).equals("Admin")){
			System.out.println("Please enter your username");
			String username=scanner.nextLine();
			if((username).equals("Admin")){
				System.out.println("Please enter your password");
				String password=scanner.nextLine();
				if((password).equals("Admin001")){
					Admin a=new Admin(username,password,null,null);
					System.out.println("Course Management or Report");
					String option=scanner.nextLine();
					if((option.equals("Course Management"))){
						AdminCourseManagement(a);	
					}else if((option.equals("Report"))){
						AdminReport(a);
					}else{
						System.out.println("No such option. Please re-enter");
					}
				}
			}
		}else if((identity).equals("Student")){
			System.out.println("Are you already registered in the system?");
			String studentAns=scanner.nextLine();
			if((studentAns).equals("No")){
				System.out.println("Please enter your username, password, first name and last name"
						+ ",use commas in between");
				Student newS=new Student(sc.next(),sc.next(),sc.next(),sc.next());
				studentList.add(newS);
				int newStuIndex=studentList.indexOf(newS);
				StudentMenu(newStuIndex);
			}else if((studentAns).equals("Yes")){
				System.out.println("Please enter your username and password, use comma in between");
				String studentUsername=sc.next();String studentPassword=sc.next();
				int sIndex;
				for(Student loginS:studentList){
					if(((loginS.getUsername()).equals(studentUsername))
							&&(loginS.getPassword().equals(studentPassword))){
						sIndex=studentList.indexOf(loginS);
						StudentMenu(sIndex);
					}
				}
			}
			
		}
	}
	
	
	public static void StudentMenu(int sIndex) {
		System.out.println("View all Courses, View all Courses not full, Register Course, "
				+ "Withdraw Course, View all Registered, Exit");
		System.out.println("Please enter your option from the menu");
		String menu=scanner.nextLine();
		Student s=studentList.get(sIndex);
		while(!(menu).equals("Exit")){
			switch(menu){
			case "View all Courses":s.viewAllCourse(courseList);
									break;
			case "View all Courses not full":s.viewNotFull(courseList);
									break;
			case "Register Course":s.register(courseList,studentList);
									break;
			case "Withdraw Course":s.withdraw(courseList,studentList);
									break;
			case "View all Registered":s.viewAllRegistered();
									break;
			}
			System.out.println("Please enter another option from the menu");
			menu=scanner.nextLine();
		}
		if(menu.equals("Exit")){
			SerilizationStudentList();
			SerilizationCourseList();
		}
		System.out.println("You have logged out");
		
	}

	public static void AdminCourseManagement(Admin a) {
		System.out.println("Menu:Create a new Course, Delet a Course, Edit a Course,"
				+ "Display, Register a Student, Exit");
		System.out.println("Please enter your option from the menu");
		int editIndex=0;
		String menu=scanner.nextLine();
		while(!(menu).equals("Exit")){
		switch(menu){
		case "Create a new Course":courseList.add(a.createCourse());
									break;
		case "Delet a Course":a.deleteCourse(courseList);
									break;
		case "Edit a Course":a.editCourse(courseList);
									break;
		case "Display": a.display(courseList);
									break;
		case "Register a Student":a.registerStudent(studentList);
									break;						
		}
		System.out.println("Please enter another option from the menu");
		menu=scanner.nextLine();
		}
		if(menu.equals("Exit")){
			SerilizationStudentList();
			SerilizationCourseList();
		}
		System.out.println("You have logged out");
	}
	
	public static void AdminReport(Admin a){
		System.out.println("Menu: View all Course,View all Full,Write to File,View names of "
				+ "Students for a course, View registered courses of a student, Sort Courses, Exit");
		System.out.println("Please enter your option from the menu");
		String menu=scanner.nextLine();
		while(!(menu).equals("Exit")){
		switch(menu){
		case "View all Course":a.viewCourse(courseList);
								break;
		case "View all Full":a.viewAllFull(courseList);
								break;
		case "Write to File":a.writeToFile(courseList);
								break;
		case "View names of Students for a course":a.viewStudentName(courseList);
								break;
		case "View registered courses of a student":a.aStudentRegistered(studentList);
								break;
		case "Sort Courses":a.sortCourse(courseList);
								break;
		}
		System.out.println("Please enter another option from the menu");
		menu=scanner.nextLine();
		}
		if(menu.equals("Exit")){
				SerilizationStudentList();
				SerilizationCourseList();
				System.out.println("You have logged out");
		}
	}
	
	public static void fillCourseList() throws IOException{
		// TODO Auto-generated method stub
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
	
			String listOfNames=strTokens.nextToken();
			String instructor=strTokens.nextToken();
			int sectionNum=Integer.parseInt(strTokens.nextToken());
			String location=strTokens.nextToken();

			//List of names is null
			Course c=new Course(courseName,courseID,maxStu,currentStu,listOfNames,
					instructor,sectionNum,location);
			
			courseList.add(c);
		
		}
	}
	}
	
	public static void SerilizationCourseList(){
		try {
			//FileOutput Stream writes data to a file
			FileOutputStream fos = new FileOutputStream("courseList.ser");
			
			//ObjectOutputStream writes objects to a stream (A sequence of data)
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			//Writes the specific object to the OOS
			oos.writeObject(courseList);
			
			//Close both streams
			oos.close();
			fos.close();
			//System.out.println("CourseList Serialization complete");
		} 
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	public static void SerilizationStudentList(){
		try {
			//FileOutput Stream writes data to a file
			FileOutputStream fos = new FileOutputStream("studentList.ser");
			
			//ObjectOutputStream writes objects to a stream (A sequence of data)
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			//Writes the specific object to the OOS
			oos.writeObject(studentList);
			
			//Close both streams
			oos.close();
			fos.close();
			//System.out.println("StudentList Serialization complete");
		} 
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	

	public static void DeSerilizationCourseList(){
		ArrayList<Course>deCourseList=new ArrayList<Course>();
	 try{
		  //FileInputSystem recieves bytes from a file
	      FileInputStream fis = new FileInputStream("courseList.ser");
	      
	      //ObjectInputStream does the deserialization-- it reconstructs the data into an object
	      ObjectInputStream ois = new ObjectInputStream(fis);
	      
	      //Cast as Employee. readObject will take the object from ObjectInputStream
	      deCourseList = (ArrayList)ois.readObject();
	      ois.close();
	      fis.close();
	    }
	    catch(IOException ioe) {
	       ioe.printStackTrace();
	       return;
	    }
	 	catch(ClassNotFoundException cnfe) {
	       cnfe.printStackTrace();
	       return;
	     }
	 	courseList=deCourseList;
	}
	
	public static void DeSerilizationStudentList(){
		ArrayList<Student>deStudentList=new ArrayList<Student>();
	 try{
		  //FileInputSystem recieves bytes from a file
	      FileInputStream fis = new FileInputStream("studentList.ser");
	      
	      //ObjectInputStream does the deserialization-- it reconstructs the data into an object
	      ObjectInputStream ois = new ObjectInputStream(fis);
	      
	      //Cast as Employee. readObject will take the object from ObjectInputStream
	      deStudentList = (ArrayList)ois.readObject();
	      ois.close();
	      fis.close();
	    }
	    catch(IOException ioe) {
	       ioe.printStackTrace();
	       return;
	    }
	 catch(ClassNotFoundException cnfe) {
	       cnfe.printStackTrace();
	       return;
	     }
	 		studentList=deStudentList;
	}
}
