import java.util.ArrayList;
/*
 * person implements person interface, treat as generic T when constructing node
 */

public class Person implements PersonInterface{
	private String firstName;
	private String lastName;
	private int SSN;
	private int father;
	private int mother;
	private ArrayList<Friend> friendList=new ArrayList<Friend>();
	private Community friendTree;
	
	
	public Person(){
		
	}
	
	
	//read file
	public Person(String first, String last, String ssn, String father, String mother, String friend) {
		this.firstName=first;
		this.lastName=last;
		this.SSN=Integer.parseInt(ssn);
		this.mother=Integer.parseInt(mother);
		this.father=Integer.parseInt(father);
		String[] strArr=friend.split(",");

		friendTree=new Community();
		for(int i=0;i<strArr.length;i++){			
			Friend addF=new Friend(Integer.parseInt(strArr[i]));
			friendList.add(addF);
			Node newFriend=new Node(addF);
			friendTree.insert(newFriend);
		}
		//friendTree.preOrderTraversalPrint();
		//System.out.println();
	}
	
	public void print(){
		System.out.println();
		System.out.println("first name:"+this.firstName+", last name:"+this.lastName+", SSN:"+this.SSN+
				", father:"+this.father+", mother:"+this.mother);
		System.out.println("Friend List");
		printFriendList();
		
	}
	
	public void printFriendList() {
		for(Friend f:friendList){
			System.out.print(f.getSSN()+",");
		}
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getSSN() {
		return SSN;
	}
	public void setSSN(int sSN) {
		SSN = sSN;
	}
	public int getFather() {
		return father;
	}
	public void setFather(int father) {
		this.father = father;
	}
	public int getMother() {
		return mother;
	}
	public void setMother(int mother) {
		this.mother = mother;
	}
	
	public ArrayList<Friend> getFriendList() {
		return friendList;
	}
	public void setFriendList(ArrayList<Friend> friendList) {
		
		this.friendList = friendList;
	}
	
	
	public boolean isFriend(int ssn){
		ArrayList<Integer> checkFriend=new ArrayList<Integer>();
		checkFriend=this.scanFriends();
		for(int i=0;i<checkFriend.size();i++){
			if(checkFriend.get(i)==ssn){
				return true;
			}
		}
		return false;
	}

	@Override
	public String getName() {
		return this.firstName+" "+this.lastName;
	}


	public ArrayList<Integer> scanFriends() {
		ArrayList<Integer> friendList=new ArrayList<Integer>();
		friendList=this.friendTree.getFriendSSN(friendList);
		
		return friendList;
	}
}
