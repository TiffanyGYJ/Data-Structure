public class Node {
	private int ID;
	private int time;
	private Node next;
	
	public Node(){
		
	}
	
	public Node(int id, int date){
		this.ID=id;
		this.time=date;
		this.next = null;
	}	
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public void displayLink() {
		System.out.println(this.ID+" "+this.time);
	}
}
