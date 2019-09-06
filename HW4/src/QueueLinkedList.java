import java.util.NoSuchElementException;

public class QueueLinkedList implements Queue{

	private int size = 0;
	private Node first, last; 
	
	public QueueLinkedList(){
		this.size = 0;
		this.first = null;
		this.last = null;
	}
	
	public QueueLinkedList(Node node){
		node.setNext(null);
		this.first = node;
		this.last = node;
	}
	
	public boolean isEmpty(){
		if(size == 0){
			return true;
		}else{
			return false;
		}
	}
	
	public void enqueue(Node node) {
		node.setNext(null);
		if(isEmpty()){
			this.first = node;
			this.last = node;
			size = 1;
		}else{
			this.last.setNext(node);
			this.last = node;
			size += 1;
		}
	}
	public Node dequeue() {
		if(isEmpty()){
			throw new NoSuchElementException("No element");
		}
		Node node = this.first;
		this.first = this.first.getNext();
		this.size -= 1;
		if(this.size == 0){
			this.last = null;
		}
		return(node);
	}

	@Override
	public Node peak() {
		if(isEmpty()){
			throw new NoSuchElementException("No element");
		}
		return first;
	}
	
	public void displayList()      // display the list
    {
      System.out.println("List (first-->last): ");
      Node current = first;       // start at beginning of list
      while(current != null)      // until end of list,
         {
         current.displayLink();   // print data
         current = current.getNext();  // move to next link
         }
      System.out.println();
    }

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	
}
